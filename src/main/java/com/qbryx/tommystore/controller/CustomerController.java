package com.qbryx.tommystore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.Country;
import com.qbryx.tommystore.enums.CustomerPage;
import com.qbryx.tommystore.enums.PaymentType;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.CustomerService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystore.util.CartHelper;
import com.qbryx.tommystore.util.Constants;
import com.qbryx.tommystore.util.UserPaymentType;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.InsufficientStockException;
import com.qbryx.tommystrore.exception.InvalidStockException;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CartHelper cartHelper;
	
	/*
	 * 
	 * Home
	 *
	 */

	@RequestMapping("/home")
	public String home(HttpServletRequest request, Model model) {

		cartHelper.createCart(request);
				
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("inventories", inventoryService.findAllInStock());
		model.addAttribute("cartProduct", new CartProduct());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.HOME);
		return "customer_home";
	}

	/*
	 * 
	 * View products
	 * 
	 */

	@RequestMapping("/viewProducts")
	public String viewProduct(@RequestParam("category") String categoryName, Model model) {

		try {

			Category category = categoryService.findByName(categoryName);
			List<Inventory> inventories = inventoryService.findByCategory(category);
			model.addAttribute("inventories", inventories);
		} catch (CategoryNotFoundException e) {
	
			model.addAttribute("inventories", inventoryService.findAllInStock());
		}
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("cartProduct", new CartProduct());	
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.HOME);
		return "customer_home";
	}
	
	/*
	 * 
	 * View Cart
	 * 
	 */
	
	@RequestMapping("/viewCart")
	public String viewCart(HttpServletRequest request, Model model){
				
		model.addAttribute("cartProducts", cartHelper.getProductsInCart(request, inventoryService));
		model.addAttribute("cartProduct", new CartProduct());	
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.VIEW_CART);
		return "customer_home";
	}
	
	/*
	 * 
	 * Clear cart
	 * 
	 */
	
	@RequestMapping(value="/clearCart", method = RequestMethod.POST)
	public String clearCart(HttpServletRequest request, Model model){
		
		cartHelper.clearCart(request);
	
		return "redirect:/customer/home";
	}
	
	/*
	 * 
	 * Select shipping address
	 * 
	 */
	
	@RequestMapping("/selectShippingAddress")
	public String selectShippingAddress(HttpServletRequest request, Model model){
		
		User user = (User) request.getSession().getAttribute("user");
		
		model.addAttribute("shippingAddresses", customerService.findShippingAddresses(user));
		model.addAttribute("shippingAddress", new ShippingAddress());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.SELECT_SHIPPING_ADDRESS);
		return "customer_home";
	}
	
	@RequestMapping(value="/selectShippingAddress", method = RequestMethod.POST)
	public String selectShippingAddressPost(@ModelAttribute("shippingAddress") ShippingAddress shippingAddress, HttpServletRequest request, Model model){
		
		shippingAddress = customerService.findShippingAddressById(shippingAddress.getId());
		
		request.getSession().setAttribute("shippingAddress", shippingAddress);
		
		return "redirect:/customer/paymentType";
	}
	
	/*
	 * 
	 * Create shipping address
	 * 
	 */
	
	@RequestMapping("/createShippingAddress")
	public String createShippingAddressGet(HttpServletRequest request, Model model){
		
		model.addAttribute("countries", Country.values());
		model.addAttribute("shippingAddress", new ShippingAddress());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CREATE_SHIPPING_ADDRESS);
		return "customer_home";
	}
	
	/*
	 * 
	 * Payment type
	 * 
	 */
	
	@RequestMapping("/paymentType")
	public String paymentType(Model model){
		
		model.addAttribute("paymentTypes", PaymentType.values());
		model.addAttribute("paymentType", new UserPaymentType());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.PAYMENT_TYPE);
		return "customer_home";
	}
	
	@RequestMapping(value="/paymentType", method = RequestMethod.POST)
	public String paymentTypePost(@ModelAttribute("paymentType") UserPaymentType userPaymentType, HttpServletRequest request, Model model){
		
		if(userPaymentType.getPaymentType() == PaymentType.CASH){
			request.getSession().removeAttribute("creditCard");
			return "redirect:/customer/checkout";
		}else{
			return "redirect:/customer/selectCreditCard";
		}
	}
		
	/*
	 * 
	 * Select credit card
	 * 
	 */
	
	@RequestMapping("/selectCreditCard")
	public String selectCreditCardGet(HttpServletRequest request, Model model){
		
		User user = (User) request.getSession().getAttribute("user");
		
		model.addAttribute("creditCards", customerService.findCreditCards(user));
		model.addAttribute("creditCard", new CreditCard());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.SELECT_CREDIT_CARD);
		return "customer_home";
	}
	
	@RequestMapping(value="/selectCreditCard", method = RequestMethod.POST)
	public String selectCreditCardPost(@ModelAttribute("creditCard") CreditCard creditCard, HttpServletRequest request, Model model){
		
		creditCard = customerService.findCreditCardById(creditCard.getId());
		
		request.getSession().setAttribute("creditCard", creditCard);
		
		return "redirect:/customer/checkout";
	}
	
	/*
	 * 
	 * Create credit card
	 * 
	 */
	
	@RequestMapping("/createCreditCard")
	public String createCreditCard(HttpServletRequest request, Model model){
		
		model.addAttribute("creditCard", new CreditCard());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CREATE_CREDIT_CARD);
		return "customer_home";
	}
	
	/*
	 * 
	 * Checkout 
	 * 
	 */
	
	@RequestMapping("/checkout")
	public String checkoutGet(HttpServletRequest request, Model model){
		
		ShippingAddress shippingAddress = (ShippingAddress) request.getSession().getAttribute("shippingAddress");
		
		CreditCard creditCard = (CreditCard) request.getSession().getAttribute("creditCard");
		
		model.addAttribute("cartProducts", cartHelper.getProductsInCart(request, inventoryService));
		model.addAttribute("shippingAddress", shippingAddress);
		model.addAttribute("creditCard", creditCard);
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CHECKOUT);
		return "customer_home";
	}
	
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public String checkoutPost(HttpServletRequest request, Model model) throws InvalidStockException, InsufficientStockException{
		
		User user = (User) request.getSession().getAttribute("user");
		
		ShippingAddress shippingAddress = (ShippingAddress) request.getAttribute("shippingAddress");
		
		try {
			
			customerService.checkOut(user, shippingAddress, cartHelper.getCart(request));
			
			request.getSession().removeAttribute("shippingAddress");
			request.getSession().removeAttribute("creditCard");
			cartHelper.clearCart(request);
		} catch (InsufficientStockException e) {
			
			throw new InsufficientStockException();
		}
		
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CHECKOUT);
		return "customer_home";
	}
	
	/*
	 * 
	 * Categories
	 * 
	 */
	
	@RequestMapping("/categories")
	public String categories(Model model){
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CATEGORIES);
		return "customer_home";
	}
}
