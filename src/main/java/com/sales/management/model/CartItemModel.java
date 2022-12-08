package com.sales.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItemModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @FutureOrPresent
    @Column(name = "sale_date", nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime saleDate;

    @ManyToOne(targetEntity = ProductModel.class, fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JoinColumn(name = "product_price", referencedColumnName = "price")
    private ProductModel product;

    @ManyToOne(targetEntity = EmployeeModel.class, fetch = FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JoinColumn(name = "employee_name", referencedColumnName = "name")
    private EmployeeModel employee;
}
