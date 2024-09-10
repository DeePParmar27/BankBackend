package com.aurionpro.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankErrorDto {

	private int status ;
	private String errorMessage ;
	private Long timeStamp ;
}
