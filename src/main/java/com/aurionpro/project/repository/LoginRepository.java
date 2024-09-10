package com.aurionpro.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.project.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

}
