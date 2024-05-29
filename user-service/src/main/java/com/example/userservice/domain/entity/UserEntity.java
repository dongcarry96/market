package com.example.userservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String email; // 이메일

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private String phone; //전화번호
}