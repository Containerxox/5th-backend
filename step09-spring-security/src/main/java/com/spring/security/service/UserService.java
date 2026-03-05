package com.spring.security.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.security.dto.SignUpRequestDto;
import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void signUp(SignUpRequestDto dto) {
    	
    	
    	// 암호화 
//    	System.out.println("암호화 x : " + dto.getPassword());
//    	System.out.println("암호화 o : " + passwordEncoder.encode(dto.getPassword()));
    	
    	// dto -> entity 변환
    	User user = User.builder()
    					.username(dto.getUsername())
    					.email(dto.getEmail())
    					.password(passwordEncoder.encode(dto.getPassword())) // PW를 암호화
    					.role("ROLE_USER")
    					.build();
    	
        userRepository.save(user); // DB에 저장
    }
}