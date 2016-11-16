package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

public interface ProductService {

	List<Product> findAll();
	
	List<Product> findByCategory(Category category);

	Product findByName(String name) throws ProductNotFoundException;

	Product findByProductId(String ProductId);

	void createProduct(Product Product) throws DuplicateProductException;

	void updateProduct(Product Product) throws DuplicateProductException;

	void deleteProduct(Product Product);
}
