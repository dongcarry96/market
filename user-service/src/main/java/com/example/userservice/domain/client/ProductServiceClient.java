package com.example.userservice.domain.client;

import com.example.userservice.domain.vo.ResponseProduct;
import com.example.userservice.global.error.FeignErrorDecoder2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="product-service", configuration = FeignErrorDecoder2.class)
public interface ProductServiceClient {

    @GetMapping("/product-service/Products")
    List<ResponseProduct> getCatalogs();

}
