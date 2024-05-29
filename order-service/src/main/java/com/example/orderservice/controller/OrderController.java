package com.example.orderservice.controller;


import com.example.orderservice.dto.KafkaOrderCancelDto;
import com.example.orderservice.dto.KafkaOrderDto;
import com.example.orderservice.dto.KafkaOrderReturnDto;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
    Environment env;
    OrderService orderService;

    KafkaProducer kafkaProducer;

    @Autowired
    public OrderController(Environment env, OrderService orderService,
                           KafkaProducer kafkaProducer) {
        this.env = env;
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in Order Service on Port %s",
                env.getProperty("local.server.port"));
    }

    /**
     * 회원 아이디로 주문하기
     */
    @PostMapping("/orders/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable("userId") String userId,
                                                @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto, userId);
        KafkaOrderDto kafkaCreateOrder = orderService.kafkaCreateOrder(orderDto);

        kafkaProducer.orderCreateSend("order-create-topic", kafkaCreateOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     *  주문 검색 - 해당 회원
     * @param userId
     */
    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrder(@PathVariable("userId") String userId)
                throws Exception {
        List<OrderDto> orderList = orderService.getOrdersByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    /**
     * 주문 상세 조회 - 주문 ID로
     * @param orderId 주문 ID
     * @return ResponseEntity<ResponseOrder> 주문 상세 정보 응답
     */
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderDetails(@PathVariable("orderId") Long orderId) {
        // 주문 ID를 사용하여 주문 상세 정보 가져오기
        OrderDto orderDto = orderService.getOrderByOrderId(orderId);
        if (orderDto == null) {
            // 주문이 존재하지 않는 경우 404 에러 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    /**
     * 주문 취소 - 주문 ID
     */
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Long orderId) {
        KafkaOrderCancelDto kafkaOrderCancelDto = orderService.cancelOrder(orderId);
        if (kafkaOrderCancelDto != null) {
            kafkaProducer.orderCancelSend("order-cancel-topic", kafkaOrderCancelDto);
            return ResponseEntity.ok().body("주문 ID " + orderId + "가 성공적으로 취소되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문 ID " + orderId + "를 찾을 수 없습니다.");
        }
    }
    /**
     * 주문 반품 - 주문 ID
     * @param orderId 주문 ID
     * @return
     */
    @PutMapping("/return/{orderId}")
    public ResponseEntity<String> returnOrder(@PathVariable("orderId") Long orderId) {
        KafkaOrderReturnDto kafkaOrderReturnDto = orderService.returnOrder(orderId);
        if (kafkaOrderReturnDto != null) {
            kafkaProducer.orderReturnSend("order-return-topic", kafkaOrderReturnDto);
            return ResponseEntity.ok().body("주문 ID " + orderId + "가 성공적으로 반품되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("주문 ID " + orderId + "를 찾을 수 없습니다.");
        }
    }

}
