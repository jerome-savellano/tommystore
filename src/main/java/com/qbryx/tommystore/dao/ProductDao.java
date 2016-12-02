package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;

public interface ProductDao {
	
	List<Product> findAll();
	
	List<Product> findByCategory(Category category);
	
	List<Product> findByNameOrCategory(String name);
	
	Product findByName(String name);
	
	Product findByProductId(String productId);
	
	void save(Product product);
	
	void update(Product product);
	
	void delete(Product product);
}
