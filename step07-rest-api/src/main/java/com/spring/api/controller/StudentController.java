package com.spring.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value="/students")
	public ResponseEntity<ApiResponseDto> insertStudent(@RequestBody Student student){
		
		ApiResponseDto apiResponseDto = new ApiResponseDto<>();
		
		studentService.insertStudent(student);
		
		apiResponseDto = ApiResponseDto.builder()
										.statusCode(HttpStatus.CREATED.value())
										.httpStatus(HttpStatus.CREATED)
										.message("학생 생성 성공")
										.data(student)
										.build();
		
		return new ResponseEntity<>(apiResponseDto, apiResponseDto.getHttpStatus());
		
	}
	
	@PutMapping(value="/students/{sid}")
	public ResponseEntity<ApiResponseDto> updateStudent(@PathVariable Integer sid,@RequestBody Student student){
		
		ApiResponseDto apiResponseDto = new ApiResponseDto<>();
		
		studentService.updateStudent(sid, student);
		
		apiResponseDto = ApiResponseDto.builder()
										.statusCode(HttpStatus.OK.value())
										.httpStatus(HttpStatus.OK)
										.message("학생 수정 성공")
										.data(student)
										.build();
		
		return new ResponseEntity<>(apiResponseDto, apiResponseDto.getHttpStatus());
		
	}
	
	@DeleteMapping(value="/students/{sid}")
	public ResponseEntity<ApiResponseDto> deleteStudent(@PathVariable Integer sid){
		
		ApiResponseDto apiResponseDto = new ApiResponseDto<>();
		
		studentService.deleteStudent(sid);
		
		apiResponseDto = ApiResponseDto.builder()
										.statusCode(HttpStatus.OK.value())
										.httpStatus(HttpStatus.OK)
										.message("학생 삭제 성공")
										.build();
		
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
