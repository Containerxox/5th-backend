package com.spring.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.security.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//post "/login"
//로그인 성공 -> 시큐리티 세션(인증된 UserDetails) -> 시큐리티 context holder에 정보 저장
@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {
	
	private final User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(() -> user.getRole());
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	
	public String getEmail() {
		return user.getEmail();
	}
	
}