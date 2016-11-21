package com.qbryx.tommystore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.Product;

@Repository("inventoryDao")
public class InventoryDaoImpl implements InventoryDao {

	private static final String FIND_ALL = "from Inventory";
	private static final String FIND_BY_CATEGORY = "from Inventory where product.category.name = :categoryName";
	private static final String FIND_BY_PRODUCT = "from Inventory where product = :product";
	private static final String FIND_BY_ID = "from Inventory where id = :id";
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findAll() {

		List<Inventory> inventories = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		inventories = (List<Inventory>) session.createQuery(FIND_ALL).getResultList();

		return inventories;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inventory> findByCategory(Category category) {

		List<Inventory> inventories = new ArrayList<>();

		Session session = sessionFactory.getCurrentSession();

		inventories = (List<Inventory>) session.createQuery(FIND_BY_CATEGORY)
				.setParameter("categoryName", category.getName()).getResultList();

		return inventories;
	}

	@Override
	public Inventory findByProduct(Product product) {

		Inventory inventory = null;

		Session session = sessionFactory.getCurrentSession();

		inventory = (Inventory) session.createQuery(FIND_BY_PRODUCT)
				.setParameter("product", product).getSingleResult();

		return inventory;
	}
	
	@Override
	public Inventory findById(long id) {
		Inventory inventory = null;

		Session session = sessionFactory.getCurrentSession();

		inventory = (Inventory) session.createQuery(FIND_BY_ID)
				.setParameter("id", id).getSingleResult();

		return inventory;
	}

	@Override
	public void createInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().save(inventory);
	}

	@Override
	public void updateInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().update(inventory);
	}

	@Override
	public void deleteInventory(Inventory inventory) {
		sessionFactory.getCurrentSession().delete(inventory);
	}
}
