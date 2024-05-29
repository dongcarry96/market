package com.example.orderservice.service;


import com.example.orderservice.dto.*;
import com.example.orderservice.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto, String userId); // 주문하기
    OrderDto getOrderByOrderId(Long orderId); // 주문id로 검색
    List<OrderDto> getOrdersByUserId(String userId); // 회원이 주문한 것들 검색
    KafkaOrderCancelDto cancelOrder(Long orderId); // 주문 취소
    KafkaOrderReturnDto returnOrder(Long orderId); // 주문 반품
    KafkaOrderDto kafkaCreateOrder(OrderDto orderDto);

}
