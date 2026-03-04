package com.spring.api.dto;

import java.time.LocalDateTime;

import lombok.Getter;

//ErrorResponse.java
@Getter
public class ErrorResponse {

 private final String code;
 private final String message;
 private final LocalDateTime timestamp;

 // 생성자를 private으로 막고 정적 팩토리 메서드 사용
 private ErrorResponse(String code, String message) {
     this.code = code;
     this.message = message;
     this.timestamp = LocalDateTime.now();
 }

 public static ErrorResponse of(String code, String message) {
     return new ErrorResponse(code, message);
 }

}