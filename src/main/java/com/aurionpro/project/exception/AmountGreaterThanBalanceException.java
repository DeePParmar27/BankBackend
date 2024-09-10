package com.aurionpro.project.exception;



public class AmountGreaterThanBalanceException extends RuntimeException {

	public String getMessage() {
		return "Enter Amount Less Than Balance" ;
	}
	
}
