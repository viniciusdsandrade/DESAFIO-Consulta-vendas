package com.restful.desafioconsultasvendasvinicius.repositories;

import com.restful.desafioconsultasvendasvinicius.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
}
