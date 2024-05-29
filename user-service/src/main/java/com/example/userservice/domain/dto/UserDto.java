package com.example.userservice.domain.dto;

import com.example.userservice.domain.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String password; // 비밀번호
    private String userId;
    private String address; // 주소
    private String phone; //전화번호
    private Date createdAt;
    private List<ResponseOrder> orders;
}
