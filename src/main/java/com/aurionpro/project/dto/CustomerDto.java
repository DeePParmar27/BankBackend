package com.aurionpro.project.dto;

import java.util.List;

import com.aurionpro.project.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDto {
	

	private String firstName ;
	
	private String lastName ;
	 
	private String email ;

	private String password ;
	

}
