package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Lecture;
import com.spring.jpa.repository.LectureRepository;
import com.spring.jpa.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApplyService {
	
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	
	@Transactional
	public Object jpaApply() {
		Object result = null;
		// 비즈니스 로직 : 강의 수강하는 학생들의 이름 출력하려면?
		/*
		 [
		    "HTML",
		    "CSS",
		    "JAVASCRIPT",
		    "SERVER",
		    "SERVLET",
		    "JSP",
		    "DI",
		    "MVC",
		    "JPA"
		]
		 */

		// N + 1 해결

		result = lectureRepository.findAll();
		
		return result;
	}
	
	@Transactional
	public List<Lecture> osivApply() {
		return lectureRepository.findAll();
	}
	
	@Transactional
	public Object cascadeApply() {
		
//		Lecture lecture = new Lecture();
//		lecture.setLname("API");
//
//		Student stu1 = new Student();
//		stu1.setSid(20244001);
//		stu1.setSname("REST");
//		stu1.setLecture(lecture);	
//	
//		Student stu2 = new Student();
//		stu2.setSid(20244002);
//		stu2.setSname("API");
//		stu2.setLecture(lecture);
//		
//		lecture.getStudents().add(stu1);
//		lecture.getStudents().add(stu2);
		
		return null;
	}
	
}