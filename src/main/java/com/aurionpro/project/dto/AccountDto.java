package com.aurionpro.project.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountDto {

	private int accId ;
	
	private long accountNumber ;
	
	private long balance;
	
	private String name ;
}
