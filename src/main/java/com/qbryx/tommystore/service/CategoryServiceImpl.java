package com.qbryx.tommystore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.CategoryDao;
import com.qbryx.tommystore.dao.ProductDao;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystrore.exception.CategoryHasProductsException;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;

@Service("categoryService")
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findByName(String name) throws CategoryNotFoundException {

		Category category = categoryDao.findByName(name);

		if (category == null) {
			throw new CategoryNotFoundException();
		}

		return category;
	}

	@Override
	public Category findByCategoryId(String categoryId) {
		return categoryDao.findByCategoryId(categoryId);
	}

	@Override
	@Transactional(readOnly = false)
	public void createCategory(Category category) throws DuplicateCategoryException {

		if (isCategoryExisting(category)) {
			throw new DuplicateCategoryException();
		}

		category.setCategoryId(generateCategoryId());
		categoryDao.createCategory(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCategory(Category categoryToUpdate) throws DuplicateCategoryException {

		Category category = categoryDao.findByCategoryId(categoryToUpdate.getCategoryId());

		if (categoryDao.findByName(categoryToUpdate.getName()) != null
				&& !category.getName().equals(categoryToUpdate.getName())) {
			throw new DuplicateCategoryException();
		}
		
		category.setName(categoryToUpdate.getName());
		categoryDao.updateCategory(category);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteCategory(Category category) throws CategoryHasProductsException{
		
		if(productDao.findByCategory(category).size() != 0){
			throw new CategoryHasProductsException();
		}
		
		categoryDao.deleteCategory(category);
	}

	private boolean isCategoryExisting(Category category){
		System.out.println(category.getName());
		return categoryDao.findByName(category.getName()) != null;
	}

	private String generateCategoryId() {

		String initialCategoryId = "CAT-" + UUID.randomUUID().toString().replaceAll("[a-zA-Z-]", "");
		String categoryId = "";

		while (categoryDao.findByCategoryId(initialCategoryId) != null) {
			initialCategoryId = "CAT-" + UUID.randomUUID().toString().replaceAll("[a-zA-Z-]", "");
		}

		categoryId = initialCategoryId;

		return categoryId;
	}
}
