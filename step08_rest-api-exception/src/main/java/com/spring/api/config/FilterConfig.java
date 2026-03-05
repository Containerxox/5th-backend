package com.spring.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.api.filter.TestFilter1;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<Filter> testFilter1() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
		
		bean.setFilter(new TestFilter1());
		bean.setOrder(1);
		bean.addUrlPatterns("/*");
		
		return bean;
	}
}