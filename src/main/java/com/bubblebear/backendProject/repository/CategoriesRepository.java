package com.bubblebear.backendProject.repository;


import org.springframework.data.repository.CrudRepository;
import com.bubblebear.backendProject.entity.Categories;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {

	Categories findById(long id);
}
