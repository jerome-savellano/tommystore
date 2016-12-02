package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.Product;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao{
	
	private static final String FIND_BY_PRODUCT = "from Order where product = :product";
	private static final String FIND_ALL = "from Order order by orderDate";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findByProduct(Product product) {
		
		List<Order> order = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		order = session.createQuery(FIND_BY_PRODUCT).setParameter("product", product).getResultList();
		
		return order;
	}

	@Override
	public void save(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		
		List<Order> orders = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		orders = session.createQuery(FIND_ALL).getResultList();
		
		return orders;
	}
}
