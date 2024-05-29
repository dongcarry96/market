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
public class KafkaOrderReturnDto {
    private Long orderId;
    private String productId;
    private Long stock;
//    private Long unitPrice;
//    private Long totalPrice;
    private OrderStatus status;
//    private String userId;
//    private Date createdAt;

}
