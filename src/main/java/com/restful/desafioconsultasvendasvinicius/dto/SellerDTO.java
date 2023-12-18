package com.restful.desafioconsultasvendasvinicius.dto;

import com.restful.desafioconsultasvendasvinicius.entities.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private Long id;
    private String name;

    public SellerDTO(Seller seller) {
        this.id = seller.getId();
        this.name = seller.getName();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        SellerDTO that = (SellerDTO) o;

        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;

        hash *= prime + ((this.id == null) ? 0 : this.id.hashCode());
        hash *= prime + ((this.name == null) ? 0 : this.name.hashCode());

        if (hash < 0)
            hash *= -1;

        return hash;
    }
}