package com.qbryx.tommystore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="inventory_history")
public class InventoryHistory {
	
	private long id;
	
	private Inventory inventory;
	
	public InventoryHistory(){}

	public InventoryHistory(Inventory inventory) {
		super();
		this.inventory = inventory;
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="inventory_id")
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}	
}
