package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Emp;

@Repository 
public interface EmpRepository extends JpaRepository<Emp, Integer>{

	Object findByEmpno(int empno);

	void deleteByEmpno(int empno);

	List<Emp> findByJobOrDept_Deptno(String job, Integer deptno);

	List<Emp> findBySalBetween(double sal1, double sal2);

	List<Emp> findByEnameContains(String string, Sort by);

	List<Emp> findByEnameContains(String string);

	List<Emp> findByDept_DeptnoIn(List<Integer> deptnos);

	@Query("SELECT e.ename FROM Emp e where e.dept.deptno = :deptno")
	List<String> findEnameByDept_Deptno(@Param("deptno") Integer deptno);


}
