package com.aurionpro.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRolename(String role);

}
