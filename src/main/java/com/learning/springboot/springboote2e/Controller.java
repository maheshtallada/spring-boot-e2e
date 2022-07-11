package com.learning.springboot.springboote2e;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping(method = RequestMethod.GET, path = "/welcome")
	public String sendGreeting() {
		return "Hello-World";
		
	}
}
