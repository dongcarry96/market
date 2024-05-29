package com.example.orderservice.service;

import com.example.orderservice.dto.KafkaOrderCancelDto;
import com.example.orderservice.dto.KafkaOrderDto;
import com.example.orderservice.dto.KafkaOrderReturnDto;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.OrderEntity;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * kafka 주문 생성 메세지
     */
    public KafkaOrderDto kafkaCreateOrder(OrderDto orderDto) {
        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder()
                .productId(orderDto.getProductId())
                .stock(orderDto.getStock())
                .build();

        return kafkaOrderDto;
    }

    /**
     * 주문생성
     */
    @Override
    public OrderDto createOrder(OrderDto orderDto, String userId) {
        OrderEntity orderEntity = OrderEntity.builder()
                .productId(orderDto.getProductId())
                .stock(orderDto.getStock())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getStock() * orderDto.getUnitPrice())
                .status(OrderStatus.ORDER)
                .userId(userId)
                .build();
        orderRepository.save(orderEntity); // DB에 저장

        return OrderDto.builder()
                .productId(orderEntity.getProductId())
                .stock(orderEntity.getStock())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getStatus())
                .userId(userId)
                .orderId(orderEntity.getOrderId())
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }

    /**
     * 주문 검색 - 회원id로
     */
    @Override
    public List<OrderDto> getOrdersByUserId(String userId) {
        Iterable<OrderEntity> orderList = orderRepository.findByUserId(userId);

        // OrderEntity 리스트를 OrderDto 리스트로 변환
        return StreamSupport.stream(orderList.spliterator(), false)
                .map(orderEntity -> OrderDto.builder()
                        .orderId(orderEntity.getOrderId())
                        .productId(orderEntity.getProductId())
                        .stock(orderEntity.getStock())
                        .unitPrice(orderEntity.getUnitPrice())
                        .totalPrice(orderEntity.getTotalPrice())
                        .status(orderEntity.getStatus())
                        .userId(orderEntity.getUserId())
                        .createdAt(orderEntity.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 주문 검색 - 주문id로
     */
    @Override
    public OrderDto getOrderByOrderId(Long orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if (orderEntity == null) {
            return null;
        }
        return OrderDto.builder()
                .productId(orderEntity.getProductId())
                .stock(orderEntity.getStock())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getStatus())
                .userId(orderEntity.getUserId())
                .orderId(orderEntity.getOrderId())
                .createdAt(orderEntity.getCreatedAt())
                .build();
    }

    /**
     * 주문 취소 - 주문 ID
     */
    @Override
    public KafkaOrderCancelDto cancelOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity.getStatus() == OrderStatus.CANCEL || orderEntity.getStatus() == OrderStatus.RETURN) {
            throw new RuntimeException("취소처리를 실패하였습니다.");
        }
        else if (orderEntity != null) {
//            orderEntity.setStatus(OrderStatus.CANCEL);
            orderRepository.save(orderEntity);
            // 수동으로 변환
            KafkaOrderCancelDto kafkaOrderCancelDto  = KafkaOrderCancelDto.builder()
                    .orderId(orderEntity.getOrderId())
                    .status(OrderStatus.CANCEL)
                    .productId(orderEntity.getProductId())
                    .stock(orderEntity.getStock())
//                    .unitPrice(orderEntity.getUnitPrice())
//                    .totalPrice(orderEntity.getTotalPrice())
//                    .userId(orderEntity.getUserId())
//                    .createdAt(orderEntity.getCreatedAt())
                    .build();
            return kafkaOrderCancelDto;
        } else {
            return null;
        }
    }

    // 반품처리
    @Override
    public KafkaOrderReturnDto returnOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity.getStatus() == OrderStatus.CANCEL || orderEntity.getStatus() == OrderStatus.RETURN) {
            throw new RuntimeException("반품처리를 실패했습니다.");
        }
        else if (orderEntity != null) {
            orderRepository.save(orderEntity);
            KafkaOrderReturnDto kafkaOrderReturnDto =KafkaOrderReturnDto.builder()
                    .orderId(orderEntity.getOrderId())
                    .status(OrderStatus.RETURN)
                    .productId(orderEntity.getProductId())
                    .stock(orderEntity.getStock())
                    .build();
            return kafkaOrderReturnDto;
        } else {
            return null;
        }
    }

}
