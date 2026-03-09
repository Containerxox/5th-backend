package com.spring.token.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/*
 * Redis 기반 토큰 관리 서비스
 * 
 * - key 구조
 * RT: {username} -> TTL : 7일
 * BL: {accessToken} -> 로그아웃 시, 더이상 해당 토큰 활용 불가하도록 처리, TTL : AT 만료 시간
 */

@RequiredArgsConstructor
@Service
public class TokenRedisService {
	
	private final RedisTemplate<String, String> redisTemplate;
	
	private static final String RT_PREFIX = "RT:";
	private static final String BL_PREFIX = "BL:";
	
	
	//  --- Refresh Token ---
	// 로그인 성공 시, RT를 redis에 저장
	public void saveRefreshToken(String username, String refreshToken, long ttlSeconds) {
		 	redisTemplate.opsForValue().set(
		 			RT_PREFIX + username, 
		 			refreshToken,
		 			ttlSeconds, 
		 			TimeUnit.SECONDS
		 	);
		 	
		 	
	}
	
	
	// 로그아웃 시, RT 삭제
	public void deleteRefreshToke(String username) {
		redisTemplate.delete(RT_PREFIX + username);
	}
	
	
	//  --- Black List : (로그아웃하는)Access Token ---
	// 로그아웃 시, AT -> 블랙리스트 등록
	public void addToBlackList(String accessToken, long remainingTtlSeconds) {
		redisTemplate.opsForValue().set(
								BL_PREFIX + accessToken,
								"logout",
								remainingTtlSeconds,
								TimeUnit.SECONDS);
	}
	
	
	
}
