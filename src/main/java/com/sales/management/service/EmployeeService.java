package com.sales.management.service;

import com.sales.management.dto.EmployeeDTO;
import com.sales.management.model.Employee;
import com.sales.management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
   private EmployeeRepository employeeRepository;

    public void save(EmployeeDTO dto) {
        Employee employee = new Employee();
        toDto(dto, employee);
        employeeRepository.save(employee);
    }

    public void toDto(EmployeeDTO dto, Employee employee){
        employee.setName(dto.getName());

    }

    public Employee toModel(EmployeeDTO dto, Employee employee){
        dto.setName(employee.getName());

        return employee;
    }

    public EmployeeDTO findOneEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnData;
        EmployeeDTO dto = new EmployeeDTO();
        if (employee.isPresent()){
            employeeOnData = employee.get();
            toDto(dto, employee.get());
        }
        return dto;
    }

    public void update(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnBank = new Employee();

        if (employee.isPresent()) {
            employeeOnBank = employee.get();
            if (employeeDTO.getName() != null) {
                employeeOnBank.setName(employeeDTO.getName());
            }
            employeeRepository.save(employeeOnBank);
        }
    }

    public void deleteEmployeeById(long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> listAll()
    {
        return employeeRepository.findAll();
    }
}
