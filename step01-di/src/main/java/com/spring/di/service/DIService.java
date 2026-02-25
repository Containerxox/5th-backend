package com.spring.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DIService {
	
	public DIService() {
		System.out.println("DIService 생성자");
	}
	
	@Autowired
	private ObjectMapper objectMapper;
}
