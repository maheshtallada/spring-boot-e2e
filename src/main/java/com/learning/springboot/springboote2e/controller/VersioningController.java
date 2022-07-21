package com.learning.springboot.springboote2e.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springboot.springboote2e.model.FullName;
import com.learning.springboot.springboote2e.model.Personv1;
import com.learning.springboot.springboote2e.model.Personv2;

@RestController
public class VersioningController {
	
	// This is the basic Versioning, just providing diff uris for versions
	//basic Versioning start -- called URI versioning -- used by Twitter
	
	@GetMapping("/person/v1")
	public Personv1 personv1() {
		return new Personv1("Mahesh Tallada");
	}
	
	@GetMapping("/person/v2")
	public Personv2 personv2() {
		return new Personv2(new FullName("Mahesh", "Tallada"));
	}
	//basic Versioning end
	
	
	// This Versioning is by providing version no. in query param
	//Request param Versioning start -- used By Amazon
	
	@GetMapping(value = "/person", params = "version=1")
	public Personv1 personParamv1() {
		return new Personv1("Mahesh Tallada");
	}
	
	@GetMapping(value = "/person", params = "version=2")
	public Personv2 personParamv2() {
		return new Personv2(new FullName("Mahesh", "Tallada"));
	}
	//Request param Versioning end
	
	/* 
	 * 3. Through Headers (custom header) -- used by Microsoft
	 * 4. Media Type Versioning  -- using produces in GetMapping --used by GitHub
	 * -- called "MIME type" OR "Content-Negotiaton" OR "Accept Header"
	 * 
	 * Each one has specific adv/dis-adv 
	 * -- choosing one is absolutely based on requirement from client
	 */
	
}
