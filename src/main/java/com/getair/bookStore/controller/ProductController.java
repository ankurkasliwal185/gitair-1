package com.getair.bookStore.controller;

import com.getair.bookStore.model.Product;
import com.getair.bookStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        return new ResponseEntity(productService.createProduct(product), HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<Product> updateProductStock(@RequestParam Long id,@RequestParam Integer quantity) {
        return new ResponseEntity(productService.updateProductStock(id,quantity), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(pageIndex, pageSize));
    }
}
