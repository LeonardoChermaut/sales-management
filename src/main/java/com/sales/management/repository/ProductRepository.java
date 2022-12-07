package com.sales.management.repository;

import com.sales.management.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Optional<ProductModel> findByPrice(BigDecimal price);
}