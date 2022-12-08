package com.sales.management.service;

import com.sales.management.dto.EmployeeDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.model.EmployeeModel;
import com.sales.management.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.val;
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

    public EmployeeModel save(EmployeeDto dto) {
        EmployeeModel model = mapper.map(dto, EmployeeModel.class);
        return employeeRepository.save(model);
    }

    public EmployeeDto findById(long id) throws DataNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public void update(long id, EmployeeDto dto) throws DataNotFoundException {
        EmployeeModel model = this.employeeRepository.findById(id).orElseThrow(DataNotFoundException::new);
        model.setName(dto.getName());
        employeeRepository.save(model);
    }

    public void deleteEmployeeById(long id) throws DataNotFoundException {
        findByIdOrElseThrow(id);
        employeeRepository.deleteById(id);
    }

    private EmployeeDto findByIdOrElseThrow(long id) throws DataNotFoundException {
        return employeeRepository.findById(id).map(model -> mapper.map(model, EmployeeDto.class))
                .orElseThrow(() -> new DataNotFoundException("Employee not found for id: " + id));
    }

    public List<EmployeeDto> listAll() {
        return employeeRepository.findAll().stream().map(model -> mapper.map(model, EmployeeDto.class))
                .collect(Collectors.toList());
    }
}
