package com.qbryx.tommystore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.qbryx.tommystore.domain.Inventory;

@Component
public class InventoryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Inventory.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Inventory inventory = (Inventory) target;
		
		if(inventory.getStock() <= 0){
			errors.rejectValue("stock", "error.invalidstock.inventory.stock");
		}
	}
}
