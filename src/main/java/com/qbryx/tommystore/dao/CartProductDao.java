package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;

public interface CartProductDao {
	
	List<CartProduct> findByUser(User user);
	
	CartProduct findByProduct(Product product);

	void createCartProduct(CartProduct cartProduct);
	void updateCartProduct(CartProduct cartProduct);
	void deleteCartProduct(CartProduct cartProduct);
}
