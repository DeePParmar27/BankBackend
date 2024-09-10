package com.aurionpro.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aurionpro.project.entity.Customer;
import java.util.List;
import com.aurionpro.project.entity.Role;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	
////	  @Query("SELECT c FROM Customer c JOIN c.role r WHERE r.id = :roleId")
//	    Page<Customer> findByRoles(Pageable pageable, int roleId);	
	
	
	   Page<Customer> findByRoles(Pageable page,String roles);
	   
	   Optional<Customer>  findByCustomerId(int customerId);
	    
	boolean searchByEmail(String email);

	Optional<Customer> findByFirstName(String username);
	
	Optional<Customer> findByEmail(String email);

	boolean existsByFirstName(String username);


	
}
