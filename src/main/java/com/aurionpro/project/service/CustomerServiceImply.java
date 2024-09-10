package com.aurionpro.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.CustomerDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.dto.RegisterDto;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Role;
import com.aurionpro.project.repository.CustomerRepository;
import com.aurionpro.project.repository.RoleRepository;


@Service
public class CustomerServiceImply implements CustomerService {

	@Autowired
	CustomerRepository repo ;
	
	
	@Autowired
	RoleRepository roleRepo ;
	
	 @Autowired 
	 private PasswordEncoder passwordEncoder; 
	
	@Override
	public void addCutsomer(Customer customer) {
		repo.save(customer);
	}

	
	public CustomerDto toCustomerDtoMapper(Customer customer) {
		CustomerDto dto = new CustomerDto(); 
		
		dto.setFirstName(customer.getFirstName());
		dto.setLastName(customer.getLastName());
		dto.setEmail(customer.getLastName());
		dto.setPassword(customer.getPassword());
		
		return dto ;
	}	
	
	
	public Customer toCustomerMapper(CustomerDto dto) {
		Customer customer = new Customer() ;
		
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getLastName());
		customer.setPassword(dto.getPassword());
		
		return customer ;
	}
	
	
	@Override
	public CustomerDto getCustomerById(int id) {
		Optional<Customer> customerdb  = repo.findById(id); 
		Customer customer = customerdb.get() ;
		return toCustomerDtoMapper(customer);
	}


	@Override
	public PageResponseDto<CustomerDto> getAllCustomer(int pageNumber , int pageSize ) {
		Pageable page = PageRequest.of(pageNumber, pageSize) ;
		
		PageResponseDto<CustomerDto> pagedto = new PageResponseDto<>() ;

		
		List<CustomerDto> customerToadd = new ArrayList<>() ;
		
	Page<Customer> customers = repo.findAll(page);
	for(Customer c : customers) {
	List<Role> customerrole =	c.getRoles() ;
	for(Role r : customerrole) {
		if(r.getRolename().contains("ROLE_CUSTOMER")) {
			customerToadd.add(toCustomerDtoMapper(c));
		}
	}

	
	}
		
	pagedto.setLast(customers.isLast());
	pagedto.setSize(customers.getSize());
    pagedto.setTotalElements(customers.getTotalElements());
	

        pagedto.setContent(customerToadd);
        
		return pagedto;
		
	}


	@Override
	public void editCustomer(Customer customer , String newPassword , Authentication auth) {
		

		
		String username = auth.getName() ;
		System.out.println(username);
		
		if(username.equals(customer.getEmail()) ) {
			
			Optional<Customer> existingCustomer = repo.findByEmail(username);
			Customer customerdb = existingCustomer.get();
			
			if(existingCustomer != null) {
				customerdb.setPassword(passwordEncoder.encode(newPassword));
				repo.save(customerdb);
			
		}}else
		{
            throw new IllegalArgumentException("Customer not found");
		}
	}
		
			
		


	@Override
	public void register(RegisterDto registrationDto , Authentication auth) {
		
		//Optional<Customer> userdb =  repo.findByFirstName(registrationDto.getFirstName());
			
	      Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
	      boolean hasCustomerRole = authorities.stream()
	                                          .anyMatch(authority -> "ROLE_CUSTOMER".equals(authority.getAuthority()));

	       if (hasCustomerRole) {
	            throw new SecurityException("Access Denied: Account Can Only Be Created By Admin");
	        }
			
		Customer customer = new Customer() ;
		customer.setFirstName(registrationDto.getFirstName());
		customer.setLastName(registrationDto.getLastName());
		customer.setEmail(registrationDto.getEmail());
		customer.setPassword(registrationDto.getPassword());	
		
//		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
			
		List<Role>  roles = new ArrayList<>() ;

		  Role userRole = roleRepo.findByRolename(registrationDto.getRoles()).get(); 
		   
		  roles.add(userRole);
		  customer.setRoles(roles); 
		  repo.save(customer);
		
	}
		
		
		
		
		
		
	}





