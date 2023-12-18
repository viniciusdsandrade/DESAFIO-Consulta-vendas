package com.restful.desafioconsultasvendasvinicius.controllers;

import com.restful.desafioconsultasvendasvinicius.dto.SaleMinDTO;
import com.restful.desafioconsultasvendasvinicius.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/sales")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
        SaleMinDTO dto = saleService.findSaleById(id);
        return ResponseEntity.ok(dto);
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

    @GetMapping(value = "/report")
    public ResponseEntity<List<SaleMinDTO>> getReport(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String sellerName) {

        // Tratamento das datas e chamada do serviço
        List<SaleMinDTO> report = saleService.findSalesReport(startDate, endDate, sellerName);

        // Retorna a resposta
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
    
    /*
    Sumário de vendas por vendedor
    1. [IN] O usuário informa, opcionalmente:
        1.1 - data inicial
        1.2 - data final.
        
    2. [OUT] O sistema informa uma listagem contendo:  
        2.1 - nome do vendedor
        2.2 - soma de vendas deste vendedor no período informado.
  
    Informações complementares:
        * As mesmas do caso de uso Relatório de vendas
    */
    @GetMapping(value = "/summary")
    public ResponseEntity<List<SaleMinDTO>> getSummary(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        // Tratamento das datas e chamada do serviço
        List<SaleMinDTO> summary =  saleService.findSalesSummary(startDate, endDate);

        // Retorna a resposta
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}