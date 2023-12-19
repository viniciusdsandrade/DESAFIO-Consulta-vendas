package com.restful.desafioconsultasvendasvinicius.dto;

import com.restful.desafioconsultasvendasvinicius.entities.Sale;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SalesReportDTO {
    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SalesReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SalesReportDTO(Sale entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.amount = entity.getAmount();
        this.sellerName = entity.getSeller().getName();
    }
}  