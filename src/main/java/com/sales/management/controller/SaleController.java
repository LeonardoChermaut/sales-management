package com.sales.management.controller;

import com.sales.management.dto.SaleDto;
import com.sales.management.exception.DataNotFoundException;
import com.sales.management.service.SaleService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "api/v1/cartitem")
public class SaleController {

    private final SaleService cartService;

    public SaleController(SaleService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDto> findById(@Valid @PathVariable Long id) throws DataNotFoundException {
        return ResponseEntity.ok().body(cartService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @PathVariable Long id, @RequestBody SaleDto dto) throws Exception {
        cartService.updateById(id, dto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody SaleDto dto){
       val df = new DecimalFormat("0.00");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.getDefault()));
        df.format(dto.getTotalPrice());
        cartService.save(dto);
        return ResponseEntity.accepted().body(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@Valid @PathVariable long id) throws DataNotFoundException {
        cartService.deleteById(id);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getAll() {
        return  ResponseEntity.ok(cartService.listAll());
    }
}
