package com.aurionpro.project.exception;

import org.springframework.http.HttpStatus;

public class NoSuchElementException extends RuntimeException {

	public String getMessage() {
		return "User With this Id Does'nt Exist " ;
	}
	
	HttpStatus status = HttpStatus.NOT_FOUND;
}
