package com.restful.desafioconsultasvendasvinicius.mapper;

import com.restful.desafioconsultasvendasvinicius.dto.SellerDTO;
import com.restful.desafioconsultasvendasvinicius.entities.Seller;

public class SellerMapper {
    
    public static SellerDTO mapToSellerMinDTO(Seller seller) {
        return new SellerDTO(
                seller.getId(),
                seller.getName()
        );
    }
    
    public static Seller mapToSeller(SellerDTO SellerDTO) {
        return new Seller(
                SellerDTO.getId(),
                SellerDTO.getName()
        );
    }
}
