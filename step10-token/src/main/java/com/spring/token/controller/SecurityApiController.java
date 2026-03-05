package com.spring.token.controller;


import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.spring.token.config.auth.PrincipalDetails;
import com.spring.token.config.jwt.CookieUtil;
import com.spring.token.config.jwt.JwtProperties;
import com.spring.token.config.jwt.JwtUtil;
import com.spring.token.entity.Users;
import com.spring.token.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class SecurityApiController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // ============================================================
    // 공개 API (permitAll)
    // ============================================================

    /** 메인 페이지 */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 회원가입
     * - 비밀번호 BCrypt 암호화
     * - 기본 권한: ROLE_USER
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users users) {
        if (usersRepository.findByUsername(users.getUsername()) != null) {
            return ResponseEntity.badRequest().body(Map.of("message", "이미 존재하는 사용자입니다"));
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles("ROLE_USER");  // 기본 권한
        usersRepository.save(users);

        return ResponseEntity.ok(Map.of("message", "회원가입 성공"));
    }

    // ============================================================
    // 인증 관련 API
    // ============================================================

    /**
     * Access Token 재발급
     *
     * 흐름:
     * 1. Cookie에서 Refresh Token 추출
     * 2. Refresh Token 검증
     * 3. username으로 DB 조회 → 새 Access Token 발급
     * 4. 새 Access Token을 Cookie에 저장
     *
     * JwtAuthorizationFilter가 Access Token 만료 시 TOKEN_EXPIRED 응답
     * → 클라이언트 axios 인터셉터가 이 엔드포인트 호출
     */
    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = CookieUtil.getCookieValue(request, JwtProperties.REFRESH_TOKEN_COOKIE);

        if (refreshToken == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Refresh Token이 없습니다. 다시 로그인하세요"));
        }

        try {
            // Refresh Token 검증
            String username = JwtUtil.getUsername(refreshToken);
            Users user = usersRepository.findByUsername(username);

            if (user == null) {
                return ResponseEntity.status(401).body(Map.of("message", "사용자를 찾을 수 없습니다"));
            }

            PrincipalDetails principalDetails = new PrincipalDetails(user);

            // 새 Access Token 발급
            String newAccessToken = JwtUtil.generateAccessToken(principalDetails);

            // Cookie 갱신
            response.addCookie(CookieUtil.createCookie(
                    JwtProperties.ACCESS_TOKEN_COOKIE,
                    newAccessToken,
                    JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME / 1000,
                    true
            ));

            return ResponseEntity.ok(Map.of("message", "Access Token 재발급 성공"));

        } catch (TokenExpiredException e) {
            // Refresh Token도 만료 → 재로그인 필요
            CookieUtil.deleteCookie(response, JwtProperties.ACCESS_TOKEN_COOKIE);
            CookieUtil.deleteCookie(response, JwtProperties.REFRESH_TOKEN_COOKIE);
            return ResponseEntity.status(401).body(Map.of("message", "세션이 만료되었습니다. 다시 로그인하세요"));
        }
    }

    /**
     * 로그아웃
     * - Cookie 삭제
     */
    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        CookieUtil.deleteCookie(response, JwtProperties.ACCESS_TOKEN_COOKIE);
        CookieUtil.deleteCookie(response, JwtProperties.REFRESH_TOKEN_COOKIE);
        return ResponseEntity.ok(Map.of("message", "로그아웃 성공"));
    }

    /**
     * 현재 로그인 사용자 정보 반환
     * - SecurityContext의 Authentication에서 추출 (DB 조회 없음)
     */
    @GetMapping("/auth/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body(Map.of("message", "로그인 필요"));
        }
        return ResponseEntity.ok(Map.of(
                "username",   authentication.getName(),
                "roles",      authentication.getAuthorities()
        ));
    }

    // ============================================================
    // 권한별 API
    // ============================================================

    /** USER 이상 접근 가능 */
    @GetMapping("/user/info")
    public ResponseEntity<?> userInfo(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "message",  "USER 페이지",
                "username", authentication.getName(),
                "roles",    authentication.getAuthorities().toString()
        ));
    }

    /** MANAGER 이상 접근 가능 */
    @GetMapping("/manager/documents")
    public ResponseEntity<?> documents(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "message",  "MANAGER 페이지",
                "username", authentication.getName()
        ));
    }

    /** ADMIN만 접근 가능 */
    @GetMapping("/admin/users")
    public ResponseEntity<?> users() {
        List<Users> userList = usersRepository.findAll();
        // 비밀번호 노출 방지
        userList.forEach(u -> u.setPassword("****"));
        return ResponseEntity.ok(userList);
    }

    /**
     * ADMIN - 특정 사용자 권한 변경
     * body: { "roles": "ROLE_USER,ROLE_MANAGER" }
     */
    @PutMapping("/admin/users/{id}/roles")
    public ResponseEntity<?> updateRoles(@PathVariable Long id,
                                         @RequestBody Map<String, String> body) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        user.setRoles(body.get("roles"));
        usersRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "권한 변경 완료"));
    }
}