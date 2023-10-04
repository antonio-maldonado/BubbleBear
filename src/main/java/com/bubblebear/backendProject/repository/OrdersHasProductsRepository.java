package com.bubblebear.backendProject.repository;

import org.springframework.data.repository.CrudRepository;


import com.bubblebear.backendProject.entity.OrdersHasProducts; 

public interface OrdersHasProductsRepository extends CrudRepository<OrdersHasProducts, Long> {
	OrdersHasProducts findById(long id);
}
