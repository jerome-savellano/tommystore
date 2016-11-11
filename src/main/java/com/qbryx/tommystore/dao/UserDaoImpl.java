package com.qbryx.tommystore.dao;

import javax.persistence.NoResultException;
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
		
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
				
		Query query = session.createQuery(GET_USER)
							 .setParameter("email", email);
		
		try{
			
			user = (User) query.getSingleResult();
		}catch(NoResultException e){
			
		}

		return user;
	}

	@Override
	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
}
