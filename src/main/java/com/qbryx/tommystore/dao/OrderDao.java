package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.Product;

public interface OrderDao {
	
	List<Order> findAll();
	
	List<Order> findByProduct(Product product);
	
	void save(Order order);
}
