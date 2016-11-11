package com.qbryx.tommystore.validator;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.tommystore.domain.User;

@Component
public class RegistrationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.empty.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.empty.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.empty.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.empty.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNumber", "error.empty.contactNumber");

		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String contactNumber = user.getContactNumber();

		if (!new EmailValidator().isValid(email, null)) {
			errors.rejectValue("email", "error.format.email");
		}

		if (invalidName(firstName)) {
			errors.rejectValue("firstName", "error.format.firstName");
		}

		if (invalidName(lastName)) {
			errors.rejectValue("lastName", "error.format.lastName");
		}

		if (invalidContactNumber(contactNumber)) {
			errors.rejectValue("contactNumber", "error.format.contactNumber");
		}
	}
	
	private boolean invalidName(String name){
		return !name.isEmpty() && !name.matches("[a-zA-Z ]+");
	}
	
	private boolean invalidContactNumber(String contactNumber){
		return !contactNumber.isEmpty() && !contactNumber.matches("[0-9]+");
	}
}
