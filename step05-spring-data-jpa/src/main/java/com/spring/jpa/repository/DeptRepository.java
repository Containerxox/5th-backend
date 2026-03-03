package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Dept;

// 리포지토리에서는 DB의 CRUD 담당

@Repository   
public interface DeptRepository extends JpaRepository<Dept, Integer>{ // Entity 타입, Id 타입 적으면됨.

	Object findByLoc(String loc);

	Object findTop1ByLoc(String loc);

	List<Dept> findByDnameOrLoc(String dname, String loc);

	List<Dept> findByDeptnoBetween(int deptno1, int deptno2);

	List<Dept> findByDnameLike(String string);

	List<Dept> findByDnameContains(String string);

	List<Dept> findByDnameContainsOrderByDeptnoDesc(String string);

	List<Dept> findByDnameContains(String string, Sort by);

	Page<Dept> findByDnameContains(String string, PageRequest of);

	List<Dept> findByDeptnoIn(List<Integer> deptnos);

	@Query("SELECT d.dname FROM Dept d")
	List<String> findAllDnames(); // 내가 직접 만든 메서드!
	
	@Query("SELECT d.deptno FROM Dept d WEHRE d.loc = :loc")
	List<Integer> findAllDeptnoByLoc(@Param("loc") String loc); 
	
}
