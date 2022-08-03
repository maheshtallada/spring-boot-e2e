package com.learning.springboot.springboote2e.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.springboote2e.model.WelcomeBean;

@RestController
public class Controller {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/welcome")
	public String sendGreeting() {
		return "Hello-World";
	}
	
	@GetMapping(path = "/welcome-bean")
	public WelcomeBean sendGreetingBean() {
		return new WelcomeBean("Hello World bean");
	}

	@GetMapping(path = "/welcome/{phrase}")
	public WelcomeBean sendGreetingPathVariable(@PathVariable(name = "phrase") String content) {
		return new WelcomeBean("Hello " + content + "..!!");
	}

	// internationalization - i18N
	@GetMapping(path = "/welcome/i18n")
	public String sendGreetingInternationalization(
			//@RequestHeader(name = "Accept-Language", required=false) Locale locale
			) {
		return messageSource.getMessage("GM", null, LocaleContextHolder.getLocale());
	}
}
