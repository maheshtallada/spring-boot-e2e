package com.learning.springboot.springboote2e.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

	// even without noargsconstructor, we won't get any error while mapping the
	// request json through requestBody with updates in Jackson framework and
	// updated in Spring Boot

	private Integer id;
	
	@Size(min = 2, max = 20, message = "Name should be between 2 to 20 characters")
	private String name;
	
	@Past
	private Date birthDate;

}
