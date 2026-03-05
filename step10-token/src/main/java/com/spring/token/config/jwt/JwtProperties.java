package com.spring.token.config.jwt;

public interface JwtProperties {

    String SECRET = "a-string-secret-at-least-256-bits-long"; //

    // Access Token: 1시간
    int ACCESS_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;

    // Refresh Token: 7일
    int REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    String TOKEN_PREFIX   = "Bearer ";
    String HEADER_STRING  = "Authorization";

    // Cookie 이름
    String ACCESS_TOKEN_COOKIE  = "accessToken";
    String REFRESH_TOKEN_COOKIE = "refreshToken";
}