package com.example.userservice.domain.service;

import com.example.userservice.domain.dto.UserDto;
import com.example.userservice.domain.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto); // 회원가입

    UserDto getUserByUserId(String userId); // 1명의 회원정보 찾기

    Iterable<UserEntity> getUserByAll(); // 회원정보 다 가져오기

    UserDto getUserDetailsByEmail(String userName);

    void deleteUserByUserId(String userId);
}
