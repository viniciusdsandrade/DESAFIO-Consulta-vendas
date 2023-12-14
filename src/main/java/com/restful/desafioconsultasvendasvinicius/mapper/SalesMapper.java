package com.restful.desafioconsultasvendasvinicius.mapper;

import com.restful.desafioconsultasvendasvinicius.dto.SaleMinDTO;
import com.restful.desafioconsultasvendasvinicius.entities.Sale;

public class SalesMapper {

    public static SaleMinDTO mapToSalesMinDTO(Sale sale) {
        return new SaleMinDTO(
                sale.getId(),
                sale.getAmount(),
                sale.getDate()
        );
    }

    public static Sale mapToSales(SaleMinDTO saleMinDTO) {
        return new Sale(
                saleMinDTO.getId(),
                saleMinDTO.getAmount(),
                saleMinDTO.getDate()
        );
    }
}