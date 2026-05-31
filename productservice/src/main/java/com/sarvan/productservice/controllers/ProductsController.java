package com.sarvan.productservice.controllers;

import com.sarvan.productservice.entities.Products;
import com.sarvan.productservice.model.CreateUpdateProductRequest;
import com.sarvan.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    public ProductsController(ProductService service) {
        this.productService = service;
    }

    @GetMapping("/{productId}")
    public Products getProduct(@PathVariable Long productId) throws Exception {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public Products updateProduct(@PathVariable Long productId, @RequestBody @Valid CreateUpdateProductRequest request) throws Exception {
        return productService.updateProduct(productId, request);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) throws Exception {
        productService.deleteProduct(productId);
    }
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody @Valid CreateUpdateProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity
                .status(HttpStatus.CREATED).body("Product Created Successfully");
    }
    @GetMapping
    public List<Products> getProducts() {
        return productService.getProducts();
    }

}
