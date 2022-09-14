package com.learning.springboot.springboote2e.jpaDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.springboot.springboote2e.jpaModel.UserB;

public interface UserBRepository extends JpaRepository<UserB, Integer> {

	// here findBy<Content> content should be the name of the any field of UserB class
	// in CamelCase.
	public List<UserB> findByProfession(String prof);
}
