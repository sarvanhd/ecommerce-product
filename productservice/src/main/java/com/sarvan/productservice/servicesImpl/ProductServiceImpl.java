package com.sarvan.productservice.servicesImpl;

import com.sarvan.productservice.dao.ProductsRepository;
import com.sarvan.productservice.entities.Products;
import com.sarvan.productservice.exception.ProductNotFoundException;
import com.sarvan.productservice.model.CreateUpdateProductRequest;
import com.sarvan.productservice.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository repository) {
        this.productsRepository = repository;
    }

    @Override
    public void createProduct(CreateUpdateProductRequest prodReq) {
        Products product = new Products();
        product.setName(prodReq.getName());
        product.setDescription(prodReq.getDescription());
        product.setCategory(prodReq.getCategory());
        product.setSubCategory(prodReq.getSubCategory());
        product.setPrice(prodReq.getPrice());
        productsRepository.save(product);
    }

    @Override
    public List<Products> getProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Products getProduct(Long id) throws Exception {
        log.info("Get product...");
        Optional<Products> user = productsRepository.findById(id);
        return user.orElseThrow(() -> new ProductNotFoundException("Product not found."));
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Delete product...");
        productsRepository.deleteById(id);
    }

    @Override
    public Products updateProduct(Long id, CreateUpdateProductRequest prodReq) throws Exception {
        Products product = productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found."));
        product.setName(prodReq.getName());
        product.setDescription(prodReq.getDescription());
        product.setCategory(prodReq.getCategory());
        product.setSubCategory(prodReq.getSubCategory());
        product.setPrice(prodReq.getPrice());
        productsRepository.save(product);
        return product;
    }
}
