package com.aurionpro.project.service;

import org.springframework.security.core.Authentication;

import com.aurionpro.project.dto.CustomerDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Role;

public interface CustomerService {

	public void addCutsomer(Customer customer);
	public CustomerDto getCustomerById(int id) ;
	public PageResponseDto<CustomerDto> getAllCustomer(int pageNumber , int pageSize) ;
  
	public void editCustomer(Customer customer ,String newPassword , Authentication authentication);
	
	
	 void register(RegisterDto registrationDto , Authentication auth);

	
}
