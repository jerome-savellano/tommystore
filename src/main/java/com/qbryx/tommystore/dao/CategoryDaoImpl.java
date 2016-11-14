package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.Category;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {
	
	private static final String FIND_ALL = "from Category";
	private static final String FIND_BY_NAME = "from Category where name = :name";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		
		List<Category> categories = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		categories = session.createQuery(FIND_ALL).getResultList();
		
		return categories;
	}

	@Override
	public Category findByName(String name) {
		
		Category category = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try{
			
			category = (Category) session.createQuery(FIND_BY_NAME)
										 .setParameter("name", name).getSingleResult();
		}catch(NoResultException e){
			
		}
		
		return category;
	}

	@Override
	public void createCategory(Category category){
		sessionFactory.getCurrentSession().save(category);
	}

	@Override
	public void updateCategory(Category category) {
		sessionFactory.getCurrentSession().update(category);
	}

	@Override
	public void deleteCategory(Category category) {
		sessionFactory.getCurrentSession().delete(category);
	}
}
