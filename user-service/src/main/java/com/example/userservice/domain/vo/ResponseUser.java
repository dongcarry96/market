package com.example.userservice.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

// 회원정보 가져올떄
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String name;
    private String email;
    private String userId;
    private String address; // 주소
    private String phone; //전화번호

    private List<ResponseOrder> orders;
}
