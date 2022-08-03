package com.learning.springboot.springboote2e.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.springboot.springboote2e.customException.UserNotFoundException;
import com.learning.springboot.springboote2e.model.Post;
import com.learning.springboot.springboote2e.model.User;
import com.learning.springboot.springboote2e.repository.PostRepository;
import com.learning.springboot.springboote2e.repository.UserJpaRepository;

//@RequestMapping("/user")
@RestController
public class UserJpaController {
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	private PostRepository postRepository;

	/*
	 * add this property if browser/output return date as timestamp
	 * for versions before 2.0.0.RC1
	 * spring.jackson.serialization.write-dates-as-timestamps=false
	 */
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {
		return userJpaRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/user/{userId}")
	public EntityModel<User> retriveById(@PathVariable(name = "userId") int id) {

		Optional<User> user  = userJpaRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("id :: "+ id);

		// hateoas code start
		EntityModel<User> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers =
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		// hateoas code end

		return model;
	}
	
	
	/*
	 * // this will send a HTTP status : 201 Created along with 'Location' header with
	 * uri of created user -- with value of 'uri' variable
	 */	
	@PostMapping("/jpa/user")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userJpaRepository.save(user);
		
		// ex. for builder pattern
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


	@DeleteMapping(path = "/jpa/user/{userId}")
	public void deleteById(@PathVariable(name = "userId") int id) {
		userJpaRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveAllPosts(@PathVariable("id") int userid) {
		
		Optional<User> user = userJpaRepository.findById(userid);
		
		if(!user.isPresent()) 
			throw new UserNotFoundException("id :: "+ userid);
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(
			@PathVariable("id") int userId,
			@RequestBody Post post) {
		
		Optional<User> userOptional = userJpaRepository.findById(userId);
		
		if(!userOptional.isPresent()) 
			throw new UserNotFoundException("id :: "+ userId);
		
		post.setUser(userOptional.get());
		postRepository.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();

	}
}
