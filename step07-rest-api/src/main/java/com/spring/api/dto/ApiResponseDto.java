package com.spring.api.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
	
	private Integer statusCode;
	private HttpStatus httpStatus;
	private String message;
	private T data;
	
}
