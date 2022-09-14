package com.learning.springboot.springboote2e.jpaService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springboot.springboote2e.jpaDao.UserBRepository;
import com.learning.springboot.springboote2e.jpaModel.UserB;

@Service
public class UserBService {

	@Autowired
	private UserBRepository repository;
	
	
	// this method gets called before initializing the necessary bean
	@PostConstruct
	public void initDB() {
		List<UserB> userBs = new ArrayList<>();
		
		userBs.add(new UserB(111, "x", "IT", 23));
		userBs.add(new UserB(675, "y", "IT", 24));
		userBs.add(new UserB(432, "z", "CIVIL", 26));
		userBs.add(new UserB(88, "p", "IT", 23));
		userBs.add(new UserB(765, "q", "GOVT", 20));
		
		repository.saveAll(userBs);
		
	}
	
	public List<UserB> getUserBs() {
		return repository.findAll();
	}
	
	public List<UserB> getUserBswithProf(String prof) {
		return repository.findByProfession(prof);
	}
}
