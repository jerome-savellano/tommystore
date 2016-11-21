package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.Product;

public interface InventoryDao {
	
	List<Inventory> findAll();
	
	List<Inventory> findByCategory(Category category);
	
	Inventory findByProduct(Product product);
	
	Inventory findById(long id);
	
	void createInventory(Inventory inventory);
	void updateInventory(Inventory inventory);
	void deleteInventory(Inventory inventory);
}
