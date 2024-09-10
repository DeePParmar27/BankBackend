package com.aurionpro.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.project.dto.CustomerDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Role;
import com.aurionpro.project.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class CustomerController {

	@Autowired
	CustomerService  service ;
	
	@PostMapping("/customer")
	@PreAuthorize("hasRole('ADMIN')")
	public String addCustomer(@Valid @RequestBody Customer customer) {
		service.addCutsomer(customer);
		return "Added customer to DataBase" ;
	}
	
	@PostMapping("/register") 
	public String registerCustomer(@Valid @RequestBody RegisterDto register , Authentication auth) {
		service.register(register , auth);
		return "Added in the DataBase " ;
	}
	
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CustomerDto> getCustomerByID(@PathVariable int id ){
		return ResponseEntity.ok(service.getCustomerById(id));
	}
	
	@GetMapping("/customer")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageResponseDto<CustomerDto>> getAllCustomer(@RequestParam int pageNumber , @RequestParam int pageSize ){
		return ResponseEntity.ok(service.getAllCustomer(pageNumber, pageSize ));
	}
	
	@PutMapping("/customer/edit/{id}")
	@PreAuthorize("hasRole('CUSTOMER')")
	public void editCustomer(@PathVariable int id , @RequestBody Customer customer , @RequestParam String newPassword , Authentication auth){
		 service.editCustomer(customer, newPassword ,auth);
	}
	
	
	
}
