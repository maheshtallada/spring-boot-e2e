package com.learning.springboot.springboote2e.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User {

	// even without noargsconstructor, we won't get any error while mapping the
	// request json through requestBody with updates in Jackson framework and
	// updated in Spring Boot

	public User(Integer id, @Size(min = 2, max = 20, message = "Name should be between 2 to 20 characters") String name,
			@Past Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, max = 20, message = "Name should be between 2 to 20 characters")
	private String name;
	
	@Past
	private Date birthDate;
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

}
