package com.sales.management.repository;

import com.sales.management.model.EmployeeModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ComponentScan("com.sales.management.repository")
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

}
