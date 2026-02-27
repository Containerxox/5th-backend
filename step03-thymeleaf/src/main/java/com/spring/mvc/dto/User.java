package com.spring.mvc.dto;

import com.spring.mvc.dto.Student.Grade;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
	private String name;
	private String email;
	private String message;

	@Builder
	public User(String name, String email, String message) {
		super();
		this.name = name;
		this.email = email;
		this.message = message;
	}
}
