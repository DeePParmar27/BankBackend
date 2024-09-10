package com.aurionpro.project.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Login;
import com.aurionpro.project.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/app")
public class LoginController {

	@Autowired
	LoginService service ;
	
	
	@PostMapping("/clients")
	public String addClientEmployee(@Valid @RequestBody Login login) {
		service.addAdmin(login);
		return "Add Employee " ;
	}
	
	@GetMapping("/clients")
	public ResponseEntity<PageResponseDto<Login>> getAllClients(@RequestParam int pageNumber , @RequestParam int pageSize){
		return  ResponseEntity.ok(service.getAllAdmin(pageNumber, pageSize));
	}
	
}
