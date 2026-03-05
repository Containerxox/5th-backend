package com.spring.token.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.token.entity.Users;
import com.spring.token.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * Thymeleaf 뷰 라우팅 컨트롤러
 *
 * - REST API가 아닌 페이지 이동을 담당
 * - SecurityConfig에서 URL 기반 인가 처리
 * - 인가 실패 시 서버가 /login 또는 /access-denied로 redirect
 */
@RequiredArgsConstructor
@Controller
public class PageController {

    private final UsersRepository usersRepository;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /** ROLE_USER 이상만 접근 가능 (SecurityConfig에서 제어) */
    @GetMapping("/user")
    public String user() {
        return "user";
    }

    /** ROLE_MANAGER 이상만 접근 가능 */
    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    /**
     * ROLE_ADMIN만 접근 가능
     * Thymeleaf 서버 렌더링: 사용자 목록을 Model에 담아서 전달
     * → admin.html의 th:each="${userList}"로 테이블 렌더링
     */
    @GetMapping("/admin")
    public String admin(Model model) {
        List<Users> userList = usersRepository.findAll();
        // 비밀번호 노출 방지
        userList.forEach(u -> u.setPassword("****"));
        model.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    /** 접근 거부 페이지 */
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
