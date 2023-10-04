package com.bubblebear.backendProject.service;

import java.util.List;

import com.bubblebear.backendProject.entity.Categories;

public interface CategoryService  {

	Categories createCategory(Categories category);

	Categories getCategoryById(Long id);

	List<Categories> getAllCategories();

	Categories updateCategory(Categories category, Long id);

	void deleteCategory(Long id);


	
	
}
