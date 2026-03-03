package com.spring.api.controller;

import org.springframework.stereotype.Controller;

import com.spring.api.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class StudentController {
	private final StudentService studentService;
}
