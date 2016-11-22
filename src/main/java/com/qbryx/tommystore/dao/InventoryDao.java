package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.StockMonitor;

public interface InventoryDao {
	
	List<Inventory> findAll();
	
	List<Inventory> findByCategory(Category category);
	
	List<Inventory> findByStock(StockMonitor stockMonitor);
	
	Inventory findByProduct(Product product);
	
	Inventory findById(long id);
	
	StockMonitor findStockMonitor();
	
	void updateStockMonitor(StockMonitor stockMonitor);
	
	void createInventory(Inventory inventory);
	void updateInventory(Inventory inventory);
	void deleteInventory(Inventory inventory);
}
