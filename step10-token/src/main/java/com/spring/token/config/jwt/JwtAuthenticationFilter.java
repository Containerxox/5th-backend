package com.spring.token.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.token.config.auth.PrincipalDetails;
import com.spring.token.dto.LoginRequestDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * 로그인 처리 필터 (POST /login)
 *
 * 흐름:
 * 1. attemptAuthentication() → 로그인 요청 파싱 → AuthenticationManager 인증
 * 2. successfulAuthentication() → JWT 생성 → httpOnly Cookie에 저장
 * 3. failedAuthentication() → 401 응답
 *
 * UsernamePasswordAuthenticationFilter를 상속하므로 /login POST 자동 매핑
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /**
     * [Step 1] 로그인 인증 시도
     * - request body에서 username/password 파싱
     * - AuthenticationManager → PrincipalDetailsService.loadUserByUsername() 호출
     * - BCrypt 비밀번호 검증
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
    	
        ObjectMapper om = new ObjectMapper();
        LoginRequestDto loginRequestDto = null;

        // JSON body 파싱 → LoginRequestDto
        try(ServletInputStream is = request.getInputStream()){
        	loginRequestDto = om.readValue(is,LoginRequestDto.class); //LoginRequestDto로 변환(파싱)
        	System.out.println(loginRequestDto);
        	
        	
        }catch (IOException e){
        	e.printStackTrace();
        }

        // UsernamePasswordAuthenticationToken 생성 (미인증 상태)
        UsernamePasswordAuthenticationToken authenticationToken = 
        		new UsernamePasswordAuthenticationToken(
        				loginRequestDto.getUsername(),
        				loginRequestDto.getPassword()
        		);
        

        // AuthenticationManager가 PrincipalDetailsService를 통해 DB 조회 + 비밀번호 검증
        // 성공 시 Authentication 객체 반환, 실패 시 AuthenticationException 발생
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * [Step 2] 인증 성공 → JWT 생성 → Cookie 저장
     *
     * Access Token  → httpOnly Cookie (accessToken)  → 1시간
     * Refresh Token → httpOnly Cookie (refreshToken) → 7일
     *
     * 토큰에 roles 포함 → DB 조회 없이 인가 처리 가능
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // JwtUtil로 토큰 생성 (roles 포함)
        String accessToken  = JwtUtil.generateAccessToken(principalDetails);
//        System.out.println("----"); //확인용
//        System.out.println(accessToken);
        String refreshToken = JwtUtil.generateRefreshToken(principalDetails);
//      System.out.println("----"); // 확인용
//      System.out.println(refreshToken);

        // httpOnly Cookie로 저장
        

        // 클라이언트에 로그인 성공 응답
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\":\"로그인 성공\"}");
    }

    /**
     * [Step 3] 인증 실패 → 401 응답
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"message\":\"로그인 실패: 아이디 또는 비밀번호를 확인하세요\"}");
    }
}