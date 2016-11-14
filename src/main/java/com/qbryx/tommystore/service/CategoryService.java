package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;

public interface CategoryService {

	List<Category> findAll();
	
	Category findByName(String name);
	
	String generateCategoryId();
	
	void createCategory(Category category) throws DuplicateCategoryException;
	void updateCategory(Category category);
	void deleteCategory(Category category);
}
