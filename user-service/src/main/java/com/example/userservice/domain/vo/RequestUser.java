package com.example.userservice.domain.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

// 화원가입 할때
@Data
public class RequestUser {
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "더 길게")
    @Email
    private String email;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "더 길게")
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "더 길게")
    private String password;

    @NotNull(message = "Address cannot be null")
    private String address; // 주소

    @NotNull(message = "Phone cannot be null")
    private String phone; //전화번호
}
