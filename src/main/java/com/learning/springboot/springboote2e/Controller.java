package com.learning.springboot.springboote2e;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping(path = "/welcome")
	public String sendGreeting() {
		return "Hello-World";
		
	}
	
	@GetMapping(path = "/welcome-bean")
	public WelcomeBean sendGreetingBean() {
		return new WelcomeBean("Hello World bean");
		
	}
}
