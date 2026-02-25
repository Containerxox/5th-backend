package com.spring.di.service;

import org.springframework.stereotype.Component;

@Component("specialDIService")
public class SpecialDIService extends DIService{
	public SpecialDIService() {
		System.out.println("SpecialDIService 생성자");
	}
}
