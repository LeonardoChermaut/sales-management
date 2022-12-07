package com.sales.management.service;

import com.sales.management.dto.EmployeeDto;
import com.sales.management.model.EmployeeModel;
import com.sales.management.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final ModelMapper mapper = new ModelMapper();
    public void save(EmployeeDto dto) {
        EmployeeModel model = mapper.map(dto, EmployeeModel.class);
        employeeRepository.save(model).getId();
    }

    public EmployeeDto findById(long id) throws IllegalArgumentException {
        return findByIdOrElseThrow(id);
    }
    public void update(long id, EmployeeDto dto)  {
        EmployeeModel model = this.employeeRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        model.setName(dto.getName());
        employeeRepository.save(model);
    }
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
