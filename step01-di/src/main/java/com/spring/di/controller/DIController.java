package com.spring.di.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.di.service.DIService;


@Component
public class DIController {
	
	private DIService diService;
	
	
	public DIController(DIService diService) {
		System.out.println("DIController 생성자");
		this.diService = diService;
		System.out.println("Controller ===> Service: "+ diService);
	}
	
//	private DIService service = new DIService(); //이제 필요 없다.
	
}
