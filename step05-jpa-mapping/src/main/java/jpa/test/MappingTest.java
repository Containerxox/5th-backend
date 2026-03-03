package jpa.test;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Lecture;
import jpa.entity.Student;

public class MappingTest {
	public static void method(EntityManager em) {
		// insert
		// Tech 강좌
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);
		
		// 20242001, "jpa", Tech 강좌
//		Student stu = new Student();
//		stu.setLid(1L);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);
		
		// select 
		// 학생이 참가하고 있는 수업의 정보 출력?
		// 1) 학생 찾기 -> lid 가져오기 
		// 2) 수업 테이블에서 결과 반환받기
//		Long lid = em.find(Student.class, 20242001).getLid();
//		Lecture foundLec = em.find(Lecture.class, lid);
//		System.out.println(foundLec);
		
		// 패러다임 불일치 해결 (위처럼 find 해줄 필요 없음)
		// @JoinColumn + @ManyToOne  ->  create 모드 변경후, foriegn키 변경된 것 확인 후에 none모드로 변경
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);
//		
//		Student stu = new Student();
//		stu.setLecture(lec);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);
		
//		Lecture foundLecture = em.find(Student.class, 20242001).getLecture();
//		System.out.println(foundLecture);
		
		
		// 강좌에 참여하고 있는 학생의 정보 출력?  // 양방향 참조 
//		List<Student> students = em.find(Lecture.class, 1L).getStudents();
//		System.out.println(students);
		
		
		// SQL: SELECT * FROM student WHERE lid=?;
		// JPQL로 바꿔보자.
		// 1) 이름 기반 파라미터 JPQL (권장)(가독성 높음)
		// :변수명 -> setParameter("변수명", 값);
//		String namedJpql = "select s from Student s where s.lecture.lid = :lid";
//		List<Student> students = em.createQuery(namedJpql, Student.class)
//									.setParameter("lid", 1L)
//									.getResultList();
////		
////		System.out.println(students);
		
		// 2) 위치 기반 파라미터 JPQL
//		String PositionalJpql = "select s from Student s where s.lecture.lid = ?1";
//		students = em.createQuery(PositionalJpql, Student.class)
//					.setParameter(1, 1L)
//					.getResultList();
//		
////		System.out.println(students);
		
// ==========위는 Student와 Lecture를 가지고 한 실습 (양방향 참조) =====================================================
// ====================================================================
// ============== 아래는 Custormer와 Product를 가지고 한 실습 (다대다) ============================================
		
		
			
	}
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	
		try {
			method(em);
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}

