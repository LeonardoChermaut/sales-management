package com.sales.management.service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sales.management.dto.SaleDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.model.SaleModel;
import com.sales.management.repository.EmployeeRepository;
import com.sales.management.repository.ProductRepository;
import com.sales.management.repository.SaleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class SaleService {

    private SaleRepository saleRepository;
    private final ModelMapper mapper = new ModelMapper();

    @JsonDeserialize
    public void save(SaleDto dto) {
        SaleModel model = mapper.map(dto, SaleModel.class);
        model.setTotalPrice(bigDecimalToPrice(dto.getTotalPrice()));
        saleRepository.save(model);
    }

    public List<SaleDto> listAll() {
        return saleRepository.findAll().stream().map(model -> mapper.map(model, SaleDto.class))
                .collect(Collectors.toList());
    }

    public SaleDto findById(Long id) throws DataNotFoundException {
        return findByIdOrElseThrow(id);
    }

    public void deleteById(long id) throws DataNotFoundException {
        findByIdOrElseThrow(id);
        saleRepository.deleteById(id);
    }
    private SaleDto findByIdOrElseThrow(long id) throws DataNotFoundException {
        return saleRepository.findById(id).map(model -> mapper.map(model, SaleDto.class))
                .orElseThrow(DataNotFoundException::new);
    }

    public void updateById(long id, SaleDto dto) throws DataNotFoundException {
        SaleModel model = this.saleRepository.findById(id).orElseThrow(DataNotFoundException::new);
        model.setSaleDate(dto.getSaleDate());
        model.setTotalPrice(bigDecimalToPrice(dto.getTotalPrice()));
        saleRepository.save(model);
    }

    public BigDecimal bigDecimalToPrice (BigDecimal bd) {
        val df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.getDefault()));
        df.format(bd);
        return bd;
    }
}





