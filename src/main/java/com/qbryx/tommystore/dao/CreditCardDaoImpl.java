package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.User;

@Repository("creditCard")
public class CreditCardDaoImpl implements CreditCardDao{
	
	private static final String FIND_BY_USER = "from CreditCard where user = :user";
	private static final String FIND_BY_ID = "from CreditCard where id = :id";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CreditCard> findByUser(User user) {
		
		List<CreditCard> creditCards = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		creditCards = session.createQuery(FIND_BY_USER).setParameter("user", user).getResultList();
		
		return creditCards;
	}
	
	@Override
	public CreditCard findById(long id) {
		
		CreditCard creditCard = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		creditCard = (CreditCard) session.createQuery(FIND_BY_ID).setParameter("id", id).getSingleResult();
				
		
		return creditCard;
	}

	@Override
	public void save(CreditCard creditCard) {
		sessionFactory.getCurrentSession().save(creditCard);
	}
}
