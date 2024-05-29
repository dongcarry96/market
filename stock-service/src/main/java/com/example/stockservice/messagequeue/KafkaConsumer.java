package com.example.stockservice.messagequeue;

import com.example.stockservice.entity.StockEntity;
import com.example.stockservice.repository.StockRepository;
import com.example.stockservice.service.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {
    StockRepository stockRepository;
    RedisService redisService;

    @Autowired
    public KafkaConsumer(StockRepository stockRepository, RedisService redisService) {
        this.stockRepository = stockRepository;
        this.redisService = redisService;
    }

    /**
     * 상품 등록 -> Redis에 저장
     * @param kafkaMessage
     */
    @KafkaListener(topics = "product-create-topic")
    public void productCreate(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> map = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {});
            Map<String, Long> productStockMap = new HashMap<>();

            // productId와 stock을 변환 및 저장
            Object productId = map.get("productId");
            Object stock = map.get("stock");
            productStockMap.put(String.valueOf(productId), (Long) stock);
            redisService.setProductStock(productStockMap);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }


//    /**
//     * 상품 등록 -> 재고DB에 저장
//     * @param kafkaMessage
//     */
//    @KafkaListener(topics = "product-create-topic")
//    public void productCreate(String kafkaMessage) {
//        log.info("Kafka Message: ->" + kafkaMessage);
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Map<String, Object> map = mapper.readValue(kafkaMessage, new TypeReference<Map<String, Object>>() {});
//            StockEntity stockEntity = new StockEntity();
//
//            // productId를 String으로 변환
//            Object productId = map.get("productId");
//            if (productId != null) {
//                stockEntity.setProductId(String.valueOf(productId));
//            } else {
//                log.error("productId is null in the Kafka message");
//                return; // productId가 null이면 저장하지 않음
//            }
//            // stock을 Long으로 변환
//            Object stock = map.get("stock");
//            if (stock != null) {
//                if (stock instanceof Integer) {
//                    stockEntity.setStock(((Integer) stock).longValue());
//                } else if (stock instanceof Long) {
//                    stockEntity.setStock((Long) stock);
//                } else {
//                    log.error("Invalid stock type in the Kafka message");
//                    return; // stock의 타입이 Integer나 Long이 아니면 저장하지 않음
//                }
//            } else {
//                log.error("stock is null in the Kafka message");
//                return; // stock이 null이면 저장하지 않음
//            }
//            stockRepository.save(stockEntity);
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//    }


    /**
     * 상품 주문 카프카 메세지
     * 가져올 데이터 상품id, 수량
     * @param kafkaMessage
     */
    @KafkaListener(topics = "order-create-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        StockEntity entity = stockRepository.findByProductId((String)map.get("productId"));
        if (entity != null) {
            entity.setStock(entity.getStock() - (Integer)map.get("stock"));
            stockRepository.save(entity);
        }
    }

    /**
     * 주문 취소 카프카 메세지
     * @param kafkaMessage
     */
    @KafkaListener(topics = "order-cancel-topic")
    public void orderCancel(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        StockEntity entity = stockRepository.findByProductId((String) map.get("productId"));
        if (entity != null) {
            entity.setStock(entity.getStock() + (Integer)map.get("stock"));
            stockRepository.save(entity);
        }
    }


    /**
     * 주문 반품 카프카 메세지
     * @param kafkaMessage
     */
    @KafkaListener(topics = "order-return-topic")
    public void orderReturn(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }

        StockEntity entity = stockRepository.findByProductId((String) map.get("productId"));
        if (entity != null) {
            entity.setStock(entity.getStock() + (Integer)map.get("stock"));
            stockRepository.save(entity);
        }
    }
}
