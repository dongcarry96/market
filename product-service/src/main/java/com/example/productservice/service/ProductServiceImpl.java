package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    /**
     * 상품 전체 조회 기능
     */
    @Override
    public Iterable<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 상품 생성 기능
     */
    // 상품 생성 기능 구현
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        // ProductEntity 빌더를 사용하여 새로운 상품 엔티티 생성
        ProductEntity productEntity = ProductEntity.builder()
                .productId(UUID.randomUUID().toString())
                .productName(productDto.getProductName())
                .stock(productDto.getStock())
                .unitPrice(productDto.getUnitPrice())
                .build();

        // 생성된 상품 엔티티를 저장
        productRepository.save(productEntity);

        // 생성된 상품 정보를 ProductDto 빌더를 사용하여 반환 => controller로 보냄
        return ProductDto.builder()
                .productId(productEntity.getProductId())
                .productName(productEntity.getProductName())
                .stock(productEntity.getStock())
                .unitPrice(productEntity.getUnitPrice())
                .build();
    }
}
