package com.sales.management.service;

import com.sales.management.dto.EmployeeDto;
import com.sales.management.model.EmployeeModel;
import com.sales.management.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeService {

    @Autowired
   private EmployeeRepository employeeRepository;

    private final ModelMapper mapper = new ModelMapper();

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
        model.setAverageSale(dto.getAverageSale());
        model.setTotalSale(dto.getTotalSale());
        employeeRepository.save(model);
    }

    public void deleteEmployeeById(long id){
        findByIdOrElseThrow(id);
        employeeRepository.deleteById(id);
    }

    @Transactional
    private EmployeeDto findByIdOrElseThrow(long id) throws IllegalArgumentException {
        return employeeRepository.findById(id).map(model -> mapper.map(model, EmployeeDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<EmployeeDto> listAll() {
        return employeeRepository.findAll().stream().map(model -> mapper.map(model, EmployeeDto.class))
                .collect(Collectors.toList());

    }
}
