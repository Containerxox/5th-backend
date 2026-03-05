package com.spring.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Step08RestApiExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step08RestApiExceptionApplication.class, args);
	}

}
