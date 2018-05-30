package com.myRestfulwebService.example.exceptions;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyExceptionResponse {
	private Date timeStamp;
	private String message;
	private String details;
	
	public MyExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
}
