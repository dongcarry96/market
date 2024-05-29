package com.example.productservice.repository;


import com.example.productservice.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId); // 상품 1건 조회
}
