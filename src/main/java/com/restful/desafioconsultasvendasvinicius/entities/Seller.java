package com.restful.desafioconsultasvendasvinicius.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Seller")
@Table(name = "tb_seller", schema = "db_vendas")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "seller")
    @Setter(AccessLevel.NONE)
    private List<Sale> sales = new ArrayList<>();

    public Seller(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Seller that = (Seller) o;

        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getName(), that.getName()) &&
                Objects.equals(this.getEmail(), that.getEmail()) &&
                Objects.equals(this.getPhone(), that.getPhone()) &&
                Objects.equals(this.getSales(), that.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.getId(),
                this.getName(),
                this.getEmail(),
                this.getPhone(),
                this.getSales()
        );
    }
}