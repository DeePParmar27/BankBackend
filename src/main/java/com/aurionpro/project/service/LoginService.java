package com.aurionpro.project.service;

import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Login;

@Service
public interface LoginService  {
	
	public void addAdmin(Login login);
    PageResponseDto<Login> getAllAdmin(int pageNumber , int pageSize);
}
