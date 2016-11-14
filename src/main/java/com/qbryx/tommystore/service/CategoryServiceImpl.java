package com.qbryx.tommystore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbryx.tommystore.dao.CategoryDao;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Category findByName(String name) {
		return categoryDao.findByName(name);
	}

	@Override
	public void createCategory(Category category) throws DuplicateCategoryException {
		
		if(isCategoryExisting(category)){
			throw new DuplicateCategoryException();	
		}
		
		categoryDao.createCategory(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDao.deleteCategory(category);
	}

	private boolean isCategoryExisting(Category category){
		return categoryDao.findByName(category.getName()) != null;
	}

	@Override
	public String generateCategoryId() {
		
		String dateAndTimeOfCreation = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		
		return "CAT-" + dateAndTimeOfCreation;
	}
}
