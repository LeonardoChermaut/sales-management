package com.sales.management.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItem  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName="id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;

    @Column(name = "cart_quantity")
    private int quantity;

    @Column(name = "cart_total")
    private BigDecimal totalPrice;
}
