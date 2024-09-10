package com.aurionpro.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.project.dto.JwtAuthResponse;
import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.dto.loginDto;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthControllor {
	
	@Autowired
    AuthService service ;
	
	@PostMapping("/register")
	
	public Customer register(@Valid @RequestBody RegisterDto dto ) {
		return service.register(dto); 
	}
	
	@PostMapping("/login") 
    public ResponseEntity<JwtAuthResponse> login(@RequestBody loginDto loginDto) { 
        String token = service.login(loginDto); 
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse(); 
        jwtAuthResponse.setAccesstoken(token); 
 
        return ResponseEntity.ok(jwtAuthResponse); 
    }


}
