package com.spring.jpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.entity.Lecture;
import com.spring.jpa.entity.Student;
import com.spring.jpa.service.ApplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ApplyController {
	
	private final ApplyService applyService;
	
	@GetMapping(value = "/cascade-apply")
	@ResponseBody
	public Object cascadeApply() {
		Object result = null;
		
		result = applyService.cascadeApply();
		
		return result;
	}
	
	
	@GetMapping(value = "/osiv-apply")
	@ResponseBody
	public Object osivApply() {
		Object result = null;
		
		List<Lecture> lectures = applyService.osivApply();
		
		for(Lecture lecture : lectures) {
			
			List<Student> students = lecture.getStudents();
			
			for(Student student : students) {
				System.out.println("학생 이름 : " + student.getSname());
			}
			
		}
		
		return result;
	}
	
	@GetMapping(value = "/jpa")
	@ResponseBody
	public String jpaTest() {
		return "test";
	}
	
	@GetMapping(value = "/jpa-apply")
	@ResponseBody
	public Object jpaApply() {
		Object result = null;
		
		result = applyService.jpaApply();
		
		return result;
	}
	
	
}

