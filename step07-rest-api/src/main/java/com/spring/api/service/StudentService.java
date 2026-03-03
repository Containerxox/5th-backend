package com.spring.api.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.spring.api.entity.Student;
import com.spring.api.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService {
	private final StudentRepository studentRepository;

	@Transactional
	public Student getStudent(Integer sid) {
		
		
		return studentRepository.findById(sid)
								.orElseThrow(() -> new NoSuchElementException("학생 존재 X"));
		
	}
	
}
