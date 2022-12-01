package com.sales.management.service;

import com.sales.management.dto.ProductDTO;
import com.sales.management.model.Product;
import com.sales.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void save(ProductDTO dto) {
		Product product = new Product();
		toDto(dto, product);
		productRepository.save(product);
	}

	public void toDto(ProductDTO dto, Product product){
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
	}

	public Product toModel(ProductDTO dto, Product product){
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setQuantity(product.getQuantity());

		return product;
	}

	public void deleteById(long id){productRepository.deleteById(id);}

	public List<Product> getAll() {return productRepository.findAll();}

	public ProductDTO findById(Long id){
		Optional<Product> product = productRepository.findById(id);
		Product productOnData;
		ProductDTO dto = new ProductDTO();
		if (product.isPresent()){
			productOnData = product.get();
			toDto(dto, product.get());
		}
		return dto;
	}

	public void updateById(Long id, ProductDTO dto) {
		Optional<Product> product = productRepository.findById(id);
		Product productOnBank = new Product();
		if (product.isPresent()) {
			productOnBank = product.get();
			productRepository.save(productOnBank);
		}
	}

}



