package com.sales.management.service;

import com.sales.management.dto.ProductDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.model.ProductModel;
import com.sales.management.repository.ProductRepository;
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
public class ProductService {
	private ProductRepository productRepository;
	private ModelMapper mapper;
	public ProductModel save(ProductDto dto) {
		ProductModel model = mapper.map(dto, ProductModel.class);
		model.setPrice(bigDecimalToPrice(dto.getPrice()));
		return productRepository.save(model);
	}
	public void deleteById(long id) throws DataNotFoundException {
		findByIdOrElseThrow(id);
		productRepository.deleteById(id);
	}
	public List<ProductDto> listAll() {
		return productRepository.findAll().stream().map(model -> mapper.map(model, ProductDto.class))
				.collect(Collectors.toList());
	}
	public void updateById(long id, ProductDto dto) throws DataNotFoundException {
		ProductModel model = this.productRepository.findById(id).orElseThrow(DataNotFoundException::new);
		model.setName(dto.getName());
		model.setPrice(bigDecimalToPrice(dto.getPrice()));
		productRepository.save(model);
	}
	public ProductDto findById(long id) throws DataNotFoundException {
		return findByIdOrElseThrow(id);
	}
	private ProductDto findByIdOrElseThrow(long id) throws DataNotFoundException{
		return productRepository.findById(id).map(model ->mapper.map(model, ProductDto.class))
				.orElseThrow(DataNotFoundException::new);
	}
	public BigDecimal bigDecimalToPrice (BigDecimal price) {
		val df = new DecimalFormat("0.00");
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.getDefault()));
		df.format(price);
		return price;
	}
}



