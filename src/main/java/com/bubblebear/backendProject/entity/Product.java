package com.bubblebear.backendProject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	
//	@OneToMany(mappedBy="product",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
//	@JsonIgnoreProperties("product")
//	private List<OrdersHasProducts> orders;
//    @Column(name = "quantity", nullable = true,columnDefinition="INT default 1")
//    private int quantity;
//
//    @Column(name = "price_product", nullable = true,columnDefinition="INT default 1")
//    private int priceProduct;
	
//	@ManyToMany(mappedBy="products",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
//	@JsonIgnore
//	//@JsonIgnoreProperties("categories")
//	private List<Orders> orders;
//	@ManyToOne
//	@JoinColumn(name="fk_categories_id")
//	@JsonIgnoreProperties("products")
//	private Categories category;
	
}
