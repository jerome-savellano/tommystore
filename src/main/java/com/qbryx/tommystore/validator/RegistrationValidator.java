package com.qbryx.tommystore.validator;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.empty.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.empty.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.empty.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNumber", "error.empty.contactNumber");
		
		RegisterUser user = (RegisterUser) target;

		if (!new EmailValidator().isValid(user.getEmail(), null)) {
			errors.rejectValue("email", "error.format.email");
		}
		
		if(!user.getPassword().isEmpty() && user.getConfirmPassword().isEmpty()){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.empty.confirmPassword");
		}

		if (invalidName(user.getFirstName())) {
			errors.rejectValue("firstName", "error.format.firstName");
		}

		if (invalidName(user.getLastName())) {
			errors.rejectValue("lastName", "error.format.lastName");
		}

		if (invalidContactNumber(user.getContactNumber())) {
			errors.rejectValue("contactNumber", "error.format.contactNumber");
		}
		
		if(!user.getPassword().equals(user.getConfirmPassword())){
			errors.rejectValue("password", "error.mismatch.password");
			errors.rejectValue("confirmPassword", "error.mismatch.confirmPassword");
		}
	}
	
	private boolean invalidName(String name){
		return !name.isEmpty() && !name.matches("[a-zA-Z ]+");
	}
	
	private boolean invalidContactNumber(String contactNumber){
		return !contactNumber.isEmpty() && !contactNumber.matches("[0-9]+");
	}
}
