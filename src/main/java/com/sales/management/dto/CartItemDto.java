package com.sales.management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sales.management.model.ProductModel;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime saleDate;

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    private Long employeeId;

    @NotNull
    private String employeeName;

    @NotNull
    private Set<Long> productId;

    @NotNull
    private Set<BigDecimal> productPrice;

}
