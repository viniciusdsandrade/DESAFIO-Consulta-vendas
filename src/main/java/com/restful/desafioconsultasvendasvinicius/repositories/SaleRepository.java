package com.restful.desafioconsultasvendasvinicius.repositories;


import com.restful.desafioconsultasvendasvinicius.dto.SalesReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restful.desafioconsultasvendasvinicius.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.restful.desafioconsultasvendasvinicius.dto.SalesReportDTO(s.id, s.date, s.amount, s.seller.name) " +
            "FROM Sale s " +
            "WHERE (:minDate IS NULL OR s.date >= :minDate) " +
            "AND (:maxDate IS NULL OR s.date <= :maxDate) " +
            "AND (LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    List<SalesReportDTO> findSalesReport(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            @Param("name") String name);

    @Query("SELECT s.seller.name, SUM(s.amount) FROM Sale s " +
            "WHERE (:startDate IS NULL OR s.date >= :startDate) " +
            "AND (:endDate IS NULL OR s.date <= :endDate) " +
            "GROUP BY s.seller.name")
    List<Object[]> getSalesSummary(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN :start AND :end ORDER BY s.date DESC")
    List<Sale> findAllByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);
    
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