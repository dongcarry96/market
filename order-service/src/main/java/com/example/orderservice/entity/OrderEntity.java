package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false, length = 120)
    private String productId;
    @Column(nullable = false)
    private Long stock; // 재고
    @Column(nullable = false)
    private Long unitPrice; // 상품가격
    @Column(nullable = false)
    private Long totalPrice; //전체 가격
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}