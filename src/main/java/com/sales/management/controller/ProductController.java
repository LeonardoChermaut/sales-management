package com.sales.management.controller;

import com.sales.management.dto.ProductDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.model.ProductModel;
import com.sales.management.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@Valid @PathVariable long id) throws DataNotFoundException {
        return ResponseEntity.ok().body(productService.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @PathVariable long id, @RequestBody ProductDto dto) throws DataNotFoundException {
        productService.updateById(id, dto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<ProductModel> save(@Valid @RequestBody ProductDto dto){
        return ResponseEntity.accepted().body(productService.save(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Valid @PathVariable long id) throws DataNotFoundException {
        productService.deleteById(id);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
}
