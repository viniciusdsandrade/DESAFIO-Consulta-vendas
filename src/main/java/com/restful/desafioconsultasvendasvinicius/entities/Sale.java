package com.restful.desafioconsultasvendasvinicius.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Sale")
@Table(name = "tb_sales",
        schema = "db_vendas")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer visited;
    private Integer deals;
    private Double amount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Sale(Long id, Double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Sale that = (Sale) o;

        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getVisited(), that.getVisited()) &&
                Objects.equals(this.getDeals(), that.getDeals()) &&
                Objects.equals(this.getAmount(), that.getAmount()) &&
                Objects.equals(this.getDate(), that.getDate()) &&
                Objects.equals(this.getSeller(), that.getSeller());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash *= prime + ((this.id == null) ? 0 : this.id.hashCode());
        hash *= prime + ((this.visited == null) ? 0 : this.visited.hashCode());
        hash *= prime + ((this.deals == null) ? 0 : this.deals.hashCode());
        hash *= prime + ((this.amount == null) ? 0 : this.amount.hashCode());
        hash *= prime + ((this.date == null) ? 0 : this.date.hashCode());

        if (hash < 0)
            hash *= -1;

        return hash;
    }

    //Construtor de Cópia
    public Sale(Sale entity) {
        this.id = entity.getId();
        this.visited = entity.getVisited();
        this.deals = entity.getDeals();
        this.amount = entity.getAmount();
        this.date = entity.getDate();
        this.seller = entity.getSeller();
    }
}
