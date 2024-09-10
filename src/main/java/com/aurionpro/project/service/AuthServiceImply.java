package com.aurionpro.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.dto.loginDto;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Role;
import com.aurionpro.project.exception.UserApiException;
import com.aurionpro.project.repository.CustomerRepository;
import com.aurionpro.project.repository.RoleRepository;
import com.aurionpro.project.security.JwtTokenProvider;

@Service
public class AuthServiceImply implements AuthService {
	
	@Autowired 
	 private AuthenticationManager authenticationManager; 
	  
	 @Autowired 
	 private CustomerRepository repo; 
	 
	 @Autowired 
	 private RoleRepository roleRepo; 
	  
	 @Autowired 
	 private PasswordEncoder passwordEncoder; 
	  
	 @Autowired 
	 private JwtTokenProvider tokenProvider;

	@Override
	public Customer register(RegisterDto registrationDto) {
//		if(userRepo.existsByUsername(registrationDto.getUsername()))
//			throw new UserApiException(HttpStatus.BAD_REQUEST,"User Already Exist");
		
		Optional<Customer> userdb =  repo.findByEmail(registrationDto.getEmail());
		
		
		if(userdb.isPresent())
			throw new UserApiException(HttpStatus.BAD_REQUEST , "User already Exists");

		
//		if(repo.existsByFirstName(registrationDto.getFirstName()))
//			throw new UserApiException(HttpStatus.BAD_REQUEST , "User already Exists");
			
			
		Customer customer = new Customer() ;
		customer.setFirstName(registrationDto.getFirstName());
		customer.setLastName(registrationDto.getLastName());
		customer.setEmail(registrationDto.getEmail());
			
		customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		List<Role>  roles = new ArrayList<>() ;

		  Role userRole = roleRepo.findByRolename(registrationDto.getRoles()).get(); 
		   
		  roles.add(userRole); 
		  customer.setRoles(roles); 
		  return repo.save(customer); 		
	}

	@Override
	public String login(loginDto logindto) {
	try {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword())); 
		SecurityContextHolder.getContext().setAuthentication(authentication); 
		   String token = tokenProvider.generateToken(authentication); 
		 
		   return token; 
				
	} catch (BadCredentialsException e) {
		throw new UserApiException(HttpStatus.NOT_FOUND, "Invaid login details");
	}
		
	}

}
