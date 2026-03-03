package com.spring.jpa.service;

import java.util.NoSuchElementException;

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
		result = deptRepository.findAll(); // List 타입으로 리턴함.
		
		return result;
	}
	
}
