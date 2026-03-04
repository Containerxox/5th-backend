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
		
		
//		return studentRepository.findById(sid)
//								.orElseThrow(() -> new NoSuchElementException("학생 존재 X"));
		
		return studentRepository.findById(sid)
								.orElse(null);
		
	}
	
	@Transactional
	public Student insertStudent(Student student) {
		studentRepository.save(student);
		
		return student;
	}

	@Transactional
	public Student updateStudent(Integer sid, Student student) {
		Student existStudent = studentRepository.findById(sid)
												.orElse(null);
		
		if(existStudent != null) {
			existStudent.setSname(student.getSname());
		}
		
		return existStudent;
		
	}

	@Transactional
	public void deleteStudent(Integer sid) {
		studentRepository.deleteById(sid);
		
	}
	
}
