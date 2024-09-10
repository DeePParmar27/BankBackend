
package com.aurionpro.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Transaction;
import com.aurionpro.project.entity.TransactionType;
import com.aurionpro.project.repository.TransactionRepository;

import jakarta.validation.constraints.Null;

@Service
public class TransactionServicImply implements TransactionService {

	@Autowired
	TransactionRepository repo ;
	
	@Autowired
	AccountService service ;
	


	@Override
	public PageResponseDto<Transaction> getTransactionByType(TransactionType type, int pageNumber, int PageSize) {
		Pageable page = PageRequest.of(pageNumber, PageSize);
		Page<Transaction> transactiondb = repo.findByTransactiontype(type , page);
		List<Transaction> transactions = new ArrayList<>() ;
		
		for(Transaction t  : transactiondb) {
		      transactions.add(t);
		}
		System.out.println(transactions);
		PageResponseDto<Transaction> response = new PageResponseDto<>() ;

		response.setSize(transactiondb.getSize());
		response.setContent(transactions);
		response.setLast(transactiondb.isLast());
		response.setTotalElements(transactiondb.getTotalElements());
		response.setTotalPages(transactiondb.getTotalPages());
		
		return response ;
	}



	@Override
	public PageResponseDto<Transaction> getAllTransaction(int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Transaction> transactiondb = repo.findAll(page);
		List<Transaction> transactions = new ArrayList<>() ;
		
		for(Transaction t  : transactiondb) {
		      transactions.add(t);
		}
		System.out.println(transactions);
		PageResponseDto<Transaction> response = new PageResponseDto<>() ;

		response.setSize(transactiondb.getSize());
		response.setContent(transactions);
		response.setLast(transactiondb.isLast());
		response.setTotalElements(transactiondb.getTotalElements());
		response.setTotalPages(transactiondb.getTotalPages());
		
		return response ;
	}



	@Override
	public PageResponseDto<Transaction> getTransactionByAccountNumber(long accountNumber, int pageNumber,
		int PageSize , Authentication authentication) {
		
        if (authentication == null) {
            throw new SecurityException();
        }
        
        String currentUserEmail = authentication.getName() ;
        String emailForAccountNumber = service.findEmailByAccountNumber(accountNumber);
        System.out.println(currentUserEmail);
        System.out.println(emailForAccountNumber);
        		
        if(emailForAccountNumber == null) {
            throw new IllegalArgumentException("Account number not found");
        }

        if (!currentUserEmail.equals(emailForAccountNumber)) {
            throw new SecurityException("Access denied: User does not own this account");
        }

		Pageable page = PageRequest.of(pageNumber, PageSize);
		Page<Transaction> transactiondb = repo.findByFromAccountNumber(accountNumber , page);
		List<Transaction> transactions = new ArrayList<>() ;
		
		for(Transaction t  : transactiondb) {
		      transactions.add(t);
		}
		System.out.println(transactions);
		PageResponseDto<Transaction> response = new PageResponseDto<>() ;

		response.setSize(transactiondb.getSize());
		response.setContent(transactions);
		response.setLast(transactiondb.isLast());
		response.setTotalElements(transactiondb.getTotalElements());
		response.setTotalPages(transactiondb.getTotalPages());
		
		return response ;
	}



	@Override
	public PageResponseDto<Transaction> orderByAmount(long accountNumber ,int pageNumber, int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Transaction> transactiondb = repo.getByFromAccountNumberOrderByAmount(accountNumber , page);
		List<Transaction> transactions = new ArrayList<>() ;
		
		for(Transaction t  : transactiondb) {
		      transactions.add(t);
		}
		System.out.println(transactions);
		PageResponseDto<Transaction> response = new PageResponseDto<>() ;

		response.setSize(transactiondb.getSize());
		response.setContent(transactions);
		response.setLast(transactiondb.isLast());
		response.setTotalElements(transactiondb.getTotalElements());
		response.setTotalPages(transactiondb.getTotalPages());
		
		return response ;
	}




}
