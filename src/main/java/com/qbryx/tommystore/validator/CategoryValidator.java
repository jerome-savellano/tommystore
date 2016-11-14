package com.qbryx.tommystore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.tommystore.domain.Category;

@Component
public class CategoryValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.empty.category.name");
		
		Category category = (Category) target;
		
		if(invalidName(category.getName())){
			errors.rejectValue("name", "error.invalidformat.category.name");
		}
	}

	private boolean invalidName(String name){
		return !name.isEmpty() && !name.matches("[0-9a-zA-Z ]+");
	}
}
