package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.ExistingOrderException;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

public interface ProductService {

	List<Product> findAll();
	
	List<Product> findByCategory(Category category);
	
	List<Order> findAllOrders();

	Product findByName(String name) throws ProductNotFoundException;

	Product findByProductId(String ProductId);

	void save(Product Product) throws DuplicateProductException;

	void update(Product Product) throws DuplicateProductException;

	void delete(Product Product) throws ExistingOrderException;
}
