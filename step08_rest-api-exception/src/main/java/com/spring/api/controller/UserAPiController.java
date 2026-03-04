package com.spring.api.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.dto.ErrorResponse;
import com.spring.api.service.BizService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserAPiController {
	private final BizService bizService;
	
	@GetMapping(value = "/user/api", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bizAPIMethod() {
		
		String result = null;
		result = bizService.bizAPIMethod();
		
		return result;
	}
	
	@GetMapping(value = "/user/api2", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bizAPIMethod2() {
		
		String result = null;
		result = bizService.bizAPIMethod();
		
		return result;
	}
	
	@GetMapping(value = "/user/api3", produces = MediaType.APPLICATION_JSON_VALUE)
	public String bizAPIMethod3() {
		
		String result = null;
		result = bizService.bizAPIMethod();
		
		return result;
	}
	
//	@ExceptionHandler({IllegalArgumentException.class})
//	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
//		return ResponseEntity
//					.status(HttpStatus.BAD_REQUEST)
//					.body(ErrorResponse.of("BAD_REQEUST", e.getMessage()));
//	}
	
	
	
	@ExceptionHandler({NoSuchElementException.class}) //예외 나열 가능
	public ResponseEntity<ErrorResponse> NoSuchElementException(NoSuchElementException e) {
		return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(ErrorResponse.of("NOT_FOUND", e.getMessage()));
	}
}