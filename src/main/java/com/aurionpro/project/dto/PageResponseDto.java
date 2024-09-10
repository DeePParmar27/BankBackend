package com.aurionpro.project.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseDto<T> {
	
	private int totalPages ;
	private int size ;
	private long totalElements ;
	private List<T> content ;
	private boolean isLast ;

}
