package com.qbryx.tommystore.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private static final String GET_USER = "from User where email = :email";
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUser(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		System.out.println(email);
		Query query = session.createQuery(GET_USER)
							 .setParameter("email", email);
		
		return (User) query.getSingleResult();
	}
}
