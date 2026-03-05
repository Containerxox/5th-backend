package com.spring.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.dto.SignUpRequestDto;
import com.spring.security.entity.User;
import com.spring.security.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final UserService userService;

    @GetMapping({ "", "/" })
    public String moveIndexPage() {
        return "index";
    }

    @GetMapping("/user")
    public String moveUserPage() {
        return "user";
    }

    @GetMapping("/admin")
    public String moveAdminPage() {
        return "admin";
    }

    @GetMapping("/manager")
    public String moveManagerPage() {
        return "manager";
    }

    @PreAuthorize("hasrole('ADMIN')")  // 메서드 기준 보안
    @GetMapping("/about")
    public String moveAboutPage() {
        return "about";
    }

    @GetMapping("/login")
    public String moveLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String moveSignupPage() {
        return "signup";
    }
	
	// 실제 회원가입 -> 회원가입 후 index page로 이동
    @PostMapping("/signup")
    public String signup(SignUpRequestDto dto) {
    	userService.signUp(dto); // 회원가입 처리
    	
    	return "redirect:/login"; // 가입 완료 후 /login 페이지로 리다이렉트
    }
    
}