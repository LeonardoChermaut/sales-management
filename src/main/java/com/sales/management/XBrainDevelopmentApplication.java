package com.sales.management;

import com.sales.management.controller.EmployeeController;
import com.sales.management.controller.ProductController;
import com.sales.management.controller.SaleController;
import com.sales.management.service.EmployeeService;
import com.sales.management.service.ProductService;
import com.sales.management.service.SaleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackageClasses = {SaleController.class, EmployeeController.class, ProductController.class, SaleService.class,
EmployeeService.class, ProductService.class})
public class XBrainDevelopmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(XBrainDevelopmentApplication.class, args);
	}

}
