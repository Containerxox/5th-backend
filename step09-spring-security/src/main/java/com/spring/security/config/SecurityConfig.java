package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	// 필터 체인
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable());
		
		http
			.formLogin(form -> 
								form
									.loginPage("/login") // GET요청은 /login (뷰) 을 사용
									.loginProcessingUrl("/login") //POST요청은 컨트롤러가 아닌 Spring Security 필터가 가로챔 -> Security 내부에서 UserDetailsService.loadUserByusername(useranme) 호출
									.defaultSuccessUrl("/")); // 로그인 성공 시, /로 이동.
		
		return http.build();
	}
    
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}