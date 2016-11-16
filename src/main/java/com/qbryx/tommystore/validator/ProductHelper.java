package com.qbryx.tommystore.validator;

import java.math.BigDecimal;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;

public class ProductHelper {
	
	private String name;
	
	private String category;
	
	private BigDecimal price;
	
	private CommonsMultipartFile imageFile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public CommonsMultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(CommonsMultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	public Product buildProduct(CategoryService categoryService) throws CategoryNotFoundException{
		
		Product product = new Product();
		
		product.setName(name);
		product.setCategory(categoryService.findByName(category));
		product.setPrice(price);
		product.setImage(imageFile.getBytes());
		
		return product;
	}
}
