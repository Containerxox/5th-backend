package com.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.service.DeptService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class DeptController {
	
	private final DeptService deptService; // 의존성 주입
	
	@GetMapping(value="/jpa-test")
	@ResponseBody
	public Object jpaTest() {
		Object result = null;
		
		result = deptService.jpaTest();
		
		return result;
	}
}
