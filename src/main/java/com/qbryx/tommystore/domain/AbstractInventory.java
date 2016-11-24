package com.qbryx.tommystore.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class AbstractInventory {

	private long id;

	private User updater;

	private Product product;

	private int stock;

	private String dateUpdated;

	@Id
	@GeneratedValue
	@Column(name="id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	@Column(name="last_updated")
	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
}
