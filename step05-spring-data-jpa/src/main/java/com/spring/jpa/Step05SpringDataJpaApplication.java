package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Step05SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step05SpringDataJpaApplication.class, args);
	}

}
 