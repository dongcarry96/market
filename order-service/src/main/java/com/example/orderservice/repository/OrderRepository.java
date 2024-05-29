package com.example.orderservice.repository;


import com.example.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(Long orderId);
    Iterable<OrderEntity> findByUserId(String userId);
}
