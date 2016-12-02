package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.InventoryHistory;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.StockMonitor;

public interface InventoryDao {
	
	List<Inventory> findAll();
	
	List<Inventory> findAllInStock();
	
	List<Inventory> findByCategory(Category category);
	
	List<Inventory> findByStock(StockMonitor stockMonitor);
	
	Inventory findByProduct(Product product);
	
	Inventory findById(long id);
	
	StockMonitor findStockMonitor();
	
	void saveStockMonitor(StockMonitor stockMonitor);
	
	void updateStockMonitor(StockMonitor stockMonitor);
	
	void save(Inventory inventory);
	void update(Inventory inventory);
	void delete(Inventory inventory);
	
	/*
	 * 
	 * Inventory history
	 * 
	 */
	
	List<InventoryHistory> findAllInventoryHistory();
	
	List<InventoryHistory> findInventoryHistoryByProductId(String productId);
	
	void saveInventoryHistory(InventoryHistory inventoryHistory);
}
