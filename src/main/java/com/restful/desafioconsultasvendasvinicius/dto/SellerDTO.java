package com.restful.desafioconsultasvendasvinicius.dto;

import com.restful.desafioconsultasvendasvinicius.entities.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}