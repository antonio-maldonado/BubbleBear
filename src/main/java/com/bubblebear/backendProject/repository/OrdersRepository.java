package com.bubblebear.backendProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.bubblebear.backendProject.entity.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

	Orders findById(int id);
	
	
}
