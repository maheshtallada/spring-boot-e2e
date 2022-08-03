package com.learning.springboot.springboote2e.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@JsonFilter("SomeBeanFilter")

// static filtering - second way
//@JsonIgnoreProperties(value = {"field1","field2"})
public class CustomBean {
	
	private String field1;
	private String field2;
	
	// with this, in NONE of the responses field 3 will be sent, hence static
	//@JsonIgnore
	private String field3;
	
}
