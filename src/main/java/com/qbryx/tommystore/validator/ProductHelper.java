package com.qbryx.tommystore.validator;

import java.math.BigDecimal;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;

public class ProductHelper {
	
	private String productId;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

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
	
	public Product buildProductToUpdate(ProductService productService, CategoryService categoryService) throws CategoryNotFoundException{
		
		Product product = productService.findByProductId(productId);
		
		product.setName(name);
		product.setCategory(categoryService.findByName(category));
		product.setPrice(price);
		
		if(image.getSize() > 0){
			product.setImage(image.getBytes());
		}
		
		return product;
	}

	public static ProductHelper buildProductHelper(Product product){
		
		ProductHelper productHelper = new ProductHelper();
		
		productHelper.setProductId(product.getProductId());
		productHelper.setName(product.getName());
		productHelper.setCategory(product.getCategory().getName());
		productHelper.setPrice(product.getPrice());
		
		return productHelper;
	}

	@Override
	public String toString() {
		return "ProductHelper [name=" + name + ", category=" + category + ", price=" + price + ", image=" + image + "]";
	}
	
	
}
