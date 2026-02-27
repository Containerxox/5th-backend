package com.spring.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Student {
	private Integer sid;
	
	@NotBlank(message = "이름은 필수값이어야 합니다.")
	private String sname;
	private String grade;
	
}
