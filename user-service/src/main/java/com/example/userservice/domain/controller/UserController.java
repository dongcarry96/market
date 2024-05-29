package com.example.userservice.domain.controller;

import com.example.userservice.domain.dto.UserDto;
import com.example.userservice.domain.entity.UserEntity;
import com.example.userservice.domain.service.UserService;
import com.example.userservice.domain.vo.RequestUser;
import com.example.userservice.domain.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
    private UserService userService;
    private Environment env;

    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    /* 상태 체크 */
    @GetMapping("/health-check")
    public String status() {
        return String.format("It's Working in User Service on Port %s",
                env.getProperty("local.server.port"));
    }

    /**
     * 회원가입
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        // vo에 있는 ResponseUser에 담아서 사용자에게 전달
        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    // 회원정보 전체 가져오기
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseUser.class));
        });

        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 1명 회원정보 가져오기
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);

        return  ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    // 회원 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUserByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body("회원 정보를 삭제했습니다.");
    }
}
