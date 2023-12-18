package com.restful.desafioconsultasvendasvinicius.services.impl;

import com.restful.desafioconsultasvendasvinicius.dto.SaleMinDTO;
import com.restful.desafioconsultasvendasvinicius.entities.Sale;
import com.restful.desafioconsultasvendasvinicius.repositories.SaleRepository;
import com.restful.desafioconsultasvendasvinicius.repositories.SellerRepository;
import com.restful.desafioconsultasvendasvinicius.services.SaleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SaleService {
    
    private final SaleRepository saleRepository;
    
    public SalesServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public SaleMinDTO findSaleById(Long id) {
        return saleRepository.findById(id)
                .map(sale -> new SaleMinDTO(sale.getId(), sale.getAmount(), sale.getDate()))
                .orElseThrow(() -> new EntityNotFoundException("Sale not found with ID: " + id));
    }

    @Override
    public List<SaleMinDTO> findSalesReport(String startDate, String endDate, String sellerName) {
        // Tratamento das datas
        LocalDate start = startDate != null ? LocalDate.parse(startDate) : LocalDate.now().minusYears(1L);
        LocalDate end = endDate != null ? LocalDate.parse(endDate) : LocalDate.now();

        // Tratamento do nome do vendedor
        String name = sellerName != null ? sellerName : "";

        // Chama o método do repository para buscar as vendas
        List<Sale> sales = saleRepository.findSalesReport(start, end, name);

        // Converte a lista de vendas para uma lista de DTOs
        return sales.stream()
                .map(sale -> new SaleMinDTO(sale.getId(), sale.getAmount(), sale.getDate()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SaleMinDTO> findSalesSummary(String startDate, String endDate) {
        LocalDate start;
        LocalDate end;

        if (endDate == null) 
            end = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        else 
            end = LocalDate.parse(endDate);
        
        if (startDate == null) 
            start = end.minusYears(1L);
        else 
            start = LocalDate.parse(startDate);
        
        List<Sale> sales = saleRepository.findAllByDateBetweenOrderByDateDesc(start, end);

 
        Map<String, Double> salesSummary = getSummary(start, end); 
        return salesSummary.entrySet().stream()
                .map(entry -> new SaleMinDTO(entry.getKey(), entry.getValue())) 
                .collect(Collectors.toList());
    }

    public Map<String, Double> getSummary(LocalDate startDate, LocalDate endDate) {
        List<Object[]> summaryList = saleRepository.getSalesSummary(startDate, endDate);

        return summaryList.stream()
                .collect(Collectors.toMap(
                        array -> (String) array[0], // Seller name
                        array -> (Double) array[1] // Total sales amount
                ));
    }

    /*
    Relatório de vendas
    1. [IN] O usuário informa, opcionalmente:
        1.1 - data inicial, 
        1.2 - data final e 
        1.3 - um trecho do nome do vendedor.
     
    2. [OUT] O sistema informa uma listagem paginada contendo: 
        2.1 - id, 
        2.2 - data, 
        2.3 - quantia vendida e 
        2.4 - nome do vendedor, das vendas que se enquadrem nos dados informados.
     
    Informações complementares:
        
        * Se a data final não for informada, considerar a data atual do sistema. Para instanciar a data atual,
        utilize o comando: LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        
        * Se a data inicial não for informada, considerar a data de 1 ano antes da data final. Para instanciar
        uma data com um ano a menos, use a função minusYears: LocalDate result = minhaData.minusYears(1L);
        
        * Se o nome não for informado, considerar o texto vazio.
       
        * Dica: receba todos os dados como String no controller, e faça os tratamentos das datas acima,
        instanciando os objetos LocalDate, no service.
    */
}