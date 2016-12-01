package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;

public interface ShippingAddressDao {
	
	List<ShippingAddress> findByUser(User user);
	
	void createShippingAddress(ShippingAddress shippingAddress);
}
