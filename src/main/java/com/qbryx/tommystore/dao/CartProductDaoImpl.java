package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;

@Repository("cartProductDao")
public class CartProductDaoImpl implements CartProductDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String FIND_BY_USER = "from CartProduct where user = :user"; 
	private static final String FIND_BY_PRODUCT = "from CartProduct where product = :product";

	@SuppressWarnings("unchecked")
	@Override
	public List<CartProduct> findByUser(User user) {
		
		List<CartProduct> cartProducts = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		cartProducts = session.createQuery(FIND_BY_USER)
							  .setParameter("user", user)
							  .getResultList();
		
		return cartProducts;
	}
	
	@Override
	public CartProduct findByProduct(Product product) {
		
		CartProduct cartProduct = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try{
			
			cartProduct = (CartProduct) session.createQuery(FIND_BY_PRODUCT).setParameter("product", product).getSingleResult();
		}catch(NoResultException e){
			
		}
		
		return cartProduct;
	}

	@Override
	public void createCartProduct(CartProduct cartProduct) {
		sessionFactory.getCurrentSession().save(cartProduct);
	}

	@Override
	public void updateCartProduct(CartProduct cartProduct) {
		sessionFactory.getCurrentSession().merge(cartProduct);
	}

	@Override
	public void deleteCartProduct(CartProduct cartProduct) {
		sessionFactory.getCurrentSession().delete(cartProduct);
	}
}
