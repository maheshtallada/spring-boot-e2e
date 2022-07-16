package com.learning.springboot.springboote2e.model;

import java.util.Date;

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
	private String name;
	private Date birthDate;

}
