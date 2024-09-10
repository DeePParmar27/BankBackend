package com.aurionpro.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.project.dto.AccountDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Account;
import com.aurionpro.project.entity.TransactionType;
import com.aurionpro.project.service.AccountService;

import jakarta.websocket.OnError;

@RequestMapping("/app")
@RestController
public class AccountControllor {
	
	@Autowired
	AccountService service ;
	
    @PostMapping("/account/{customerId}")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Account> addAccount(@PathVariable int customerId,Account account , Authentication auth) {
 	
    	Account createdAccount = service.addAccount(customerId, account , auth);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    
    @GetMapping("/account")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResponseDto<AccountDto>> getAllAccount(@RequestParam int pageNumber , @RequestParam int pageSize){
    	return  ResponseEntity.ok(service.getAllAccount(pageNumber, pageSize));
    }

    @GetMapping("/accounts")
	@PreAuthorize("hasRole('ADMIN')")
    public Account getByAccountNumber(@RequestParam long accountNumber){
    	return  service.getByAccountNumber(accountNumber);
    }
    
    @PutMapping("/account/transfer")
	@PreAuthorize("hasRole('CUSTOMER')")
    public void transfer(@RequestParam int fromaccount , @RequestParam int toaccount,@RequestParam long amount,TransactionType transtype ,  Authentication authentication) {
    	 service.transfer(fromaccount, toaccount , amount, transtype ,   authentication);
    }
    
    @PutMapping("/account/withdraw")
	@PreAuthorize("hasRole('CUSTOMER')")
    public Account withdraw( int fromaccount,@RequestParam long amount,TransactionType transtype , Authentication auth) {
    	return service.withdraw(fromaccount, amount, transtype.debit , auth);
    }
    
    
    @PutMapping("/account/deposite")
	@PreAuthorize("hasRole('CUSTOMER')")
    public Account deposite(@RequestParam int fromaccount,@RequestParam long amount,TransactionType transtype , Authentication auth) {
    	return service.deposite(fromaccount , amount, transtype.credit , auth);
    }
    

    
}
