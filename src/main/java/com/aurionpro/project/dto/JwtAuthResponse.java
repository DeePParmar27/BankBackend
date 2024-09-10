package com.aurionpro.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class JwtAuthResponse {
	
	private String accesstoken ;
	
	private String tokentype ="Bearer" ;

}
