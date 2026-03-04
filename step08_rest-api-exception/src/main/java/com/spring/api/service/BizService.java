package com.spring.api.service;

import org.springframework.stereotype.Service;

@Service
public class BizService {
	
	public String bizAPIMethod() throws IllegalArgumentException {
		// 예외 발생
		String result = null;
		
		if(result == null) {
			throw new IllegalArgumentException("잘못된 요청");
		}
		
		return result;
	}
	
}
