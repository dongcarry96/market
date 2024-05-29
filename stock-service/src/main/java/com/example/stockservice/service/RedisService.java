package com.example.stockservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisService {
    private final RedisTemplate<String, Long> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    // 상품 ID와 수량을 한 번에 설정하는 메서드
    public void setProductStock(Map<String, Long> productStockMap) {
        productStockMap.forEach((productId, stock) -> redisTemplate.opsForValue().set(productId, stock));
    }

    // 레디스에 정보 set
    public void setQuantity(String productId, Long stock) {
        redisTemplate.opsForValue().set(productId, stock);
    }
    // 레디스에 정보 get
    public Long getStock(String productId) {
        Long stock = redisTemplate.opsForValue().get(productId);
        if (stock != null) {
            return stock;
        } else {
            return null;
        }
    }
    // 수량만큼 감소
    public ResponseEntity<Long> decreaseQuantity(String productId, Long stock) {
        redisTemplate.opsForValue().decrement(productId, stock);
        return ResponseEntity.ok(stock);
    }
    // 수량만큼 증가
    public void increaseQuantity(String productId, Long stock) {
        redisTemplate.opsForValue().increment(productId, stock);
    }

    // 해당 키,값 삭제
    public void deleteQuantity(String productId) {
        redisTemplate.delete(productId);
    }
}
