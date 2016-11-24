package com.qbryx.tommystore.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="inventory_history")
public class InventoryHistory extends AbstractInventory{
	
	public InventoryHistory(){}
	
	public InventoryHistory(Inventory inventory){
		setProduct(inventory.getProduct());
		setStock(inventory.getStock());
		setUpdater(inventory.getUpdater());
		setDateUpdated(inventory.getDateUpdated());
	}

	@Override
	public String toString() {
		return "InventoryHistory [getId()=" + getId() + ", getProduct()=" + getProduct() + ", getStock()=" + getStock()
				+ ", getUpdater()=" + getUpdater() + ", getDateUpdated()=" + getDateUpdated() + "]";
	}
}
