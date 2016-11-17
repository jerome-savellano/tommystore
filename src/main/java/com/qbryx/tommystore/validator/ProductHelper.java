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
	
	private CommonsMultipartFile image;

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

	public CommonsMultipartFile getImage() {
		return image;
	}

	public void setImage(CommonsMultipartFile image) {
		this.image = image;
	}
	
	public Product buildProduct(CategoryService categoryService) throws CategoryNotFoundException{
		
		Product product = new Product();
		
		product.setName(name);
		product.setCategory(categoryService.findByName(category));
		product.setPrice(price);
		product.setImage(image.getBytes());
		
		return product;
	}

	@Override
	public String toString() {
		return "ProductHelper [name=" + name + ", category=" + category + ", price=" + price + ", image=" + image + "]";
	}
	
	
}
