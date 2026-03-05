package com.spring.token.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JWT 인증/인가 필터 (모든 요청에 실행)
 *
 * 흐름:
 * 1. Cookie에서 Access Token 추출
 * 2. 토큰 검증 (서명, 만료)
 * 3. 토큰의 roles claim으로 권한 객체 생성 → DB 조회 없음
 * 4. SecurityContext에 Authentication 저장 → Spring Security가 인가 처리
 *
 * Access Token 만료 시:
 * → 401 응답 + "TOKEN_EXPIRED" 메시지
 * → 클라이언트(axios)가 /api/v1/auth/refresh 호출하여 재발급
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cookie에서 Access Token 추출

        // 토큰 없으면 인증 없이 통과 (permitAll 경로는 통과, 인가 필요 경로는 Security가 403 처리)

        try {
            // 토큰 검증 (서명 + 만료 확인)
            // 만료 시 TokenExpiredException, 변조 시 JWTVerificationException 발생


        } catch (TokenExpiredException e) {
            // Access Token 만료 → 클라이언트가 Refresh Token으로 재발급 요청해야 함
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"message\":\"TOKEN_EXPIRED\"}");
            return;

        } catch (Exception e) {
            // 토큰 변조 등 기타 오류
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"message\":\"유효하지 않은 토큰\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}