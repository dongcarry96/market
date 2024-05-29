package com.example.orderservice.dto;

import com.example.orderservice.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchOrderDto {
    private String productId; // 물건 ID
    private Long stock; // 수량
    private Long unitPrice; // 물건 가격
    private Long totalPrice; // 총금액
    private OrderStatus status; // 주문상태
    private Long orderId; // 주문Id
    private String userId; // 회원 Id
    private Date createdAt; // 생성일자
}
