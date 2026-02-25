package com.spring.ioc;

import org.springframework.stereotype.Component;

@Component
public class IoCContainer {
	public IoCContainer() {
		System.out.println("IoCContainer 생성자");
	}
}
