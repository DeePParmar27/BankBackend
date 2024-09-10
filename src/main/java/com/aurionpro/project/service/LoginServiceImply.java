package com.aurionpro.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Login;
import com.aurionpro.project.repository.LoginRepository;

@Service
public class LoginServiceImply implements LoginService{

	@Autowired
	LoginRepository repo ;
	
	@Override
	public void addAdmin(Login login) {
		repo.save(login);
		
	}

	@Override
	public PageResponseDto<Login> getAllAdmin(int pageNumber, int pageSize) {
		Pageable page =  PageRequest.of(pageNumber, pageSize) ;
		Page<Login> logindb = repo.findAll(page);
		PageResponseDto<Login> pagedto = new PageResponseDto<>() ;
		
		pagedto.setLast(logindb.isLast());
		pagedto.setSize(logindb.getSize());
        pagedto.setTotalElements(logindb.getTotalElements());
        
        List<Login> admins = new ArrayList<>() ;
        for(Login data : logindb) {
        	admins.add(data);
        }
        
        pagedto.setContent(admins);
        
		return pagedto;
	}

}
