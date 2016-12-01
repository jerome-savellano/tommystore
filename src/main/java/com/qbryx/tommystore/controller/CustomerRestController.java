package com.qbryx.tommystore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.util.CartHelper;
import com.qbryx.tommystore.util.Constants;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartHelper cartHelper;
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	public CartProduct addToCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
		
		cartProduct.setQuantity(Constants.INITIAL_QUANTITY_IN_CART);
		cartProduct.setProduct(productService.findByProductId(cartProduct.getProduct().getProductId()));
		
		cartHelper.addProductToCart(request, cartProduct);
		
		return cartProduct;
	}
	
	@RequestMapping(value="/removeFromCart", method = RequestMethod.POST)
	public int removeFromCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
	
		cartHelper.removeProductFromCart(request, cartProduct);
		return cartHelper.getCartSize(request);
	}
}
