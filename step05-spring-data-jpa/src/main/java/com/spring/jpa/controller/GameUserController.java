package com.spring.jpa.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.jpa.entity.GameUser;
import com.spring.jpa.repository.GameUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GameUserController {
	
	private final GameUserRepository gameUserRepository;
	
	@PostMapping(value = "/sign-up")
	@ResponseBody // 이 메서드의 반환값을 JSON 형태로 HTTP 응답 바디에 직접 넣기
	public GameUser signUp(@RequestBody GameUser gameUser) {  //HTTP 요청 바디(JSON)를 Java 객체로 변환
		return gameUserRepository.save(gameUser);
	}
	
	@PostMapping(value = "/users/{gid}")
	@ResponseBody
	@Transactional
	public GameUser updateProfile(@PathVariable Long gid,
								@RequestBody GameUser gameUser) {
		
		GameUser foundUser = gameUserRepository.findById(gid)
												.orElseThrow();
		
		foundUser.setGname(gameUser.getGname());
		
		return foundUser;
	}
	
}