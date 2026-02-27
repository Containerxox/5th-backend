package com.spring.mvc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

	public enum Grade {
		JUNIOR, SENIOR;
	}

	private Integer sid;
	private String sname;
	private Grade grade;

	@Builder
	public Student(Integer sid, String sname, Grade grade) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.grade = grade;
	}

}
