package com.spring.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.spring.api.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController  // @Controller + @ResponseBody
public class StudentController {
	
	private final StudentService studentService;
	
	
	
	
}
