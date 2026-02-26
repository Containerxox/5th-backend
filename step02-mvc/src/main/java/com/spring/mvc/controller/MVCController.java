package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.dto.Student;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MVCController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void mvc() {
		System.out.println("mvc");
	}
	
	
	// http://localhost:8080/test11
	
	
	// http://localhost:8080/test10

	
	// http://localhost:8080/test9
	
	
	// http://localhost:8080/test8

	
	// http://localhost:8080/test7?emp=7369
	// http://localhost:8080/test7/emp/7369


	// http://localhost:8080/test6
	// "{"sid"="web-mvc", "grade"="junior"}"

	
	// http://localhost:8080/test5?sid=web-mvc&grade=junior

	
	// http://localhost:8080/test4?sid=web-mvc&grade=junior
	// 데이터를 받아오고 객체로 만들고 싶어!
	/*
	 * Student
	 * String sid
	 * String grade
	 * 
	 */
	@GetMapping(value = "/test4")
	public void test4(String sid, String grade) {
		System.out.println("MVCController : test4()");
		System.out.println("sid : "+ sid + ", " + "grade : " + grade);
		
		Student student = new Student(1, sid, grade);
		System.out.println(student);
		
	}

	
	// http://localhost:8080/test3?sid=web-mvc
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public void test3(String sid) {
		System.out.println("MVCController : test3()");
		System.out.println("sid : "+ sid);
	}
	
	
	// http://localhost:8080/test2?sid=web-mvc
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public void test2(HttpServletRequest request) {
		System.out.println("MVCController : test2()");
		
//		System.out.println(request.getContentType());
		String sid = request.getParameter("sid");
		System.out.println("sid : "+sid);
	}

	
	// http://localhost:8080/test1
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1()
	{
		System.out.println("MVCController : test1()");
	}
	
}
