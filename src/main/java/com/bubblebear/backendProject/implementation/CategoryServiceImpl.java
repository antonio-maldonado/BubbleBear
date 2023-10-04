package com.bubblebear.backendProject.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bubblebear.backendProject.entity.Categories;
import com.bubblebear.backendProject.repository.CategoriesRepository;
import com.bubblebear.backendProject.service.CategoryService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoriesRepository categoriesRepository;

	@Override
	public Categories createCategory(Categories category) {
		return categoriesRepository.save(category);
	}


	@Override
	public Categories getCategoryById(Long id) {
	
		return categoriesRepository.findById(id)
				.orElseThrow( ()-> new IllegalStateException("Category does not exist with id "+ id) );
	}

	@Override
	public List<Categories> getAllCategories() {
		
		return (List<Categories>) categoriesRepository.findAll();
	}

	@Override
	public Categories updateCategory(Categories category, Long id) {
		Categories existingCategory = categoriesRepository.findById(id)
				.orElseThrow( ()-> new IllegalStateException("Category does not exist with id "+ id) );
		existingCategory.setOutstanding(category.isOutstanding());
		existingCategory.setSale(category.isSale());
		return categoriesRepository.save(existingCategory);
	}

	@Override
	public void deleteCategory(Long id) {
		
		Categories deletedCategory = categoriesRepository.findById(id)
				.orElseThrow( ()-> new IllegalStateException("Category does not exist with id "+ id) );
		   if (deletedCategory == null) {
		        throw new EntityNotFoundException("Category not found");
		    }

		    try {
		        categoriesRepository.delete(deletedCategory);
		    } catch (Exception e) {
		       
		        throw new RuntimeException("Failed to delete category: " + e.getMessage());
		    }
		
	}


    

}

