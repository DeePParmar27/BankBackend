package com.aurionpro.project.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserNotFoundException extends RuntimeException {

	private String message ;
	private HttpStatus status ;
}
