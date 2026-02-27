package com.spring.mvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Size(min=2,max=10)
	private String sname;
	
	// 1학년 ~ 4학년
	@Pattern(regexp = "^[1-4]학년")
	private String grade;
	
}
