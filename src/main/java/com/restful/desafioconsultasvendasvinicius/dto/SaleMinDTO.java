package com.restful.desafioconsultasvendasvinicius.dto;

import java.util.Objects;

import com.restful.desafioconsultasvendasvinicius.entities.Sale;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaleMinDTO {

    private String sellerName;
    private Double amount;

    public SaleMinDTO(Sale entity) {
        this.sellerName = entity.getSeller().getName();
        this.amount = entity.getAmount();
    }

    public SaleMinDTO(String key, Double value) {
        this.sellerName = key;
        this.amount = value;
    }

    public SaleMinDTO(SalesReportDTO salesReportDTO) {
        this.sellerName = salesReportDTO.getSellerName();
        this.amount = salesReportDTO.getAmount();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        SaleMinDTO that = (SaleMinDTO) o;

        return Objects.equals(this.amount, that.amount) && 
                Objects.equals(this.sellerName, that.sellerName);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        
        hash *= prime + ((this.amount == null) ? 0 : this.amount.hashCode());
        hash *= prime + ((this.sellerName == null) ? 0 : this.sellerName.hashCode());
        
        if (hash < 0)
            hash *= -1;

        return hash;
    }
}