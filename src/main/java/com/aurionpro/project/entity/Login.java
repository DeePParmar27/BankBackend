package com.aurionpro.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="login")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Login {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private int id ;

    @Column
    @NotNull
	private String username ;
	
    @Column
    @NotNull
    @Size(min = 6)
	private String password ;
	
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
	private loginAs loginas ;

}
