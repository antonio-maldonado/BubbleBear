package com.bubblebear.backendProject.entity;

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

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price_product", nullable = false)
    private int priceProduct;

    @Column(name = "fk_product_id", nullable = false)
    private int product;
    
    @Column(name = "fk_order_id", nullable = false)
    private int order;
    
//    @ManyToOne
//	@JoinColumn(name="fk_order_id")
//	@JsonIgnoreProperties("orders_has_products")
//	private Orders orders;

    
}
