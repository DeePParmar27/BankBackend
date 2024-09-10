package com.aurionpro.project.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId ;
	
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	@Size(min = 2)
	private String firstName ;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z]+$")
	@Size(min=2)
	private String lastName ;
	 
	@NotNull
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email ;
	
	@NotNull
	@Size(min = 6 )
	private String password ;
	
	@OneToMany(mappedBy = "customer" ,cascade = {CascadeType.DETACH ,CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
	private List<Account> accounts ;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "cusomerid") , inverseJoinColumns = @JoinColumn(name = "rolename"))
	private List<Role> roles ;
	

	

	
}
