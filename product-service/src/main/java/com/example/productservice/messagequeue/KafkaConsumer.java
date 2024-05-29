package com.example.productservice.messagequeue;//package com.dong.good.domain.messagequeue;
//
//import com.dong.good.domain.entity.ProductEntity;
//import com.dong.good.domain.repository.ProductRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//@Slf4j
//public class KafkaConsumer {
//    ProductRepository repository;
//
//    @Autowired
//    public KafkaConsumer(ProductRepository repository) {
//        this.repository = repository;
//    }
//
//    /**
//     * 상품 주문 카프카 메세지
//     * @param kafkaMessage
//     */
//    @KafkaListener(topics = "order-create-topic")
//    public void updateQty(String kafkaMessage) {
//        log.info("Kafka Message: ->" + kafkaMessage);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        ProductEntity entity = repository.findByProductId((String)map.get("productId"));
//        if (entity != null) {
//            entity.setStock(entity.getStock() - (Integer)map.get("stock"));
//            repository.save(entity);
//        }
//    }
//
//    /**
//     * 상품 취소 카프카 메세지
//     * @param kafkaMessage
//     */
//    @KafkaListener(topics = "order-cancel-topic")
//    public void orderCancel(String kafkaMessage) {
//        log.info("Kafka Message: ->" + kafkaMessage);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        ProductEntity entity = repository.findByProductId((String)map.get("productId"));
//        if (entity != null) {
//            entity.setStock(entity.getStock() + (Integer)map.get("stock"));
//            repository.save(entity);
//        }
//    }
//
//
//    /**
//     * 상품 반품 카프카 메세지
//     * @param kafkaMessage
//     */
//    @KafkaListener(topics = "order-return-topic")
//    public void orderReturn(String kafkaMessage) {
//        log.info("Kafka Message: ->" + kafkaMessage);
//
//        Map<Object, Object> map = new HashMap<>();
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//
//        ProductEntity entity = repository.findByProductId((String)map.get("productId"));
//        if (entity != null) {
//            entity.setStock(entity.getStock() + (Integer)map.get("stock"));
//            repository.save(entity);
//        }
//    }
//}
