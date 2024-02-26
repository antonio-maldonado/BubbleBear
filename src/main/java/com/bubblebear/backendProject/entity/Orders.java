package com.bubblebear.backendProject.entity;



import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")


public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column( name = "order_id",nullable = true)
	private Integer order_id;
	
	private Date purchase_date;
	private int total_amount;
//	@Column( nullable = true)
//	private int fk_user_id;	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User user;
	
//	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
//	//@JsonIgnore
//    @JoinTable(
//            name = "orders_has_products",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//        )
//	private List<Product> products;
	
	@OneToMany(mappedBy="order",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("order")
	//@JsonBackReference
	private List<OrdersHasProducts> products;
}
