package com.sales.management.controller;


import com.sales.management.dto.SaleDto;
import com.sales.management.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cartitem")
public class SaleController {

    private final SaleService cartService;

    public SaleController(SaleService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@Valid @PathVariable Long id){
        return ResponseEntity.ok().body(cartService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @PathVariable Long id, @RequestBody SaleDto dto) throws Exception {
        cartService.updateById(id, dto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody SaleDto dto){
        cartService.save(dto);
        return ResponseEntity.accepted().body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Valid @PathVariable long id){
        cartService.deleteById(id);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getAll() {
        return  ResponseEntity.ok(cartService.listAll());
    }
}
