package com.spring.security.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//인증 실제 진행 -> loadUserByUsername
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	// 시큐리티 내부에서 실제 인증 --> 성공적이라면? return UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user != null) {
			return new PrincipalDetails(user);
		}
		
		return null;
	}
	

}