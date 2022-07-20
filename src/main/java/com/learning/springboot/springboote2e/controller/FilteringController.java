package com.learning.springboot.springboote2e.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.springboote2e.model.CustomBean;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public CustomBean retrieveCustomBean() {
		return new CustomBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<CustomBean> retrieveCustomBeansList() {
		return Arrays.asList(new CustomBean("value1", "value2", "value3"),
				new CustomBean("value11", "value22", "value33"),
				new CustomBean("value12", "value22", "value32"));
	}
}
