package com.spring.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.dto.ApiResponseDto;
import com.spring.api.entity.Student;
import com.spring.api.service.StudentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController  // @Controller + @ResponseBody
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping(value="/students/{sid}")
	public ResponseEntity<ApiResponseDto> getStudent(@PathVariable Integer sid) {
		
		ApiResponseDto apiResponseDto = new ApiResponseDto<>();
		
		Student student = studentService.getStudent(sid);
		
		if(student != null) {
			apiResponseDto = ApiResponseDto.builder()
											.statusCode(HttpStatus.OK.value())
											.httpStatus(HttpStatus.OK)
											.message("학생 조회 성공")
											.data(student)
											.build();
		}else {
			apiResponseDto = ApiResponseDto.builder()
					.statusCode(HttpStatus.NOT_FOUND.value())
					.httpStatus(HttpStatus.NOT_FOUND)
					.message("학생 조회 실패")
					.data(student) // 생략 가능
					.build();
		}
		
		return new ResponseEntity<>(apiResponseDto, apiResponseDto.getHttpStatus());
	}
	
	
	
	
	
	
	
//	@GetMapping(value="/students/{sid}")
//	public Student getStudent(@PathVariable Integer sid) { 
//		Student student = null;
//		
//		student = studentService.getStudent(sid);
//		
//		return student;
//	}
	
	
	
}
