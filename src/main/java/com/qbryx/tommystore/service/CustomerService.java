package com.qbryx.tommystore.service;

import java.util.List;
import java.util.Map;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystrore.exception.InsufficientStockException;
import com.qbryx.tommystrore.exception.InvalidStockException;

public interface CustomerService {
	
	List<ShippingAddress> findShippingAddresses(User user);
	
	List<CreditCard> findCreditCards(User user);
		
	ShippingAddress findShippingAddressById(long id);
	
	CreditCard findCreditCardById(long id);
	
	void checkOut(User user, ShippingAddress shippingAddress, Map<String, CartProduct> cart) throws InsufficientStockException, InvalidStockException;
	
	void saveShippingAddress(ShippingAddress shippingAddress);
	
	void saveCreditCard(CreditCard creditCard);
	
	void saveOrder(Order order);
}
