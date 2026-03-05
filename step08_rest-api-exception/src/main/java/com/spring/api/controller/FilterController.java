package com.spring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilterController {

	@GetMapping("/filter")
	public String filterTest() {
		
		System.out.println("FilterController");
		
		
		return "filter";
	}
	
	
}
