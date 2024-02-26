package com.bubblebear.backendProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders_has_products")

public class OrdersHasProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "quantity", nullable = true,columnDefinition="INT default 1")
    private int quantity;

    @Column(name = "price_product", nullable = true,columnDefinition="INT default 1")
    private int priceProduct;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name="product_id")
	//@JsonIgnoreProperties("orders_has_products")
	private Product product;
    
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name="order_id")
	//@JsonIgnoreProperties("orders_has_products")
	private Orders order;
//    
//    @Column(name = "fk_order_id", nullable = false)
//    private int order;
//    
//    @ManyToOne
//	@JoinColumn(name="fk_order_id")
//	@JsonIgnoreProperties("orders_has_products")
//	private Orders orders;

    
}
