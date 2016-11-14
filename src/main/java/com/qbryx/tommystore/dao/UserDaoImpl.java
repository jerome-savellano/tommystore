package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	private static final String FIND_BY_EMAIL = "from User where email = :email";
	private static final String FIND_ALL = "from User";
	private static final String FIND_BY_USER_TYPE = "from User where userType = :userType";
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByEmail(String email) {
		
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
				
		Query query = session.createQuery(FIND_BY_EMAIL)
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		
		List<User> users = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		users = session.createQuery(FIND_ALL).getResultList();
			
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByType(UserType userType) {
		
		List<User> users = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		users = session.createQuery(FIND_BY_USER_TYPE)
					   .setParameter("userType", userType)
					   .getResultList();
		
		return users;
	}
}
