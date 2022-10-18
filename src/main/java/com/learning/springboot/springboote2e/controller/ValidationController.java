package com.learning.springboot.springboote2e.controller;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class ValidationController {
	
	@Autowired
	private ValidRepo repo;
	
	@PostMapping("/saveAspirian")
	public Aspirian saveAspirian(@Valid @RequestBody Aspirian aspirian) {
		Aspirian savedAspirian = repo.save(aspirian);
		return savedAspirian;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		
		
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}

@Entity
@Data
class Aspirian {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@NotBlank(message = "Email is mandatory")
    private String email;
	
	private float salary;
	
}

@Repository
interface ValidRepo extends JpaRepository<Aspirian, Long> {
	
}
