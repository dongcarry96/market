package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.KafkaOrderCancelDto;
import com.example.orderservice.dto.KafkaOrderDto;
import com.example.orderservice.dto.KafkaOrderReturnDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public KafkaOrderDto orderCreateSend(String topic, KafkaOrderDto kafkaOrderDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderDto);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: " + kafkaOrderDto);
        return kafkaOrderDto;
    }
    public KafkaOrderCancelDto orderCancelSend(String topic, KafkaOrderCancelDto kafkaOrderCancelDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderCancelDto);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: " + kafkaOrderCancelDto);
        return kafkaOrderCancelDto;
    }
    public KafkaOrderReturnDto orderReturnSend(String topic, KafkaOrderReturnDto kafkaOrderReturnDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderReturnDto);
        } catch(JsonProcessingException ex) {
            ex.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: " + kafkaOrderReturnDto);
        return kafkaOrderReturnDto;
    }

}
