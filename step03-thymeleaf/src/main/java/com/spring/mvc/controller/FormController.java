package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.mvc.dto.Student;


@Controller
public class FormController {
	
	@GetMapping(value="/form")
	public String form(Student student) {
		System.out.println("FormController");
		System.out.println(student);
		
		return "test";
	}
}

