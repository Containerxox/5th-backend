package com.spring.token.config.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Cookie 생성 및 추출 유틸리티
 */
public class CookieUtil {

    /**
     * httpOnly Cookie 생성
     * @param name     쿠키 이름
     * @param value    쿠키 값 (JWT 토큰)
     * @param maxAge   만료 시간 (초)
     * @param httpOnly JS 접근 차단 여부
     */
    public static Cookie createCookie(String name, String value, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        
        cookie.setHttpOnly(true); //JS 접근 차단
        cookie.setSecure(false); //https가 아닌 http를 사용
        cookie.setPath("/"); //루트에 대한 모든 경로로 cookie를 보낼거야.
        cookie.setMaxAge(maxAge); //쿠키 만료 시간
    	
    	
    	return cookie;
    }

    /**
     * Request에서 특정 이름의 Cookie 값 추출
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        return null;
    }

    /**
     * Cookie 삭제 (maxAge=0으로 덮어씌우기)
     */
    public static void deleteCookie(HttpServletResponse response, String name) {
    }
}