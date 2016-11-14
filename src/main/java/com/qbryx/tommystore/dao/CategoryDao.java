package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;

public interface CategoryDao {
	
	List<Category> findAll();
	
	Category findByName(String name);
	
	void createCategory(Category category);
	
	void updateCategory(Category category);
	
	void deleteCategory(Category category);
}
