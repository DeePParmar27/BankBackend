package com.aurionpro.project.exception;

import org.springframework.http.HttpStatus;

public class AmountTransferException extends RuntimeException {
	
	
	public String getMessage(){
		return "The transfer request cannot be processed because it is attempting to transfer funds to the same account. Please select a different destination account. ";
	}

	HttpStatus status = HttpStatus.BAD_REQUEST;
}
