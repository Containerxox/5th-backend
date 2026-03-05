package com.spring.security.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data // 편의상 setter 적용
@Entity
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid; 		 // 유저 pk
	
	private String username; // 유저 id
	
	private String password; // 유저 pw
	
	private String email;	 // 유저 email
	
	private String role; 	 // 유저 역할 ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
	
	@CreationTimestamp
	private Timestamp createDate; // 유저 생성(가입) 시간
	
	@Builder
	public User(int uid, String username, String password, String email, String role, Timestamp createDate) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.createDate = createDate;
	}
	
}
