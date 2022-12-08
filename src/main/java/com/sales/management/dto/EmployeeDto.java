package com.sales.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

}
