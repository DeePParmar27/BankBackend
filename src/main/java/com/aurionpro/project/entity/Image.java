package com.aurionpro.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	@Column
	private String url ;
	@Column
	private String imageid ;
	
	@ManyToOne(cascade = {CascadeType.DETACH ,CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	@JsonIgnore
	private Customer user; 

}
