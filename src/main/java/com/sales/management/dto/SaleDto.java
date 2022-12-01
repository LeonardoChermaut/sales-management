package com.sales.management.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime saleDate;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    private Long employeeId;
    @NotNull
    private String employeeName;
    @NotNull
    private Long productId;
}
