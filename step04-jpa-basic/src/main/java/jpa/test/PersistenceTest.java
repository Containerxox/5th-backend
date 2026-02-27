package jpa.test;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Student;

public class PersistenceTest {
	public static void main(String[] args) {
		// EMF
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence"); // 공장 객체가 emf에 들어옴.
		
		// EM
		EntityManager em = emf.createEntityManager();
		
		// TX : begin() ~ 작업 수행 ~  commit()
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// insert
		// 20241001, "DEV"
		// 20241002, "DEVOPS"
//		Student stu1 = new Student(20241001, "DEV");
//		Student stu2 = new Student(20241002, "DEVOPS");
//		em.persist(stu1);
//		em.persist(stu2);
		
		
		// select
		// sid로 검색 20241001
//		Student foundStu1 = em.find(Student.class, 20241001);
//		System.out.println(foundStu1);
		
		// selectAll (em 매니저는 findAll이란 메서드가 없음)
		// ▷ JPQL (자바의 jpa에서 사용하는 객체지향 쿼리)를 사용해야 함
		// SELECT * FROM student;  -> 이건 에러 발생함.
		// SELECT s FROM Student s;  -> JPQL 적용 (엔티티 클래스 객체로 맵핑하기 때문에 대문자 Student를 적어줘야 함 / 반드시 별칭 적어줘야 함)
		// 예시) SELECT s.sname FROM Student s; 
//		List<String> names = em.createQuery("SELECT s.sname FROM Student s", String.class)
//								.getResultList(); 
//		List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class)
//									.getResultList(); 
		
		// SELECT * FROM student;
//		students = em.createNativeQuery("SELECT * FROM student", Student.class)
//					.getResultList();
		
//		System.out.println(students);
		
		// JPQL을 굳이 사용해야 하느냐?
		// mysql과 orcle db의 문법이 서로 다르다.
		// createNativeQuery를 쓸 경우, 만약 사용하는 DB가 바뀌면 사용하는 DB에 맞춰서 쿼리를 변경해줘야 하는 번거로움 존재.
		// createQuery()는 자동으로 맵핑하고 쿼리를 만들어주므로, 사용하는 DB가 바뀌더라도 변경할 부분이 없다. 
		// 따라서 JPQL이 더 유용하다!
		
		// 하지만, JPQL은 복잡한 쿼리를 실행하는데에는 한계가 있다.
		// DB의 고유한 함수를 사용하는 경우, 성능상의 튜닝하는 경우에는 createNativeQuery()가 더 낫다.
		
		
		// update 
		// DEVOPS(20241001) 이름을 IT 변경
		// 1) find -> 1차 캐시에 해당 객체에 대한 스냅샷 저장
//		Student foundStu2 = em.find(Student.class, 20241002);
//		System.out.println(foundStu2.getSname());
		
		// 2) 값을 변경
//		foundStu2.setSname("IT");
//		System.out.println(foundStu2.getSname());
		
		// 3) commit 시점에서 스냅샷과 현재 객체 상태와 비교 -> 변경사항 있다면 update 실행됨.
		// => 이게 바로 '변경 감지(Dirty Checking)`이라는 특징!
		
		
		
		// delete
		// 20241002 학생 삭제
//		Student foundStu2 = em.find(Student.class, 20241002);
//		em.remove(foundStu2); // 영속성 영역에서 해당 entity 객체를 삭제
		
		
		// ▶ 영속성 컨텍스트 (Persistence Context) 특징
		// ▷ 1차 캐시
		Student cashedStu1 = em.find(Student.class, 20241001);
		System.out.println(cashedStu1);
		
		Student cashedStu2 = em.find(Student.class, 20241001);
		System.out.println(cashedStu2);
		// 같은 쿼리를 여러번 요청하는 경우
		// context에 해당 정보를 담아두기 때문에
		// DB로 다시 갈 필요 없이 context에서 뽑아옴
		// 따라서 위에서는 select가 한번만 실행됨.
		
		
		// ▷ 쓰기 지연
		// 여러가지 작업을 한번에 실행
		// 20241003, 'CLOUD'
		// 20241004, 'JPA'
		// COMMIT이 없다고 가정 -> 작업 X
		
//		Student stu3 = new Student(20241003, "CLOUD");
//		Student stu4 = new Student(20241004, "JPA");
//		em.persist(stu3);
//		em.persist(stu4);
		
		// commit이 될 때, 위 2개에 대한 작업 내역(insert)이 실행됨!
		
		
		
		// ▷ 변경 감지
		// 커밋하는 경우 DB에 결과를 바로 반영하지 않고 1차 캐시 영역의 결과와 비교하여 변경사항 발생시 DB 반영
		
		
		
		
		tx.commit();
	}
}
