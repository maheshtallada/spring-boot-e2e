package com.learning.springboot.springboote2e;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class WelcomeBean {

	private String message;

	public WelcomeBean(String message) {
		this.message = message;
	}
	
}
