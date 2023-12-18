package com.restful.desafioconsultasvendasvinicius.services;

import com.restful.desafioconsultasvendasvinicius.dto.SaleMinDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SaleService {
    SaleMinDTO findSaleById(Long id);

    List<SaleMinDTO> findSalesReport(String startDate, String endDate, String sellerName);

    Map<String, Double> getSummary(LocalDate startDate, LocalDate endDate);

    List<SaleMinDTO> findSalesSummary(String startDate, String endDate);
    
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