package com.sales.management.service;

import com.sales.management.dto.EmployeeDto;
import com.sales.management.model.EmployeeModel;
import com.sales.management.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan("com.sales.management.service")
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final ModelMapper mapper = new ModelMapper();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public void save(EmployeeDto dto) {
        EmployeeModel model = mapper.map(dto, EmployeeModel.class);
        employeeRepository.save(model).getId();
    }
    @Transactional
    public EmployeeDto findById(long id) throws IllegalArgumentException {
        return findByIdOrElseThrow(id);
    }
    @Transactional
    public void update(long id, EmployeeDto dto)  {
        EmployeeModel model = this.employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.setName(dto.getName());
        employeeRepository.save(model);
    }
    @Transactional
    public void deleteEmployeeById(long id){
        findByIdOrElseThrow(id);
        employeeRepository.deleteById(id);
    }
    private EmployeeDto findByIdOrElseThrow(long id) throws IllegalArgumentException {
        return employeeRepository.findById(id).map(model -> mapper.map(model, EmployeeDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }
    public List<EmployeeDto> listAll() {
        return employeeRepository.findAll().stream().map(model -> mapper.map(model, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
