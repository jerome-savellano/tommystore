package com.qbryx.tommystore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.service.CustomerService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.util.CartHelper;
import com.qbryx.tommystore.util.Constants;
import com.qbryx.tommystore.util.JsonResponse;
import com.qbryx.tommystore.validator.CreditCardValidator;
import com.qbryx.tommystore.validator.ShippingAddressValidator;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShippingAddressValidator shippingAddressValidator;

	@Autowired
	private CreditCardValidator creditCardValidator;

	@Autowired
	private CartHelper cartHelper;

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public CartProduct addToCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request) {

		cartProduct.setProduct(productService.findByProductId(cartProduct.getProduct().getProductId()));

		cartHelper.addProductToCart(request, cartProduct);

		return cartProduct;
	}

	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public int removeFromCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request) {

		cartHelper.removeProductFromCart(request, cartProduct);
		return cartHelper.getCartSize(request);
	}

	@RequestMapping(value = "/addShippingAddress", method = RequestMethod.POST)
	public JsonResponse addShippingAddress(@Validated @ModelAttribute ShippingAddress shippingAddress,
			BindingResult bindingResult) {

		JsonResponse response = new JsonResponse();

		shippingAddressValidator.validate(shippingAddress, bindingResult);

		if (bindingResult.hasErrors()) {

			response.setResult(Constants.STATUS_ERROR);
		} else {
			shippingAddress.setUser(userService.findByEmail(shippingAddress.getUser().getEmail()));

			customerService.saveShippingAddress(shippingAddress);

			response.setResult(Constants.STATUS_OK);
		}

		response.setResult(shippingAddress);
		return response;
	}

	@RequestMapping(value = "/addCreditCard", method = RequestMethod.POST)
	public JsonResponse addCreditCard(@Validated @ModelAttribute CreditCard creditCard, BindingResult bindingResult) {

		JsonResponse response = new JsonResponse();
		
		creditCardValidator.validate(creditCard, bindingResult);

		if (bindingResult.hasErrors()) {

			response.setStatus(Constants.STATUS_ERROR);
		} else {
			creditCard.setUser(userService.findByEmail(creditCard.getUser().getEmail()));

			customerService.saveCreditCard(creditCard);

			response.setStatus(Constants.STATUS_OK);
		}

		response.setResult(creditCard);
		return response;
	}
}
