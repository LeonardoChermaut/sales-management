package com.sales.management.controller;

import com.sales.management.dto.EmployeeDto;
import com.sales.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(employeeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@Valid @PathVariable long id){
        return ResponseEntity.ok().body(employeeService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @PathVariable long id, @RequestBody EmployeeDto dto){
        employeeService.update(id, dto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody EmployeeDto dto){
        employeeService.save(dto);
        return ResponseEntity.accepted().body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Valid @PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
}
