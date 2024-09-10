package com.aurionpro.project.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.AccountDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Account;
import com.aurionpro.project.entity.TransactionType;

@Service
public interface AccountService {
	
	public Account addAccount(int customerId ,Account account , Authentication auth);
    public PageResponseDto<AccountDto> getAllAccount(int pageNumber , int pageSize);
    public Account getByAccountNumber(long accountNumber);
    
    public Account deposite(int fromaccount , long amount , TransactionType transtype , Authentication auht);
    public Account withdraw(int fromaccount , long amount , TransactionType transtype , Authentication auth);
    public void transfer(int fromaccount , int toaccount , long amount , TransactionType transtype ,  Authentication authentication);

	String findEmailByAccountNumber(long accountNumber);


}
