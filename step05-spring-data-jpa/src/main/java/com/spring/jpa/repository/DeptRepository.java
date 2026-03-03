package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Dept;

// 리포지토리에서는 DB의 CRUD 담당

@Repository   
public interface DeptRepository extends JpaRepository<Dept, Integer>{ // Entity 타입, Id 타입 적으면됨.
	
	
}
