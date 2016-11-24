package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.InventoryDao;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.InventoryHistory;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.StockMonitor;
import com.qbryx.tommystore.util.DateHelper;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

@Service("inventoryService")
@Transactional(readOnly=true)
public class InventoryServiceImpl implements InventoryService{
	
	@Autowired
	private InventoryDao inventoryDao;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Inventory> findAll() {
		return inventoryDao.findAll();
	}
	
	@Override
	public List<Inventory> findAllInStock() {
		return inventoryDao.findAllInStock();
	}
	
	@Override
	public Inventory findByProduct(Product product) {
		return inventoryDao.findByProduct(product);
	}

	@Transactional(readOnly=false)
	@Override
	public void createInventory(Inventory inventory) {
		inventoryDao.createInventory(inventory);
		createInventoryHistory(new InventoryHistory(inventory));
	}

	@Transactional(readOnly=false)
	@Override
	public void updateInventory(Inventory inventory) {
		
		Inventory inventoryUpdate = findById(inventory.getId());
		
		inventoryUpdate.setUpdater(userService.findByEmail(inventory.getUpdater().getEmail()));
		inventoryUpdate.setDateUpdated(DateHelper.now());
		inventoryUpdate.setStock(inventory.getStock());
		
		inventoryDao.updateInventory(inventoryUpdate);
		createInventoryHistory(new InventoryHistory(inventoryUpdate));
	}
	
	@Override
	public StockMonitor findStockMonitor() {
		return inventoryDao.findStockMonitor();
	}
	
	@Override
	public List<Inventory> findByStock(StockMonitor stockMonitor) {
		return inventoryDao.findByStock(stockMonitor);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteInventory(Inventory inventory) {
		inventoryDao.deleteInventory(inventory);
	}

	@Override
	public List<Inventory> findByCategory(Category category) {
		return inventoryDao.findByCategory(category);
	}

	@Override
	public Inventory findById(long id) {
		return inventoryDao.findById(id);
	}

	@Transactional(readOnly=false)
	@Override
	public void updateStockMonitor(StockMonitor stockMonitor) {
		inventoryDao.updateStockMonitor(stockMonitor);
	}
	
	/*
	 * 
	 * Inventory history
	 * 
	 */
	
	@Override
	public List<InventoryHistory> findAllInventoryHistory() {
		return inventoryDao.findAllInventoryHistory();
	}
	
	@Override
	public List<InventoryHistory> findInventoryHistoryByProductId(String productId) throws ProductNotFoundException {
		
		List<InventoryHistory> inventoryHistories = inventoryDao.findInventoryHistoryByProductId(productId);
		
		if(inventoryHistories.size() == 0){
			throw new ProductNotFoundException();
		}
		
		return inventoryHistories;
	}

	@Transactional(readOnly=false)
	@Override
	public void createInventoryHistory(InventoryHistory inventoryHistory) {
		inventoryDao.createInventoryHistory(inventoryHistory);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteInventoryHistory() {
		inventoryDao.deleteInventoryHistory();
	}
}
