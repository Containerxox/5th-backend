package com.spring.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.entity.Student;
import com.spring.api.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController  // @Controller + @ResponseBody
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping(value="/students/{sid}")
	public Student getStudent(@PathVariable Integer sid) { 
		Student student = null;
		
		student = studentService.getStudent(sid);
		
		return student;
	}
	
	
	
}
