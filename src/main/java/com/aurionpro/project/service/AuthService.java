package com.aurionpro.project.service;

import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.dto.loginDto;
import com.aurionpro.project.entity.Customer;


@Service
public interface AuthService {

	
	 Customer register(RegisterDto registrationDto);
     String login(loginDto logindto );
	
	
	
}
