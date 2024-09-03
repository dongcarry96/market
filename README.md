# 🛒 예약마켓(reservation-market) 프로젝트

## 프로젝트 소개
다양한 물건을 구매할 수 있는 혁신적인 eCommerce 플랫폼입니다. 다양한 상품을 결제하고 예약 구매하는 통합 서비스를 경험 할 수 있습니다.
회원가입, 상품 주문, 재고 관리 등 전자상거래의 핵심 기능을 포함하고 있습니다.

---

## 💻 실행

1. 저장소 클론:
    ```sh
    git clone https://github.com/dongcarry96/market.git
    cd market
    ```
2. Gradle 빌드:
    ```sh
    ./gradlew build
    ```
3. 애플리케이션 실행:
    ```sh
    ./gradlew bootRun
    ```

## 🛠 기술 스택 
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Apache Kafka-%3333333.svg?style=for-the-badge&logo=Apache Kafka&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">

- **Language**: Java 17
- **Framework**: Spring boot 3.2.5
- **Build**: Gradle 8.7
- **Database**: MySQL 8.3
- **Messaging**: Kafka 3.7.0
- **Cache/Key-Value Store**: Redis 6.2.1
- **Container**: Docker

---

## 🔍 특징
- Spring Security 및 JWT를 활용한 사용자 인증 및 인가
- Spring Cloud 및 Netflix Eureka를 활용한 마이크로서비스 아키텍처
- Kafka를 활용하여 비동기 처리
- Redis를 이용한 데이터 일관성 유지

---

## 📚 API 문서

https://dong-2.gitbook.io/api

---

## 🧩 서비스 아키텍처

---

## 🗂 ERD
![erd](https://github.com/dongcarry96/reservation-market/assets/103975117/195b8f55-bfcf-4a9c-896b-cc5fcc3a65a4)

---

## 📁 폴더 구조
``` sh
api-gateway
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ apigateway
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ filter
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ AuthorizationHeaderFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ CustomFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ GlobalFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ LoggingFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ ApiGatewayApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┗ application.yml
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ apigateway
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartApiGatewayApplicationTests.java
eureka-server
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ eurekaserver
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ EurekaServerApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┗ application.yml
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ eurekaserver
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartEurekaServerApplicationTests.java
order-service
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ orderservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ OrderController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaOrderCancelDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaOrderDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaOrderReturnDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ OrderDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ SearchOrderDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ OrderEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ OrderStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ messagequeue
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaProducer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ KafkaProducerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ OrderRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ OrderService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ OrderServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ OrderServiceApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┗ application.yml
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ orderservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartOrderServiceApplicationTests.java
product-service
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ productservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ cofig
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ScheduleConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaProductDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ProductEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ messagequeue
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaConsumer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaConsumerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaProducer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ KafkaProducerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ProductService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ RequestProduct.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ResponseProduct.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductServiceApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┣ application.yml
 ┃ ┃ ┃ ┗ data.sql
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ productservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartProductServiceApplicationTests.java
stock-service
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ stockservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ RedisConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ StockDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ StockEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ messagequeue
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ KafkaConsumer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ KafkaConsumerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ redis
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ StockRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ RedisService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ CreateStock.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ StockServiceApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┗ application.yml
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ stockservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartStockServiceApplicationTests.java
user-service
 ┣ src
 ┃ ┣ main
 ┃ ┃ ┣ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ userservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ client
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ OrderServiceClient.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ProductServiceClient.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserEntity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ UserService.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserServiceImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ vo
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ RequestLogin.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ RequestUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ResponseOrder.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ ResponseProduct.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ ResponseUser.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ global
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ config
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ AES256Util.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ error
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ FeignErrorDecoder.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ FeignErrorDecoder2.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ redis
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ RedisConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ RedisUtil.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ security
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ AuthenticationFilter.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ WebSecurityNew.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ UserServiceApplication.java
 ┃ ┃ ┗ resources
 ┃ ┃ ┃ ┗ application.yml
 ┃ ┗ test
 ┃ ┃ ┗ java
 ┃ ┃ ┃ ┗ com
 ┃ ┃ ┃ ┃ ┗ example
 ┃ ┃ ┃ ┃ ┃ ┗ userservice
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ MartUserServiceApplicationTests.java
```
