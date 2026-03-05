package com.spring.api.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFilter1 implements Filter{
	
	public TestFilter1() {
		log.info("TestFilter1 ==============> 생성자");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("TestFilter1 ==============> pre-handle"); // 1번으로 실행
		
		chain.doFilter(request, response); // 2번으로 실행 (FileController 수행)
		
		log.info("TestFilter1 ==============> post-handle");  //3번으로 실행
	}
	
}