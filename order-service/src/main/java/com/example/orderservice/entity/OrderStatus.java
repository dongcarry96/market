package com.example.orderservice.entity;

public enum OrderStatus {
    ORDER, //주문
    PREPARATION, //준비
    RETURN, // 반품
    FAIL, // 실패
    CANCEL, // 취소
    COMPLETE // 완료
}
