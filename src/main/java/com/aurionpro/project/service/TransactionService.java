package com.aurionpro.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Transaction;
import com.aurionpro.project.entity.TransactionType;

public interface TransactionService {
	
	public PageResponseDto<Transaction> getTransactionByType(TransactionType type , int pageNumber , int PageSize);
	
	
	public PageResponseDto<Transaction> getAllTransaction(int pageNumber , int pageSize);
	public PageResponseDto<Transaction> getTransactionByAccountNumber(long accountNumber , int pageNumber , int PageSize ,Authentication authentication);
    
	public PageResponseDto<Transaction> orderByAmount(long accountNumber ,int pageNumber , int pageSize);

	
}
