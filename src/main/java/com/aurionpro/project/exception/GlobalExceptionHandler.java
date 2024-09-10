package com.aurionpro.project.exception;

import java.util.NoSuchElementException;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> depositeException(AmountGreaterThanBalanceException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(SecurityException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(UserAlreadyExsistException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(IllegalArgumentException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.BAD_REQUEST.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(NoSuchElementException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage("User With this Id Does'nt Exist , Cannot Create Account ");
		res.setStatus(HttpStatus.NOT_FOUND.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(UserApiException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.NOT_FOUND.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(AccessDeniedException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage("Access Denied: You do not have the necessary permissions to access this resource.");
		res.setStatus(HttpStatus.NOT_FOUND.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<BankErrorResponse> Exception(AmountTransferException exception) {
		BankErrorResponse res = new BankErrorResponse() ;
		res.setErrorMessage(exception.getMessage());
		res.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
	    res.setTimeStamp(System.currentTimeMillis());
	    return new ResponseEntity<>(res , HttpStatus.NOT_ACCEPTABLE);
	}
	
}
