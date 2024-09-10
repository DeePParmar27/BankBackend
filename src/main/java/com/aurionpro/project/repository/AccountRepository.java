package com.aurionpro.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.aurionpro.project.dto.AccountDto;
import com.aurionpro.project.entity.Account;
import java.util.List;



@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account getByAccountNumber(long accountNumber);
	
    @Query("SELECT c.email FROM Account a JOIN a.customer c WHERE a.accountNumber = :accountNumber")
	String findEmailByAccountNumber(long accountNumber);

}
