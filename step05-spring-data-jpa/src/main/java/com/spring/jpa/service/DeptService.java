package com.spring.jpa.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Dept;
import com.spring.jpa.repository.DeptRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeptService { // Service는 "비즈니스 로직 처리" + "트랜잭션 처리" 담당
	
	private final DeptRepository deptRepository; //의존성 주입
	
	@Transactional //자동으로 begin() ~ 작업수행 ~ commit()이 됨. (따로 트랜잭션 객체 생성하지 않아도됨)
	public Object jpaTest() {
		Object result = null;
		
		// ▷ save
		Dept newDept = new Dept(99, "JPA", "SEOUL");
//		result = deptRepository.save(newDept);
		
		// ▷ findById
//		result = deptRepository.findById(99)
//								.orElseThrow(() ->  new NoSuchElementException("해당 부서 없음")); //존재하지 않으면 에러를 던짐
		
		// ▷ findAll
//		result = deptRepository.findAll(); // List 타입으로 리턴함.
		
		// ▷ update
//		Dept foundDept = deptRepository.findById(99)
//									.orElseThrow(() ->  new NoSuchElementException("해당 부서 없음"));
//		System.out.println(foundDept.getLoc());
//		foundDept.setLoc("BUSAN");
//		System.out.println(foundDept.getLoc());
		
			
		// ▷ delete
//		deptRepository.deleteById(99);
		
		
		// ▶ 쿼리 메소드
		// loc으로 find하기
//		result = deptRepository.findByLoc("BOSTON");
		
		// find : loc
//		result = deptRepository.findTop1ByLoc("BOSTON");
		
		// find : dname or loc
//		result = deptRepository.findByDnameOrLoc("ACCOUNTING", "BOSTON");
		
		// find : deptno 10 between 30
//		result = deptRepository.findByDeptnoBetween(10,30);
		
		// find : dname LIKE 'O' -> '%O%'
//		result = deptRepository.findByDnameLike("O");
//		result = deptRepository.findByDnameContains("O");		

		
		// find : dname LIKE '%O%'  ->   Order By Deptno desc;
//		result = deptRepository.findByDnameContainsOrderByDeptnoDesc("O");	
		
		
		// Sort 객체 이용하여
		// find : dname LIKE '%O%'  ->   Order By Deptno desc;
//		result = deptRepository.findByDnameContains("O", Sort.by(Order.desc("deptno")));	
		
		// Page 객체 이용
		// find : dname LIKE '%A%'  ->  1페이지당 2개씩 출력
//		result = deptRepository.findByDnameContains("A", PageRequest.of(0,2));	
		
		
		// find : deptno IN (20, 30) 
//		List<Integer> deptnos = Arrays.asList(20,30);
//		result = deptRepository.findByDeptnoIn(deptnos);
		
		// find : only dnames  (dname만 출력해주고 싶어) => JPQL 사용하자.
//		result = deptRepository.findAllDnames();
		
		
		// delete : deleteByLoc(String loc);
		// delete : 50 이상 부서는 삭제 : deleteByDeptnoGreaterThan(Integer deptno);
		
		
		
		
		return result;
	}
	
}
