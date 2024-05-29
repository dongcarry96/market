package com.example.userservice.domain.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
// 로그인 할때
@Data
public class RequestLogin {
    @NotNull(message = "이메일을 입력해주세요")
    @Size(min = 2, message = "최소 2글자 이상 적어주세요")
    private String email;
    @NotNull(message = "비밀번호를 입력해주세요")
    @Size(min = 8, message = "최소 8글자 이상 적어주세요")
    private String password;
}
