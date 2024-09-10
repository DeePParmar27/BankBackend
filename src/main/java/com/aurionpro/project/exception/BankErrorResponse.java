package com.aurionpro.project.exception;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankErrorResponse {

	private int status ;
	private String errorMessage ;
	private Long timeStamp ;

}
