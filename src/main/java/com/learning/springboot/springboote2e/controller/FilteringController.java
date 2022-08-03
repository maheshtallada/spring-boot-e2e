package com.learning.springboot.springboote2e.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
	
	@GetMapping("/filterDynamic")
	public MappingJacksonValue retrieveDynamiBean() {
		
		CustomBean customBean = new CustomBean("value1", "value2", "value3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(customBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue retrieveDynamicBeansList() {
		
		List<CustomBean> asList = Arrays.asList(new CustomBean("value1", "value2", "value3"),
				new CustomBean("value11", "value22", "value33"),
				new CustomBean("value12", "value22", "value32"));
		
		MappingJacksonValue mapping = new MappingJacksonValue(asList);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
