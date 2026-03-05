package com.spring.token.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.token.entity.Users;
import com.spring.token.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security가 로그인 시 호출하는 서비스
 * - AuthenticationManager → loadUserByUsername() → DB 조회 → PrincipalDetails 반환
 * - 이후 BCrypt 비밀번호 검증은 Spring Security가 자동 처리
 */
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }
        return new PrincipalDetails(user);
    }
}
