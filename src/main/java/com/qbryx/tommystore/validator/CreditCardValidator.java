package com.qbryx.tommystore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.qbryx.tommystore.domain.CreditCard;

@Component
public class CreditCardValidator implements Validator{
	
	private static final int CARD_NUMBER_LENGTH = 10;
	private static final int SECURITY_CODE_LENGTH = 5;

	@Override
	public boolean supports(Class<?> clazz) {
		return CreditCard.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "error.empty.creditcard.cardnumber");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "securityCode", "error.empty.creditcard.securitycode");
		
		CreditCard creditCard = (CreditCard) target;
		
		if(!isCartNumberValid(creditCard) || !isSecurityCodeValid(creditCard)){
			System.out.println("may error");
			errors.rejectValue("cardNumber", "error.invalidformat.creditcard.cardnumber");
		}
	}

	private boolean isCartNumberValid(CreditCard creditCard){
		
		String cardNumber = creditCard.getCardNumber();
		
		return cardNumber.matches("[0-9]+") && cardNumber.length() == CARD_NUMBER_LENGTH;
	}
	
	private boolean isSecurityCodeValid(CreditCard creditCard){
		
		String securityCode = creditCard.getSecurityCode();
		
		return securityCode.matches("[0-9]+") && securityCode.length() == SECURITY_CODE_LENGTH;
	}
}
