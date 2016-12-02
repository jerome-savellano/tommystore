package com.qbryx.tommystore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.tommystore.domain.ShippingAddress;

@Component
public class ShippingAddressValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ShippingAddress.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "error.empty.shippingaddress.address1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "error.empty.shippingaddress.address2");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zipCode", "error.empty.shippingaddress.zipCode");
		
		ShippingAddress shippingAddress = (ShippingAddress) target;
		
		if(!isZipCodeValid(shippingAddress)){
			errors.rejectValue("zipCode", "error.invalidformat.shippingaddress.zipCode");
		}
	}

	private boolean isZipCodeValid(ShippingAddress shippingAddress){
		
		String zipCode = shippingAddress.getZipCode();
		
		return zipCode.matches("[0-9]+");
	}
}
