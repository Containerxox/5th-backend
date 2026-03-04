package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>{
	
}
