package com.example.userservice.domain.client;

import com.example.userservice.domain.vo.ResponseOrder;
import com.example.userservice.global.error.FeignErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-service", configuration = FeignErrorDecoder.class)
public interface OrderServiceClient {

    @GetMapping("/order-service/orders/user/{userId}/")
    List<ResponseOrder> getOrders(@PathVariable String userId);
}
