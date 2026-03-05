package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;



@Controller
public class CookieController {
	
	@GetMapping("cookie-test")
	public String cookieTest(HttpServletResponse response) {
			
		// 쿠키 생성
		Cookie c1 = new Cookie("cookie-id", "cookie-value");
		
		// 쿠키 관련 메서드
		c1.setMaxAge(60*60); // 얼마만큼의 시간동안 쿠키가 유효할건지
		
		// 내가 만든 쿠키를 reponse에 담아 브라우저의 로컬 스토리지에 저장해주기
		response.addCookie(c1);
		
		
		
		return "cookie";
	}
}
