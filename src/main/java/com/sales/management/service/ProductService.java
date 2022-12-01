package com.sales.management.service;

import com.sales.management.dto.ProductDto;
import com.sales.management.model.ProductModel;
import com.sales.management.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	private final ModelMapper mapper = new ModelMapper();

	@Transactional
	public void save(ProductDto dto) {
		ProductModel model = mapper.map(dto, ProductModel.class);
		productRepository.save(model);
	}

	@Transactional
	public void deleteById(long id){
		findByIdOrElseThrow(id);
		productRepository.deleteById(id);
	}

	@Transactional
	public List<ProductDto> listAll() {
		return productRepository.findAll().stream().map(model -> mapper.map(model, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public void updateById(long id, ProductDto dto)  {
		ProductModel model = this.productRepository.findById(id).orElseThrow(IllegalArgumentException::new);
		model.setName(dto.getName());
		model.setPrice(dto.getPrice());
		model.setQuantity(dto.getQuantity());
		productRepository.save(model);
	}

	@Transactional
	public ProductDto findById(long id) throws IllegalArgumentException {
		return findByIdOrElseThrow(id);
	}

	@Transactional
	private ProductDto findByIdOrElseThrow(long id) throws IllegalArgumentException{
		return productRepository.findById(id).map(model ->mapper.map(model, ProductDto.class))
				.orElseThrow(IllegalArgumentException::new);
	}


}



