package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	private static final String FIND_ALL = "from Product";
	private static final String FIND_BY_NAME = "from Product where name = :name";
	private static final String FIND_BY_CATEGORY = "from Product where category.name = :category";
	private static final String FIND_BY_PRODUCT_ID = "from Product where productId = :productId";
	private static final String FIND_BY_NAME_OR_CATEGORY = "from Product where name like :pattern%";

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {

		List<Product> products = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		products = (List<Product>) session.createQuery(FIND_ALL).getResultList();

		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByCategory(Category category) {
		
		List<Product> products = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		products = (List<Product>) session.createQuery(FIND_BY_CATEGORY).setParameter("category", category.getName())
				.getResultList();

		return products;
	}

	@Override
	public Product findByName(String name) {

		Product product = null;

		Session session = sessionFactory.getCurrentSession();

		try {

			product = (Product) session.createQuery(FIND_BY_NAME).setParameter("name", name).getSingleResult();
		} catch (NoResultException e) {

		}

		return product;
	}

	@Override
	public Product findByProductId(String productId) {
		
		Product product = null;

		Session session = sessionFactory.getCurrentSession();

		try {

			product = (Product) session.createQuery(FIND_BY_PRODUCT_ID).setParameter("productId", productId).getSingleResult();
		} catch (NoResultException e) {

		}

		return product;
	}

	@Override
	public void save(Product product) {
		sessionFactory.getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) {
		sessionFactory.getCurrentSession().update(product);
	}

	@Override
	public void delete(Product product) {
		sessionFactory.getCurrentSession().delete(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByNameOrCategory(String name) {
		
		List<Product> products = new ArrayList<>();
		
		Session session = sessionFactory.getCurrentSession();
		
		products = session.createQuery(FIND_BY_NAME_OR_CATEGORY).setParameter("pattern", name).getResultList();
		
		return products;
	}
}
