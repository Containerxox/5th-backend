package com.spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.api.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
