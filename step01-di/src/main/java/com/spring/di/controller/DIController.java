package com.spring.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.di.service.DIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DIController {
	
	// 필드 주입 v1
	@Autowired
	private DIService diService;
	
	// 생성자 주입 v2
	private final DIService disService;
	
//	// 생성자 주입 v1
//	private DIService diService;
//	public DIController(DIService diService) {
//		System.out.println("DIController 생성자");
//		this.diService = diService;
//		System.out.println("Controller ===> Service: "+ diService); //DIService의 객체정보를 출력
//	}
	
	// 기존 방식
//	private DIService service = new DIService(); //이제 필요 없다.
	
}
