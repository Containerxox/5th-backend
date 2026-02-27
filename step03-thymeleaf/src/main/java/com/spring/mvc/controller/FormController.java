package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.mvc.dto.Student;
import com.spring.mvc.dto.Student.Grade;
import com.spring.mvc.dto.User;


@Controller
public class FormController {
	
	
//	@GetMapping(value="/input-form")
//	public String moveInputForm(User user, Model model) {
//		System.out.println("InputForm");
//		System.out.println(user);
//		model.addAttribute("user",user);
//		
//		return "output";
//	}
	
	@GetMapping(value="/input-form")
	public String moveInputForm() {
		System.out.println("InputForm");
		
		return "input";
	}
	
	@PostMapping(value="/input-data")
	public String moveOutput(@ModelAttribute User user, Model model) {
		System.out.println("Output");
		System.out.println(user);
		 model.addAttribute("user", user);
		
		return "output";
	}
	
	
	@GetMapping(value="/form")
	public String form(Student student, Model model) {
		System.out.println("FormController");
		student.setGrade(Grade.JUNIOR);
		System.out.println(student);
		
		model.addAttribute("student", student);
		
		return "test";
	}
}

