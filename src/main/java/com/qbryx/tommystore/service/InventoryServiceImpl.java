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
import com.qbryx.tommystore.util.DateUtil;
import com.qbryx.tommystrore.exception.ExistingOrderException;
import com.qbryx.tommystrore.exception.InvalidStockException;
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
	public void save(Inventory inventory) {
		inventoryDao.save(inventory);
		saveInventoryHistory(new InventoryHistory(inventory));
	}

	@Transactional(readOnly=false)
	@Override
	public void updateRestock(Inventory inventoryUpdate) throws InvalidStockException {
		
		Inventory inventory = findById(inventoryUpdate.getId());
		
		int stockAdded = inventoryUpdate.getStock() - inventory.getStock();
		
		if(inventoryUpdate.getStock() <= inventory.getStock()){
			throw new InvalidStockException();
		}
		
		inventory.setUpdater(userService.findByEmail(inventoryUpdate.getUpdater().getEmail()));
		inventory.setDateUpdated(DateUtil.now());
		inventory.setStock(inventoryUpdate.getStock());
		
		inventoryDao.update(inventory);
		
		InventoryHistory inventoryHistory = new InventoryHistory(inventory);
		inventoryHistory.setStock(stockAdded);
		
		saveInventoryHistory(inventoryHistory);
	}
	
	@Transactional(readOnly=false)
	@Override
	public void updatePurchase(Inventory inventoryPurchase) {
	
		inventoryDao.update(inventoryPurchase);
	}

	@Override
	public StockMonitor findStockMonitor() {
		return inventoryDao.findStockMonitor();
	}
	
	@Override
	public List<Inventory> findByStock(StockMonitor stockMonitor) {
		return inventoryDao.findByStock(stockMonitor);
	}

	@Transactional(readOnly=false, rollbackFor=ExistingOrderException.class)
	@Override
	public void delete(Inventory inventory) {
		inventoryDao.delete(inventory);
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
	public void saveStockMonitor(StockMonitor stockMonitor) {
		inventoryDao.saveStockMonitor(stockMonitor);
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
	public void saveInventoryHistory(InventoryHistory inventoryHistory) {
		inventoryDao.saveInventoryHistory(inventoryHistory);
	}
}
