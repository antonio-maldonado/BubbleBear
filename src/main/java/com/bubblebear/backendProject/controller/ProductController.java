package com.bubblebear.backendProject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bubblebear.backendProject.entity.Product;
import com.bubblebear.backendProject.service.ProductService;


@CrossOrigin(origins = "*")
@RestController //@Controller @ResponseBody
@RequestMapping("api/products")

public class ProductController {
	@Autowired
	ProductService productService;

	
	@GetMapping
	public ResponseEntity<List<Product>> getAllCustomers(){
		List <Product> products = (List<Product>) productService.getAllProducts();
		return new ResponseEntity<>( products,HttpStatus.OK); 
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Product> insertNewProduct(@RequestBody Product producto) {
		return new ResponseEntity<>(productService.createProduct(producto), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product producto, @PathVariable long id ) {
		return new ResponseEntity<>(productService.updateProduct(producto, id),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public void deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
	}
	
	
}
