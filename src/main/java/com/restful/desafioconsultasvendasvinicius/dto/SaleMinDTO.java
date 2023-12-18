package com.restful.desafioconsultasvendasvinicius.dto;

import java.time.LocalDate;
import java.util.Objects;

import com.restful.desafioconsultasvendasvinicius.entities.Sale;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleMinDTO {

    private String sellerName;
    private Long id;

    @NotNull(message = "Amount is required")
    @PositiveOrZero(message = "Amount must be positive")
    private Double amount;

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be present or future")
    private LocalDate date;

    public SaleMinDTO(Sale entity) {
        this.id = entity.getId();
        this.amount = entity.getAmount();
        this.date = entity.getDate();
    }

    public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
        this(id, amount, date);
        this.sellerName = sellerName;
    }

    public SaleMinDTO(Long id, Double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public SaleMinDTO(String key, Double value) {
        this.sellerName = key;
        this.amount = value;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        SaleMinDTO that = (SaleMinDTO) o;

        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getAmount(), that.getAmount()) &&
                Objects.equals(this.getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash *= prime + ((this.id == null) ? 0 : this.id.hashCode());
        hash *= prime + ((this.amount == null) ? 0 : this.amount.hashCode());
        hash *= prime + ((this.date == null) ? 0 : this.date.hashCode());

        if (hash < 0)
            hash *= -1;

        return hash;
    }
}