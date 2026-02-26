package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	// "{"sname":"web-mvc", "grade":"junior"}"
	// Body에 Json 형식의 데이터담아서 서버에 전달하기
	// 1) @RequestBody 생략 불가능
	// 2) JSON -> java 객체 매핑 (스프링부트 내부의 jackson 라이브러리)
//	@PostMapping(value = "/test6")
//	public void test6(Student student) {  // ▷ 지금 이 경우, 자동 바인딩 안되고 있음
//		System.out.println("MVCController : test6()");
//		System.out.println(student);
//	}
	@PostMapping(value = "/test6")
	public void test6(@RequestBody Student student) {  // ▷ @RequestBody를 쓰면, body에 있는 value와 객체의필드명을 자동 바인딩
		System.out.println("MVCController : test6()");
		System.out.println(student);
	}
	

	
	// http://localhost:8080/test5?sname=web-mvc&grade=junior
	// 필드가 여러개면 복잡해져! => 스프링 부트가 자동으로 매핑
	// URL 쿼리 필드와 객체의 필드 이름이 서로 같다면, 스프링부트가 알아서 매핑해줌.
	@GetMapping(value = "/test5")
	public void test5(@ModelAttribute Student student) { //@ModelAttribute 생략가능
		System.out.println("MVCController : test5()");
		System.out.println(student);
	}
	

	
	// http://localhost:8080/test4?sname=web-mvc&grade=junior
	// 데이터를 받아오고 객체로 만들고 싶어!
	/*
	 * Student
	 * String sname
	 * String grade
	 * 
	 */
	@GetMapping(value = "/test4")
	public void test4(String sname, String grade) {
		System.out.println("MVCController : test4()");
		System.out.println("sid : "+ sname + ", " + "grade : " + grade);
		
		Student student = new Student(1, sname, grade);
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
