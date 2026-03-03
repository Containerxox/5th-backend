package com.spring.jpa.service;

import java.time.LocalDate;

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
		Emp newEmp = Emp.builder()
		        .empno(9999)
		        .ename("CLOUD")
		        .job("MANAGER")
		        .mgr(7839)
		        .hiredate(LocalDate.of(2026, 3, 3))
		        .sal(3000.0)
		        .comm(500.0)
		        .dept(new Dept(10, "ACCOUNTING", "NEW YORK"))
		        .build();
		
		result = empRepository.save(newEmp);
		

		// 2. ID(9999)로 조회

		// 3. ID(9999)로 삭제

		// 4. job OR dept로 조회("MANAGER", 20)
		// result = repository.findByJobOrDept_Deptno("MANAGER", 20);

		// 5. 급여 범위(1000.0, 3000.0) 조회

		// 6. 이름 포함("A") + 정렬(empno -> desc)

		// 7. 이름 포함 + Sort 파라미터 (6번내용을 적용)

		// 8. 페이징 처리(0, 3)

		// 9. 특정 부서(10, 20, 30) 사원 목록 (In절)

		// 10. @Query - 부서번호로 사원 이름 목록 조회
		
		
		return result;
	}
}
