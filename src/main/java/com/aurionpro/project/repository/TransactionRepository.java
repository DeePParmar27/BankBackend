package com.aurionpro.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Transaction;
import com.aurionpro.project.entity.TransactionType;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	
	public Page<Transaction> findByTransactiontype(TransactionType transactiontype , Pageable page);
	public Page<Transaction> findByFromAccountNumber(long accountNumber , Pageable page);
	
 
	
 Page<Transaction> getByFromAccountNumberOrderByAmount(long accountNumber  ,Pageable page);
	

}
