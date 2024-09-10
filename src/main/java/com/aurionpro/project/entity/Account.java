package com.aurionpro.project.entity;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accId ;
	
	
	private long accountNumber ;
	
	private long balance;
	
	@ManyToOne(cascade = {CascadeType.DETACH ,CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
	@JoinColumn(name="customer_id")
	@JsonIgnore
	private Customer customer; 
	
	
}
