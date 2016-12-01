package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;

public interface CustomerService {
	
	List<ShippingAddress> findShippingAddresses(User user);
	
	void createShippingAddress(ShippingAddress shippingAddress);
}
