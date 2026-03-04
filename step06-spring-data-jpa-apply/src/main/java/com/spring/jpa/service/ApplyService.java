package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Lecture;
import com.spring.jpa.repository.LectureRepository;
import com.spring.jpa.repository.StudentRepository;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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

		// ▶ N + 1 해결
		// ▷ @OneToMany(mappedBy = "lecture", fetch=FetchType.EAGER)
		// select lecture -> select student ==> join  (N+1을 해결하는 궁극적인 방법은 아님) (하나를 가져오는건 해결되지만, 여러개를 가져오면 N+1이 발생함)
//		result = lectureRepository.findById(1L);
		
//		result = lectureRepository.findAll(); 
		// N+1 발생: 의도하지 않은 쿼리인 lecture 관련 쿼리가 여러번 발생함.
		//  fetch=FetchType.EAGER을 붙여줬지만 N+1이 해결되진 않는다!
		
		// 1000개의 lecture가 가정 =>1001개 쿼리 실행됨. (효율성 떨어짐)
		// fetch join을 하면 쿼리 하나만으로 값을 가져올수있다.
		
		
		// ▷ N+1 해결 방안
		// 1) fetch join (이너 조인) 
//		result = lectureRepository.findAllWithFetchJoin();
		// 페치조인은 기본값이 학생이 존재하는 강좌만 불러온다.
		// 학생이 존재하지 않는 강좌라면, null이 되버린다. 따라서 outer 조인을 해줘야 겠지
		result = lectureRepository.findAllWithOuterFetchJoin();
		
		
		
		
		
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