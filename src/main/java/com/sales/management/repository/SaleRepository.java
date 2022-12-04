package com.sales.management.repository;

import com.sales.management.model.SaleModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan("com.sales.management.repository")
public interface SaleRepository extends JpaRepository<SaleModel, Long> {

}
