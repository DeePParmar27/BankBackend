package com.aurionpro.project.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int transactionId ;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TransactionType transactiontype ;
	
	@Column
	private Date date ;
	
	@Column
	private double amount ;
	
	@Column
	private long toAccountNumber ;
	
	@Column
	private long fromAccountNumber ;

	public Transaction(TransactionType transactiontype, Date date, double amount, long toAccountNumber,
			long fromAccountNumber) {
		super();
		this.transactiontype = transactiontype;
		this.date = date;
		this.amount = amount;
		this.toAccountNumber = toAccountNumber;
		this.fromAccountNumber = fromAccountNumber;
	}
	
	
	
}
