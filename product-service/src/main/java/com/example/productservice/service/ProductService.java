package com.example.productservice.service;


import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.ProductEntity;

public interface ProductService {
    Iterable<ProductEntity> getAllProducts();
    ProductDto createProduct(ProductDto productDto);
}
