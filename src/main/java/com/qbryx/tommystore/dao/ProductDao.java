package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;

public interface ProductDao {
	
	List<Product> findAll();
	
	List<Product> findByCategory(Category category);
	
	Product findByName(String name);
	
	Product findByProductId(String productId);
	
	void createProduct(Product product);
	
//	void updateProduct(Product product);
	
	void deleteProduct(Product product);
}
