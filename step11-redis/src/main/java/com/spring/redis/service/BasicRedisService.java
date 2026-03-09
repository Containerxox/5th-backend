package com.spring.redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicRedisService {
    
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;
    
    // 문자열 저장/조회
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
    
    // 객체 저장/조회
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    // TTL 설정
    public void setWithTTL(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }
    
    public Long getTTL(String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
    // 키 존재 여부 확인
    public Boolean exists(String key) {
        return stringRedisTemplate.hasKey(key);
    }
    
    // 키 삭제
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }
}