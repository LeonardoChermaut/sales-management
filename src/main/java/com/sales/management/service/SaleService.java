package com.sales.management.service;

import com.sales.management.dto.SaleDto;
import com.sales.management.model.SaleModel;
import com.sales.management.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper mapper = new ModelMapper();

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public void save(SaleDto dto) {
        SaleModel model = mapper.map(dto, SaleModel.class);
        saleRepository.save(model).getId();
    }
    @Transactional
    public List<SaleDto> listAll() {
        return saleRepository.findAll().stream().map(model -> mapper.map(model, SaleDto.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public SaleDto findById(Long id) {
        return findByIdOrElseThrow(id);
    }
    @Transactional
    public void deleteById(long id) {
        findByIdOrElseThrow(id);
        saleRepository.deleteById(id);
    }
    private SaleDto findByIdOrElseThrow(long id) throws IllegalArgumentException {
        return saleRepository.findById(id).map(model -> mapper.map(model, SaleDto.class))
                .orElseThrow(IllegalArgumentException::new);
    }
    @Transactional
    public void updateById(long id, SaleDto dto) throws Exception {
        SaleModel model = this.saleRepository.findById(id).orElseThrow(Exception::new);
        model.setId(dto.getId());
        model.setSaleDate(dto.getSaleDate());
        model.setTotalPrice(dto.getTotalPrice());
        saleRepository.save(model);
    }
}





