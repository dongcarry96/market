package com.example.userservice.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Long stock;
    private Long unitPrice;
    private Long totalPrice;
    private Date createdAt;

    private String orderId;
}
