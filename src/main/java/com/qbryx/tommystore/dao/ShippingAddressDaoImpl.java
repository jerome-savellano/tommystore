package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;

@Repository("shippingAddressDao")
public class ShippingAddressDaoImpl implements ShippingAddressDao{
	
	private static final String FIND_BY_USER = "from ShippingAddress where user = :user";
	private static final String FIND_BY_ID = "from ShippingAddress where id = :id";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingAddress> findByUser(User user) {
		
		List<ShippingAddress> shippingAddresses = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		shippingAddresses = (List<ShippingAddress>) session.createQuery(FIND_BY_USER)
								                           .setParameter("user", user).getResultList();
				
		return shippingAddresses;
	}
	
	@Override
	public ShippingAddress findById(long id) {

		ShippingAddress shippingAddress = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		shippingAddress = (ShippingAddress) session.createQuery(FIND_BY_ID).setParameter("id", id).getSingleResult();
		
		return shippingAddress;
	}

	@Override
	public void save(ShippingAddress shippingAddress) {
		sessionFactory.getCurrentSession().save(shippingAddress);
	}
}
