package com.aurionpro.project.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.aurionpro.project.dto.AccountDto;
import com.aurionpro.project.dto.CustomerDto;
import com.aurionpro.project.dto.PageResponseDto;
import com.aurionpro.project.entity.Account;
import com.aurionpro.project.entity.Customer;
import com.aurionpro.project.entity.MailStructure;
import com.aurionpro.project.entity.Transaction;
import com.aurionpro.project.entity.TransactionType;
import com.aurionpro.project.exception.AmountGreaterThanBalanceException;
import com.aurionpro.project.exception.AmountTransferException;
import com.aurionpro.project.exception.UserNotFoundException;
import com.aurionpro.project.repository.AccountRepository;
import com.aurionpro.project.repository.CustomerRepository;
import com.aurionpro.project.repository.TransactionRepository;

import jakarta.mail.MessagingException;

@Service
public class AccountServiceImply implements AccountService {

	@Autowired
	AccountRepository repo ;
	
	@Autowired
	CustomerRepository customerRepo ;
	
	@Autowired
	TransactionRepository transactionRepository ;
	
	@Autowired
	MailService mailService ;
	
	@Override
	public Account addAccount( int customerId , Account account , Authentication auth) {
		
      Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
      boolean hasCustomerRole = authorities.stream()
                                          .anyMatch(authority -> "ROLE_CUSTOMER".equals(authority.getAuthority()));

       if (hasCustomerRole) {
            throw new SecurityException("Access Denied: Account Can Only Be Created By Admin");
        }
        
        
		Optional<Customer> customer = customerRepo.findById(customerId);
	
		Customer customerdb = customer.get();
		                  

		List<Account> accountOfUser = customerdb.getAccounts() ;
		accountOfUser.add(account) ;
		
		int randomNumber = ThreadLocalRandom.current().nextInt(10000000, 100000000);
        account.setAccountNumber(randomNumber);
        account.setBalance(1000);   
		account.setCustomer(customerdb);
		
		repo.save(account);
		return account ;
	}
	
	public AccountDto toAccountDtoMapper(Account acc) {
		AccountDto dto = new AccountDto(); 
		
        dto.setAccId(acc.getAccId());
        dto.setAccountNumber(acc.getAccountNumber());
        dto.setBalance(acc.getBalance());
        dto.setName(acc.getCustomer().getFirstName());
		
		return dto ;
	}	
	
	
	public Customer toCustomerMapper(CustomerDto dto) {
		Customer customer = new Customer() ;
		
		customer.setFirstName(dto.getFirstName());
		customer.setLastName(dto.getLastName());
		customer.setEmail(dto.getLastName());
		customer.setPassword(dto.getPassword());
		
		return customer ;
	}


	@Override
	public PageResponseDto<AccountDto> getAllAccount(int pageNumber , int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Account> account = repo.findAll(page);

		List<AccountDto> dto = new ArrayList<>() ;
		for(Account acc : account) {
			dto.add(toAccountDtoMapper(acc));
		}
		
      PageResponseDto<AccountDto> pagedto = new PageResponseDto<>() ;
		
		pagedto.setLast(account.isLast());
		pagedto.setSize(account.getSize());
        pagedto.setTotalElements(account.getTotalElements());
        pagedto.setTotalPages(account.getTotalPages());
        pagedto.setContent(dto);
        
        return pagedto ;
        

		
	}

	@Override
	public Account getByAccountNumber(long accountNumber) {
		return repo.getByAccountNumber(accountNumber);
	}

	@Override
	public Account deposite(int fromaccount,  long amount, TransactionType transtype , Authentication authentication) {

        if (authentication == null) {
            throw new SecurityException("Please provide Authentication Token");
        }
        
		MailStructure mail = new MailStructure() ;
        LocalDate currentDate = LocalDate.now();

		
		String emailInToken = authentication.getName() ;
		String userEmail = findEmailByAccountNumber(fromaccount);
		
        
        System.out.println(emailInToken);
        System.out.println(userEmail);
        		
        if(userEmail == null) {
            throw new IllegalArgumentException("Account number not found");
        }

        if (!emailInToken.equals(userEmail)) {
            throw new SecurityException("Access denied: User does not own this account");
        }
		
		Account account = getByAccountNumber(fromaccount);
		account.setBalance(account.getBalance()+amount);
		Account dbaccount = repo.save(account) ;
		
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		Transaction transaction = new Transaction(transtype, date,  amount, fromaccount,  fromaccount);
		transactionRepository.save(transaction);
		
		mail.setSubject("Transaction Alert: Transfer Successful");
		mail.setMessage("Dear "+emailInToken +",\n\n" +
                "Your account has been Credited with Amount $"+ amount +" on " +currentDate+".\n\n" +
                "If you did not make this transaction, please contact support immediately.\n\n" +
                "Thank you,\n" +
                "Apro Bank Unlimited");
	
		
		try {
			mailService.sendMail(userEmail, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return account ;
	}

	@Override
	public Account withdraw(int fromaccount, long amount, TransactionType transtype , Authentication authentication) {
		
		MailStructure mail = new MailStructure() ;
        LocalDate currentDate = LocalDate.now();

		
        if (authentication == null) {
            throw new SecurityException("Please provide Authentication Token");
        }
        
		
		String emailInToken = authentication.getName() ;
		String userEmail = findEmailByAccountNumber(fromaccount);
		
        
        System.out.println(emailInToken);
        System.out.println(userEmail);
        		
        if(userEmail == null) {
            throw new IllegalArgumentException("Account number not found");
        }

        if (!emailInToken.equals(userEmail)) {
            throw new SecurityException("Access denied: User does not own this account");
        }
		
		
		Account account = getByAccountNumber(fromaccount);
		if(amount > account.getBalance())
			throw new AmountGreaterThanBalanceException();
		
		account.setBalance(account.getBalance() - amount);
		Account dbaccount = repo.save(account) ;
		
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		Transaction transaction = new Transaction(transtype, date,  amount, fromaccount,  fromaccount);
		transactionRepository.save(transaction);
		
		mail.setSubject("Transaction Alert: Withdrawal Successful");
		mail.setMessage("Dear "+emailInToken +",\n\n" +
                "Your account has been debited with Amount $"+ amount +" on " +currentDate+".\n\n" +
                "If you did not make this transaction, please contact support immediately.\n\n" +
                "Thank you,\n" +
                "Apro Bank Unlimited");
	
		
		try {
			mailService.sendMail(userEmail, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account ;
		
		
	}

	@Override
	public void transfer(int fromaccount, int toaccount, long amount, TransactionType transtype , Authentication authentication) {
	
//		withdraw(fromaccount, toaccount, amount, transtype.debit) ;

//		deposite(toaccount, fromaccount, amount, transtype.credit);

		MailStructure mail = new MailStructure() ;
        LocalDate currentDate = LocalDate.now();

		
        if (authentication == null) {
            throw new SecurityException("Please provide Authentication Token");
        }
        
		
		String emailInToken = authentication.getName() ;
		String userEmail = findEmailByAccountNumber(fromaccount);
		String tranferEmail = findEmailByAccountNumber(toaccount);
		
        
        System.out.println(emailInToken);
        System.out.println(userEmail);
        		
        if(userEmail == null) {
            throw new IllegalArgumentException("Account number not found");
        }

        if (!emailInToken.equals(userEmail)) {
            throw new SecurityException("Access denied: User does not own this account");
        }
		
		if(fromaccount ==  toaccount) {
			throw new AmountTransferException();
		}
		
		Account account = getByAccountNumber(fromaccount);
		Account transferAccount = getByAccountNumber(toaccount);

		if(amount > account.getBalance())
			throw new AmountGreaterThanBalanceException();
		
		account.setBalance(account.getBalance() - amount);
		Account dbaccount = repo.save(account) ;
		
		transferAccount.setBalance(transferAccount.getBalance() + amount);
		Account traAccount = repo.save(transferAccount) ;
		
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		Transaction transaction1 = new Transaction(transtype.debit, date,  amount, toaccount,  fromaccount);
		transactionRepository.save(transaction1);
		
		mail.setSubject("Transaction Alert: Transfer Successful");
		mail.setMessage("Dear "+emailInToken +",\n\n" +
                "Your account has been debited with Amount $"+ amount +" on " +currentDate+".\n\n" +
                "If you did not make this transaction, please contact support immediately.\n\n" +
                "Thank you,\n" +
                "Apro Bank Unlimited");
	
		
		try {
			mailService.sendMail(userEmail, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Transaction transaction2 = new Transaction(transtype.credit, date,  amount, fromaccount,  toaccount);
		transactionRepository.save(transaction2);
		
		
		mail.setSubject("Transaction Alert: Transfer Successful");
		mail.setMessage("Dear "+emailInToken +",\n\n" +
                "Your account has been Credited with Amount $"+ amount +" on " +currentDate+".\n\n" +
                "If you did not make this transaction, please contact support immediately.\n\n" +
                "Thank you,\n" +
                "Apro Bank Unlimited");
	
		
		try {
			mailService.sendMail(tranferEmail, mail);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//	return account ;
	}

	@Override
	public String findEmailByAccountNumber(long accountNumber) {
		return repo.findEmailByAccountNumber(accountNumber);
	}
	
	
	
	

}
