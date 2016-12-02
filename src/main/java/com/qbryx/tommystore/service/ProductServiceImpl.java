package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.OrderDao;
import com.qbryx.tommystore.dao.ProductDao;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Order;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.util.DateUtil;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.ExistingOrderException;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productDao.findByCategory(category);
	}

	@Override
	public Product findByName(String name) throws ProductNotFoundException {
		
		Product product = productDao.findByName(name);
		
		if(product == null){
			throw new ProductNotFoundException();
		}
		
		return product;
	}

	@Override
	public Product findByProductId(String productId) {
		return productDao.findByProductId(productId);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Product product) throws DuplicateProductException {

		if (isProductExisting(product)) {
			throw new DuplicateProductException();
		}

		product.setProductId(generateProductId());
		productDao.save(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Product productToUpdate) throws DuplicateProductException {
		
		Product product = productDao.findByProductId(productToUpdate.getProductId());

		if (productDao.findByName(productToUpdate.getName()) != null
				&& !product.getName().equals(productToUpdate.getName())) {
			throw new DuplicateProductException();
		}

		product.setName(productToUpdate.getName());
		product.setCategory(productToUpdate.getCategory());
		product.setPrice(productToUpdate.getPrice());
		
		if(productToUpdate.getImage() != null){
			product.setImage(productToUpdate.getImage());
		}
	
		productDao.update(product);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = ExistingOrderException.class)
	public void delete(Product product) throws ExistingOrderException {
		if(orderDao.findByProduct(product) != null){
			
			throw new ExistingOrderException();
		}else{
			
			productDao.delete(product);
		}
	}

	private boolean isProductExisting(Product product) {
		return productDao.findByName(product.getName()) != null;
	}

	private String generateProductId() {

		String initialProductId = "PROD-" + DateUtil.timeStamp();
		String productId = "";

		while (productDao.findByProductId(initialProductId) != null) {
			initialProductId = "PROD-" + DateUtil.timeStamp();
		}

		productId = initialProductId;

		return productId;
	}

	@Override
	public List<Order> findAllOrders() {
		return orderDao.findAll();
	}
}
