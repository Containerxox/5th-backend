package com.spring.token.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.token.config.jwt.JwtAuthenticationFilter;
import com.spring.token.config.jwt.JwtAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	private final CorsConfig corsConfig;
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	// 필터 체인
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
    												AuthenticationManager authenticationManager) throws Exception {
		
    	http
    		.addFilter(corsConfig.corsFilter())
    		.addFilter(new JwtAuthenticationFilter(authenticationManager)) // 필터 등록
    		.addFilter(new JwtAuthorizationFilter(authenticationManager)); // 필터 등록
    	
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session
											.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable);
		
		http
			.authorizeHttpRequests(authorize -> authorize
					
					// 공개 페이지
					.requestMatchers("/","/index","/signup","/about","/access-denied").permitAll()
					// 공개 API
					.requestMatchers("/api/v1/signup", "/api/v1/auth/**").permitAll()
					// 정적 리소스 접근 허용
					.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
					
					// USER 이상
					.requestMatchers("/user", "/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
					// MANAGER 이상
					.requestMatchers("/manager", "/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
					// ADMIN 이상
					.requestMatchers("/admin", "/api/v1/admin/**").hasAnyRole("ADMIN")
					
					// 그 외 모든 요청은 인증 처리가 완료되어야만 사용 가능
					.anyRequest().authenticated()
						
		);
		
			
		return http.build();
	}
    
    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

