package com.qbryx.tommystore.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="INVENTORY")
public class Inventory extends AbstractInventory{
	
	public static final int INITIAL_STOCK = 0;
	
	@Override
	public String toString() {
		return "Inventory [id=" + getId() + ", product=" + getProduct() + ", stock=" + getStock() + ", updater=" + getUpdater()
				+ ", dateUpdated=" + getDateUpdated() + "]";
	}
}
