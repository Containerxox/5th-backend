package com.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.service.EmpService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class EmpController {

	private final EmpService empService;
	
	@GetMapping(value="/jpa-emp-test")
	@ResponseBody
	public Object empTest() {
		Object result = null;
		
		result = empService.empTest();
		
		return result;
	}
}
