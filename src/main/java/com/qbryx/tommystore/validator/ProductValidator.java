package com.qbryx.tommystore.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qbryx.tommystore.util.ProductHelper;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProductHelper.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.empty.product.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "error.empty.product.category");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.empty.product.price");

		ProductHelper productHelper = (ProductHelper) target;

		if (invalidFileSize(productHelper.getImage())) {
			errors.rejectValue("image", "error.empty.product.imageFile");
		}

		if (invalidFileType(productHelper.getImage())) {
			errors.rejectValue("image", "error.invalidformat.product.imageFile");
		}
		
		if(invalidName(productHelper.getName())){
			errors.rejectValue("name", "error.invalidformat.product.name");
		}
		
		try{
			
			if(productHelper.getPrice().scale() > 2 || productHelper.getPrice().compareTo(BigDecimal.ZERO) <= 0){
				errors.rejectValue("price", "error.invalidformat.product.price");
			}
		}catch(NullPointerException e){
			
		}
	}
	
	private boolean invalidFileType(CommonsMultipartFile file){
		
		String fileType = file.getContentType();
				
		return !(fileType.equals("image/png") || fileType.equals("image/jpeg") || fileType.equals("image/jpg")) && file.getSize() != 0;
	}
	
	private boolean invalidFileSize(CommonsMultipartFile file){
		return (file.getSize() == 0);
	}
	
	private boolean invalidName(String name){
		return !name.matches("[a-zA-Z0-9 ]+") && !name.isEmpty();
	}
}
