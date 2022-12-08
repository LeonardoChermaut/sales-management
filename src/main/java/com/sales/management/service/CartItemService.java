package com.sales.management.service;

import com.sales.management.dto.CartItemDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.model.CartItemModel;
import com.sales.management.repository.EmployeeRepository;
import com.sales.management.repository.ProductRepository;
import com.sales.management.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CartItemService {

    private CartItemRepository saleRepository;
    private ProductRepository productRepository;
    private EmployeeRepository employeeRepository;
    private ModelMapper mapper;
    public CartItemModel save(CartItemDto dto) {
        CartItemModel model = mapper.map(dto, CartItemModel.class);
        model.setTotalPrice(bigDecimalToPrice(dto.getTotalPrice()));
       return saleRepository.save(model);
    }
    public List<CartItemDto> listAll() {
        return saleRepository.findAll().stream().map(model -> mapper.map(model, CartItemDto.class))
                .collect(Collectors.toList());
    }
    public CartItemDto findById(Long id) throws DataNotFoundException {
        return findByIdOrElseThrow(id);
    }
    public void deleteById(long id) throws DataNotFoundException {
        findByIdOrElseThrow(id);
        saleRepository.deleteById(id);
    }
    private CartItemDto findByIdOrElseThrow(long id) throws DataNotFoundException {
        return saleRepository.findById(id).map(model -> mapper.map(model, CartItemDto.class))
                .orElseThrow(DataNotFoundException::new);
    }
    public void updateById(long id, CartItemDto dto) throws DataNotFoundException {
        CartItemModel model = this.saleRepository.findById(id).orElseThrow(DataNotFoundException::new);
        model.setSaleDate(dto.getSaleDate());
        model.setTotalPrice(bigDecimalToPrice(dto.getTotalPrice()));
        saleRepository.save(model);
    }
    public BigDecimal bigDecimalToPrice (BigDecimal price) {
        val df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.getDefault()));
        df.format(price);
        return price;
    }
}





