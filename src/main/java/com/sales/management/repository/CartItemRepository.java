package com.sales.management.repository;

import com.sales.management.model.CartItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemModel, Long> {

}
