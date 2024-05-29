package com.example.stockservice.messagequeue;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka // KafkaListener를 활성화하는 어노테이션
@Configuration // Spring의 구성 클래스임을 나타냄
public class KafkaConsumerConfig {
    // Kafka Consumer를 생성하는 Bean 정의
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        // 속성을 설정하기 위한 Map 생성
        Map<String, Object> properties = new HashMap<>();
        // Kafka 서버의 주소 설정
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // Consumer Group의 ID 설정
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
        // 메시지의 Key를 역직렬화하기 위한 디시리얼라이저 설정
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 메시지의 Value를 역직렬화하기 위한 디시리얼라이저 설정
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // 설정된 속성을 사용하여 Kafka ConsumerFactory 생성 후 반환
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());

        return kafkaListenerContainerFactory;
    }
}