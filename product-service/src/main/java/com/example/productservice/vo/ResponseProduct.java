package com.example.productservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProduct {
    private String productId;
    private String productName;
    private Long unitPrice;
    private Long stock;
    private Date createdAt;
}
