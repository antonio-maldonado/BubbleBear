package com.bubblebear.backendProject.service;

import java.util.List;

import com.bubblebear.backendProject.entity.Product;

public interface ProductService {
	Product createProduct(Product producto);
	
	Product saveProduct(Product producto);
	
	Product getProductById(Long id);
	
	List<Product> getAllProducts();
	
	Product updateProduct(Product producto , Long id);
	
	void deleteProduct(Long id);
}
