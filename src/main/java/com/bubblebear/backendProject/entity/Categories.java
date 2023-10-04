package com.bubblebear.backendProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")

public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categories_id", nullable = false)
	private long id; 
	
	@Column (name = "sale", nullable = false)
	private boolean sale;  
	
	@Column (name = "outstanding", nullable = false)
	private boolean outstanding;  

}

