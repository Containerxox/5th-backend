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
		// JPA에서 연관된 엔티티를 조회할 때 쿼리가 불필요하게 여러 번 실행되는 성능 문제
		// 한 번의 조회 쿼리(1번)로 N개의 엔티티를 가져온 후,
		// 각 엔티티의 연관 데이터를 조회하기 위해 추가로 N번의 쿼리가 실행되는 현상
		// 총 실행 쿼리 = 1 + N
		
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
		// 페치조인(이너조인)은 학생이 있는 강의만 조회되고, 학생이 없는 강의는 결과에서 사라짐.
		// 학생이 존재하지 않는 강좌라면, null이 되버린다. 따라서 outer 조인을 해줘야 겠지 
		// fetch join (외부 조인) -> 학생이 없어도 강좌 조회되도록 함!
//		result = lectureRepository.findAllWithOuterFetchJoin();
		
		
		// 2) Entity Graph
//		result = lectureRepository.findAllWithEntityGraph();
		
		
		// 1), 2) 페이지 처리가 불가능 ↓ 해결 방안 
		// 3) @BatchSize 사용하기 (Lecture.java -> students 필드에 해당 어노테이션 적어주기) + FetchType.EAGER
		result = lectureRepository.findAll();
		// => Hibernate가 한 번에 여러 lecture의 student를 가져온다.
		// Hibernate가 lecture.getStudents() 접근
		// lecture 여러 개의 ID를 모아서 IN 쿼리로 students 조회
		// 즉, select * from student where lid in (?, ?, ?, ...)
		
		
		/*
		 * 페이징 필요?
		 * 		yes -> @BatchSize
		 * 		no  -> 데이터 규모?
		 * 					소규모, 단순   -> fetch join
		 * 						대규모    -> @BatchSize
		 * 						동적 처리  -> EntityGraph
		 * 
		 * N + 1 해결 주요 목적?
		 * => 쿼리를 최소화
		 * 
		 * 
		 * 만약 강좌 25개, batchSize = 10
		 * 발생하는 쿼리는 ? 
		 * 1: select * from lecture
		 * 2: where lid In (1,2,3,4,5,6,7,8,9,10)
		 * 3: where lid In (11,12,13,14,15,16,17,18,19,20)
		 * 3: where lid In (21,22,23,24,25)
		 * 
		 */
		
		// ▶ OSIV
		// spring.jpa.open-in-view= 
		// true: 영속성 컨텍스트 controller까지 유지
		// false: controller 에서는 영속성 컨텍스트 없음 (Service까지만 영속성 컨텍스트 유지)
		
		

		
		
		
		
		
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