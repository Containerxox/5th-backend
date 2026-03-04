package com.spring.api.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.api.dto.ErrorResponse;

// 전역 예외 핸들러 

@RestControllerAdvice  // REST API용 (= @ControllerAdvice + @ResponseBody)
public class GlobalExceptionHandler {
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(ErrorResponse.of("BAD_REQEUST", e.getMessage()));
	}
	
}
