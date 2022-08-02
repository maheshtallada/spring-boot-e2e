package com.learning.springboot.springboote2e.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springboot.springboote2e.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer>{
	
}
