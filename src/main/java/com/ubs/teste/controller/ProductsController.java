package com.ubs.teste.controller;

import com.ubs.teste.controller.model.SaleCalculationResult;
import com.ubs.teste.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/product")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/import")
    public ResponseEntity<?> importData() {
        productService.importProducts();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/calculateSell")
    public ResponseEntity<List<SaleCalculationResult>> calculateSale(@RequestParam String product, @RequestParam Integer establishmentQuantity) {
        return ResponseEntity.ok(productService.calculateSale(product, establishmentQuantity));
    }
}
