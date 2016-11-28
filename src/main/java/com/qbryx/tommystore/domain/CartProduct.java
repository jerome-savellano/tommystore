package com.qbryx.tommystore.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "cart")
public class CartProduct {
	
	private long id; 
	
	private User user;
	
	private Product product;
	
	private int quantity;
	
	public static final int INITIAL_QUANTITY = 1;

	public CartProduct(){}
	
	public CartProduct(long id, User user, Product product, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}
	
	@Id @GeneratedValue
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "user_id")
	@Cascade(CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
	@JoinColumn(name="product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Transient
	public BigDecimal totalPrice(){
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		totalPrice = getProduct().getPrice().multiply(new BigDecimal(getQuantity()));
		
		return totalPrice;
	}

	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", userId=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}
}
