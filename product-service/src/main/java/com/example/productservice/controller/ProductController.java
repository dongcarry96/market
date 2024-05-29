package com.example.productservice.controller;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.ProductEntity;
import com.example.productservice.messagequeue.KafkaProducer;
import com.example.productservice.service.ProductService;
import com.example.productservice.vo.RequestProduct;
import com.example.productservice.vo.ResponseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product-service")
public class ProductController {
    private final Environment env;
    private final ProductService productService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public ProductController(Environment env, ProductService productService
                                ,KafkaProducer kafkaProducer) {
        this.env = env;
        this.productService = productService;
        this.kafkaProducer = kafkaProducer;
    }

    /**
     *  상태 체크
     */
    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in Product Service on Port %s",
                env.getProperty("local.server.port"));
    }
    /**
     * 상품 등록
     */
    @PostMapping("/products")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct requestProduct) {
        // RequestProduct를 이용하여 ProductDto 빌더를 통해 상품 정보 생성
        ProductDto productDto = ProductDto.builder()
                .productId(requestProduct.getProductId())
                .productName(requestProduct.getProductName())
                .stock(requestProduct.getStock())
                .unitPrice(requestProduct.getUnitPrice())
                .build();

        // ProductService를 통해 상품 생성 후 생성된 정보를 ResponseProduct 빌더를 통해 반환
        ProductDto createDto = productService.createProduct(productDto);
        ResponseProduct returnValue = ResponseProduct.builder()
                .productId(createDto.getProductId())
                .productName(createDto.getProductName())
                .stock(createDto.getStock())
                .unitPrice(createDto.getUnitPrice())
                .build();

        // KafkaProducer를 통해 상품 생성 이벤트를 Kafka에 전송
        kafkaProducer.send("product-create-topic", createDto);

        // 생성된 상품 정보 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }




    /**
     * 상품 전체 조회
     * @return
     */
    @GetMapping("/products")
    public ResponseEntity<List<ResponseProduct>> getProducts() {
        // ProductService를 통해 모든 상품 정보를 조회
        Iterable<ProductEntity> productList = productService.getAllProducts();
        List<ResponseProduct> result = new ArrayList<>();

        // 각 상품 정보를 ResponseProduct 빌더를 통해 변환 후 리스트에 추가
        productList.forEach(v -> {
            ResponseProduct responseProduct = ResponseProduct.builder()
                    .productId(v.getProductId())
                    .productName(v.getProductName())
                    .stock(v.getStock())
                    .unitPrice(v.getUnitPrice())
                    .createdAt(v.getCreatedAt())
                    .build();
            result.add(responseProduct);
        });

        // 변환된 상품 정보 리스트 반환
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
