package com.sales.management.repository;

import com.sales.management.model.ProductModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan("com.sales.management.repository")
public interface ProductRepository extends JpaRepository<ProductModel, Long> {


}