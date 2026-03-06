package com.spring.token.config.jwt;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.token.config.auth.PrincipalDetails;

/**
 * JWT 토큰 생성 및 검증 유틸리티
 * - 토큰에 권한(roles)까지 포함하여 DB 조회 없이 인가 처리 가능
 */
public class JwtUtil {

    /**
     * Access Token 생성
     * claim: uid, uname, roles (권한 포함)
     */
    public static String generateAccessToken(PrincipalDetails principalDetails) {
    
    	List<String> roles = principalDetails.getAuthorities()
    											.stream()
    											.map(auth -> auth.getAuthority())
    											.toList();
    	
    	return  JWT.create()
	        		.withSubject(principalDetails.getUsername())
	        		.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
	        		.withClaim("uid", principalDetails.getUsers().getId())
	        		.withClaim("uname", principalDetails.getUsers().getUsername())
	        		.withClaim("roles", roles)
	        		.sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    /**
     * Refresh Token 생성
     * claim: uname만 포함 (재발급용이므로 최소 정보만)
     */
    public static String generateRefreshToken(PrincipalDetails principalDetails) {
        return JWT.create()
        			.withSubject(principalDetails.getUsername())
        			.withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_TOKEN_EXPIRATION_TIME))
        			.withClaim("uname", principalDetails.getUsers().getUsername())
        			.sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    /**
     * 토큰 검증 및 디코딩
     * 만료 시 TokenExpiredException 발생
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
        			.build()
        			.verify(token);
    }

    /**
     * 토큰에서 username 추출
     */
    public static String getUsername(String token) {
        return verify(token).getClaim("uname").asString();
    }

    /**
     * 토큰에서 roles 추출
     */
    public static List<String> getRoles(String token) {
        return verify(token).getClaim("roles").asList(String.class);
    }

    /**
     * 토큰 만료 여부만 확인 (서명은 유효한 경우)
     */
    public static boolean isExpired(String token) {
    	try {
    		verify(token);
    		return false;
    	} catch (TokenExpiredException e) {
    		return true;
		}
    }
}
