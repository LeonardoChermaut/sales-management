package com.sales.management.controller;

import com.sales.management.dto.EmployeeDTO;
import com.sales.management.model.Employee;
import com.sales.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(employeeService.findOneEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody EmployeeDTO dto){
        employeeService.update(id, dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDTO dto){
        employeeService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
