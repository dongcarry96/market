package com.example.stockservice.repository;


import com.example.stockservice.entity.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<StockEntity, String> {
    StockEntity findByProductId(String productId);
}
