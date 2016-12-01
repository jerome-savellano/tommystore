package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbryx.tommystore.dao.ShippingAddressDao;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private ShippingAddressDao shippingAddressdao;

	@Override
	public List<ShippingAddress> findShippingAddresses(User user){
		return shippingAddressdao.findByUser(user);
	}

	@Override
	public void createShippingAddress(ShippingAddress shippingAddress) {
		shippingAddressdao.createShippingAddress(shippingAddress);
	}

}
