package com.spring.token.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.token.entity.Users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Spring Security 인증 주체 (UserDetails 구현)
 * - AuthenticationManager가 인증 후 반환하는 객체
 * - Users 엔티티를 감싸서 Spring Security가 사용할 수 있는 형태로 변환
 */
@Getter
@RequiredArgsConstructor
public class PrincipalDetails implements UserDetails {

    private final Users users;

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }

    /**
     * DB의 roles 필드(콤마 구분)를 GrantedAuthority 컬렉션으로 변환
     * 예: "ROLE_USER,ROLE_MANAGER" → [ROLE_USER, ROLE_MANAGER]
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        users.getRoleList().forEach(role ->
                authorities.add(() -> role)
        );
        return authorities;
    }
}
