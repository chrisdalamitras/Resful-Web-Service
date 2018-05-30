package com.myRestfulwebService.example.controllers;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.myRestfulwebService.example.model.ObjectToFilter;

@RestController
public class FilteringController {
	
	@GetMapping("/filter")
	public MappingJacksonValue getSomeObjectToFilter() {
		ObjectToFilter theObject = new ObjectToFilter("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(theObject);
		mapping.setFilters(filters);
		return mapping;		
	}

}
