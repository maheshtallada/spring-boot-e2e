package com.learning.springboot.springboote2e.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.springboot.springboote2e.customException.UserNotFoundException;
import com.learning.springboot.springboote2e.model.User;
import com.learning.springboot.springboote2e.repository.UserRepository;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/*
	 * add this property if browser/output return date as timestamp
	 * for versions before 2.0.0.RC1
	 * spring.jackson.serialization.write-dates-as-timestamps=false
	 */
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path = "/user/{userId}")
	public User retriveById(@PathVariable(name = "userId") int id) {

		User user  = userRepository.findOne(id);

		if (user == null)
			throw new UserNotFoundException("id :: "+ id);
		return user;
	}
	
	
	/*
	 * // this will send a HTTP status : 201 Created along with 'Location' header with
	 * uri of created user -- with value of 'uri' variable
	 */	
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		// ex. for builder patter
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
		// the above gives current request's uri		
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		//URI hardcodedURI = URI.create("http://localhost:8080/user/user/" + savedUser.getId());

		// In this way, automatically the header key is set as Location and the value as
		// parameter of created method, uri here

		//return ResponseEntity.created(uri).build();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri.toString());
		
		// if only 2 parameters are passed, the first is considered as Body and second
		// as status, hence passing body as empty in this case
		return new ResponseEntity<>(null, headers, HttpStatus.CREATED);

	}
}
