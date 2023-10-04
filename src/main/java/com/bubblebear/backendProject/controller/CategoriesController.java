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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bubblebear.backendProject.entity.Categories;
import com.bubblebear.backendProject.service.CategoryService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("api/categories")
public class CategoriesController {
	@Autowired
	CategoryService categoriesService;
	
	@GetMapping
	public ResponseEntity <List<Categories>> getAllCategories() {
		List <Categories> getAllCategories = categoriesService.getAllCategories();
		return new ResponseEntity<>(getAllCategories, HttpStatus.OK);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity <Categories> getCategoryById(@PathVariable long id) {
		Categories getCategoryById = categoriesService.getCategoryById(id);
		return new ResponseEntity<>(getCategoryById, HttpStatus.OK);
	}
	
	
	
	
	@PostMapping()
	public ResponseEntity <Categories> createCategory (Categories category) {
		Categories createdCategory = categoriesService.createCategory(category);
		return new ResponseEntity<>(createdCategory, HttpStatus.OK);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity <Categories> updateCategory (Categories category, @PathVariable long id) {
		Categories updatedCategory = categoriesService.updateCategory(category, id);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public void deleteCategory (Categories category, @PathVariable long id) {
		categoriesService.deleteCategory(id);	
	}	
}
