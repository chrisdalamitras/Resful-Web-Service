package com.myRestfulwebService.example.model;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonFilter("myFilter")
public class ObjectToFilter {
	
	private String field1;	
	private String field2;	
	private String field3;
	
	public ObjectToFilter(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	
}
