package board.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import board.dto.security.BoardDetails;
import board.service.UserService;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http
	) throws Exception {

		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth 
										.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
												.permitAll()
										.requestMatchers(
												"/",
												"/posts",
												"sign-up",
												"/error")
												.permitAll()
										.anyRequest().authenticated()
									)
			.formLogin(login -> login.defaultSuccessUrl("/posts",true))
			.logout(logout -> logout.logoutSuccessUrl("/"));
		
		return http.build();
	}
	
	// 인증된 데이터를 가져오는 로직(username, password 비교)
	@Bean 
	public UserDetailsService userDetailsService(UserService userService) {
		
		
		return username -> userService.getUser(username)
										.map(BoardDetails::from)
										.orElseThrow();
	}
	
	// 현재 db에는 암호화 x -> 이를 그대로 사용하기 위한 encoder 설정
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}
