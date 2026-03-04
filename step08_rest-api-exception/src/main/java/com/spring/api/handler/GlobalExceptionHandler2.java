package com.spring.api.handler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.api.dto.ErrorResponse;

// ▶ 전역 예외 핸들러 

@ControllerAdvice           // MVC용
public class GlobalExceptionHandler2 {
	
	
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
	public String handleIllegalArgument(IllegalArgumentException e, Model model) {
		model.addAttribute("exception",e.getMessage());
		
		
		return "error";
	}
	
}
