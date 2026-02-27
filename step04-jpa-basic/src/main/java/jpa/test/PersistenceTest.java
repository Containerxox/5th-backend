package jpa.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistenceTest {
	public static void main(String[] args) {
		// EMF
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence"); // 공장 객체가 emf에 들어옴.
		System.out.println(emf);
		
		// EM
		EntityManager em = emf.createEntityManager();
		
		// TX : begin() ~ 작업 수행 ~  commit()
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// insert
		// 20241001, "DEV"
		// 20241002, "DEVOPS"
		
		tx.commit();
		
		// select
		// sid로 검색 20241001
		
		// update 
		// DEVOPS 이름을 IT 변경
		
		// delete
		// 20241002 학생 삭제
		
	}
}
