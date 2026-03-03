package com.spring.api.service;

import org.springframework.stereotype.Service;

import com.spring.api.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentService {
	private final StudentRepository studentRepository;

}
