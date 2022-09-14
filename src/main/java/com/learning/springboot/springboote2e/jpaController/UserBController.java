package com.learning.springboot.springboote2e.jpaController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.springboote2e.jpaModel.UserB;
import com.learning.springboot.springboote2e.jpaService.UserBService;

@RestController
@RequestMapping("/spring-data-jpa")
public class UserBController {
	
	@Autowired
	private UserBService userService;
	
	@GetMapping("/getUsers")
	public List<UserB> getUsers() {
		return userService.getUserBs();
	}
	
	@GetMapping("/getUserByProf/{profession}")
	public List<UserB> getUserBswithProf(
			@PathVariable("profession") String prof) {
		return userService.getUserBswithProf(prof);
	}

}
