package com.example.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductDto implements Serializable {// 직렬화
    private String productId;
    private String productName; // 상품이름
    private Long stock; // 재고
    private Long unitPrice; // 상품가격
    private Long totalPrice; // 전체 가격
    private Long orderId;
    private String userId;
}
