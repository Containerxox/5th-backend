package com.spring.api.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.api.dto.ErrorResponse;

// ▶ 전역 예외 핸들러 

@RestControllerAdvice  // REST API용 (= @ControllerAdvice + @ResponseBody)
public class GlobalAPIExceptionHandler {
	
	
	/*
	 * 예외 발생 
	 * 누가 잡아서 처리하느냐?
	 * 1순위) try~catch 
	 * 2순위) 컨트롤러의 @ExceptionHandler
	 * 3순위) Advice의 @ExceptionHandler
	 * 4순위) Spring 기본 예외 처리(BasicErrorController)
	 * 
	 */
	
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(ErrorResponse.of("BAD_REQEUST", e.getMessage()));
	}
	
}
