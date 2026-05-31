package com.sarvan.productservice.services;

import com.sarvan.productservice.entities.Products;
import com.sarvan.productservice.model.CreateUpdateProductRequest;

import java.util.List;

public interface ProductService {
    void createProduct(CreateUpdateProductRequest prodReq);
    List<Products> getProducts();
    Products getProduct(Long id) throws Exception;
    void deleteProduct(Long id) throws Exception;
    Products updateProduct(Long id, CreateUpdateProductRequest prodReq) throws Exception;
}
