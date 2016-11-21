package com.qbryx.tommystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.ProductDao;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
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
	public void createProduct(Product product) throws DuplicateProductException {

		if (isProductExisting(product)) {
			throw new DuplicateProductException();
		}

		product.setProductId(generateProductId());
		productDao.createProduct(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateProduct(Product productToUpdate) throws DuplicateProductException {

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
	
		productDao.updateProduct(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
	}

	private boolean isProductExisting(Product product) {
		return productDao.findByName(product.getName()) != null;
	}

	private String generateProductId() {

		String initialProductId = "PROD-" + UUID.randomUUID().toString().replaceAll("[a-zA-Z-]", "");
		String productId = "";

		while (productDao.findByProductId(initialProductId) != null) {
			initialProductId = "PROD-" + UUID.randomUUID().toString().replaceAll("[a-zA-Z-]", "");
		}

		productId = initialProductId;

		return productId;
	}
}
