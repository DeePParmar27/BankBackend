package com.aurionpro.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.project.entity.Customer;

public interface UserRepository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByFirstName(String username);
	boolean existsByFirstName(String username);

}
