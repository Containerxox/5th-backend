package com.spring.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 회원가입 요청 DTO
 *
 * - role 필드 없음 → 클라이언트가 역할을 임의로 지정하는 Mass Assignment 공격 방지
 * - 엔티티(User)와 분리하여 외부 입력값과 DB 구조를 디커플링
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SignUpRequestDto {

    private String username;
    private String password;
    private String email;
}