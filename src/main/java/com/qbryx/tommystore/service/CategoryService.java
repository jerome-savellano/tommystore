package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;

public interface CategoryService {

	List<Category> findAll();
	
	Category findByName(String name) throws CategoryNotFoundException;
	
	Category findByCategoryId(String categoryId);
	
	void createCategory(Category category) throws DuplicateCategoryException;
	void updateCategory(Category category) throws DuplicateCategoryException;
	void deleteCategory(Category category);
}
