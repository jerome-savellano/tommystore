package com.qbryx.tommystore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Product product;
	
	private int quantity;
	
	private int stock;

	public CartProduct(){}
	
	public CartProduct(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal totalPrice(){
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		totalPrice = getProduct().getPrice().multiply(new BigDecimal(getQuantity()));
		
		return totalPrice;
	}

	public String toString() {
		return "CartProduct [product=" + product + ", quantity=" + quantity + "]";
	}
}
