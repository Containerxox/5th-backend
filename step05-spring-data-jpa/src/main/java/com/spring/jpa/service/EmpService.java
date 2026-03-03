package com.spring.jpa.service;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Dept;
import com.spring.jpa.entity.Emp;
import com.spring.jpa.repository.EmpRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpService {
	private final EmpRepository empRepository;

	@Transactional
	public Object empTest() {
		Object result = null;
		
		// 1. 저장
//		Emp newEmp = Emp.builder()
//		        .empno(9999)
//		        .ename("CLOUD")
//		        .job("MANAGER")
//		        .mgr(7839)
//		        .hiredate(LocalDate.of(2026, 3, 3))
//		        .sal(3000.0)
//		        .comm(500.0)
//		        .dept(new Dept(10, "ACCOUNTING", "NEW YORK"))
//		        .build();
//		
//		result = empRepository.save(newEmp);
		

		// 2. ID(9999)로 조회
		// 방법1) 기본 메서드 이용
//		result = empRepository.findById(9999)
//								.orElseThrow(() -> new NoSuchElementException("해당 사원 정보 없음"));
		// 방법2) 쿼리 메서드 이용
//		result = empRepository.findByEmpno(9999);
		
		
		// 3. ID(9999)로 삭제
		// 방법1) 기본 메서드 이용
//		empRepository.deleteById(9999);

		// 방법2) 쿼리 메서드 이용
//		empRepository.deleteByEmpno(9999);
		
		
		// 4. job OR dept로 조회("MANAGER", 20)
//		result = empRepository.findByJobOrDept_Deptno("MANAGER", 20);
		

		// 5. 급여 범위(1000.0, 3000.0) 조회
//		result = empRepository.findBySalBetween(1000.0,3000.0);

		// 6. 이름 포함("A") + 정렬(empno -> desc)
//		result = empRepository.findByEnameContainsOrderByEmpnoDesc("A");
		
		// 7. 이름 포함 + Sort 파라미터 (6번내용을 적용)
//		result = empRepository.findByEnameContains("A", Sort.by(Order.desc("empno")));
		
		// 8. 페이징 처리(0, 3)
//		Pageable pageable = PageRequest.of(0, 3);
//		Page<Emp> page = empRepository.findAll(pageable);
//		return page;

		// 9. 특정 부서(10, 20, 30) 사원 목록 (In절)
//		List<Integer> deptnos = Arrays.asList(10,20,30);
//		result = empRepository.findByDept_DeptnoIn(deptnos);

		// 10. @Query - 부서번호로 사원 이름 목록 조회
		result = empRepository.findEnameByDept_Deptno(10);
		
		return result;
	}
}
