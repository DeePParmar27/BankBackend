package com.aurionpro.project.exception;

public class UserAlreadyExsistException extends RuntimeException {
	
	public String getMessage() {
		return "User With this Email Already Exist " ;
	}

}
