package com.aurionpro.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Transaction;
import com.aurionpro.project.entity.TransactionType;
import com.aurionpro.project.service.TransactionService;

@RestController
@RequestMapping("/app")
public class TransactionController {
	
	@Autowired
	TransactionService service ;
	
	@GetMapping("/transaction/type")
	public ResponseEntity<PageResponseDto<Transaction>> getTransactionByType(@RequestParam TransactionType type , @RequestParam int  pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(service.getTransactionByType(type, pageNumber, pageSize));
	}
	
	@GetMapping("/transaction")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PageResponseDto<Transaction>> getAllTransaction(@RequestParam int  pageNumber , @RequestParam int pageSize)
	{
		return ResponseEntity.ok(service.getAllTransaction(pageNumber, pageSize));
	}
	
	@GetMapping("/transaction/account")
	public ResponseEntity<PageResponseDto<Transaction>> getTransactionByAccountNumber(@RequestParam long accountNumber , @RequestParam int  pageNumber , @RequestParam int pageSize ,Authentication auth)
	{
		
		return ResponseEntity.ok(service.getTransactionByAccountNumber(accountNumber, pageNumber, pageSize, auth));
	}
	
	@GetMapping("/transaction/account/amount")
	public ResponseEntity<PageResponseDto<Transaction>> orderByAmount(@RequestParam long accountNumber , @RequestParam int  pageNumber , @RequestParam int pageSize , Authentication auth)
	{
		
		return ResponseEntity.ok(service.orderByAmount(accountNumber, pageNumber, pageSize ));
	}
	

}
