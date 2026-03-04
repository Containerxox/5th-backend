package com.spring.api.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) //null인것은 제외해서 응답(json) 출력
public class ApiResponseDto<T> {
	
	private Integer statusCode;
	private HttpStatus httpStatus;
	private String message;
	private T data;
	
}
