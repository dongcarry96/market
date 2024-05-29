package com.example.userservice.domain.service;

import com.example.userservice.domain.client.OrderServiceClient;
import com.example.userservice.domain.dto.UserDto;
import com.example.userservice.domain.entity.UserEntity;
import com.example.userservice.domain.repository.UserRepository;
import com.example.userservice.domain.vo.ResponseOrder;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;
    Environment env;
    OrderServiceClient orderServiceClient;
//    ProductServiceClient productServiceClient;
//    CircuitBreakerFactory circuitBreakerFactory;

    /**
     * 로그인
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null)
            throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           Environment env
                           ,OrderServiceClient orderServiceClient
//                           ,ProductServiceClient productServiceClient
//                           CircuitBreakerFactory circuitBreakerFactory
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
        this.orderServiceClient = orderServiceClient;
//        this.productServiceClient = productServiceClient;
//        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    /**
     * @param userDto
     * 회원가입
     */
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword())); // 비밀번호
        userEntity.setName(userDto.getName()); //이름
        userEntity.setAddress(userDto.getAddress()); // 주소
        userEntity.setEmail(userDto.getEmail()); // 이메일
        userEntity.setPhone(userDto.getPhone()); // 폰번호
        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }

    /**
     *  해당 고객 정보 가져오기
     * @param userId
     * @return
     */
    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null)
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        /* feignclient */
        List<ResponseOrder> ordersList = orderServiceClient.getOrders(userId);
        userDto.setOrders(ordersList);

        return userDto;
    }

    /**
     * 전체 회원 정보 가져오기
     *
     */
    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    /**
     * 사용자 인증을 위한 검색 메소드
     * @param email
     */
    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(userEntity, UserDto.class);
        return userDto;
    }

    /**
     * 회원 삭제
     */
    @Override
    public void deleteUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new IllegalArgumentException("해당 유저를 찾을 수 없습니다.");
        }
        userRepository.delete(userEntity);
    }
}
