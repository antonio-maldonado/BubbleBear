package com.bubblebear.backendProject.repository;

import org.springframework.data.repository.CrudRepository;

import com.bubblebear.backendProject.entity.Product;



public interface ProductRepository extends CrudRepository<Product, Long> {

	  Product findById(long id);
}