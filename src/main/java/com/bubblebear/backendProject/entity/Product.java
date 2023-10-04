package com.bubblebear.backendProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Long id;
	
	@Column(name = "name", nullable = false, length=100)
	private String name;
	
	@Column(name = "price", nullable = false)
	private long price;
	
	@Column(name = "size", nullable = false, length=45)
	private String size;
	
	@Column(name = "stock", nullable = false)
	private long stock;
	
	@Column(name = "hide", nullable = false)
	private int hide;
	
	@Column(name = "description", nullable = false, length=300)
	private String description;
	
	@Column(name = "product_photo", nullable = false, length=150)
	private String photo;
	
	@Column(name = "flavor", nullable = false, length=45)
	private String flavor;
	
	@Column(name = "fk_categories_id", nullable = false)
	private int category;
//	@ManyToOne
//	@JoinColumn(name="fk_categories_id")
//	@JsonIgnoreProperties("products")
//	private Categories category;
	
}
