package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;

public interface CartProductService {
	
	List<CartProduct> findByUser(User user);
	
	CartProduct findByProduct(Product product);

	void createCartProduct(CartProduct cartProduct);
	void deleteCartProduct(CartProduct cartProduct);
}
