package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.GameUser;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long>{

}