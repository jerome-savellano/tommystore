package com.qbryx.tommystore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.ShippingAddress;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.Country;
import com.qbryx.tommystore.enums.CustomerPage;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.CustomerService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystore.util.CartHelper;
import com.qbryx.tommystore.util.Constants;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;

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
		
		cartHelper.getCart(request).clear();
	
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
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.SELECT_SHIPPING_ADDRESS);
		return "customer_home";
	}
	
	/*
	 * 
	 * Create shipping address
	 * 
	 */
	
	@RequestMapping("/createShippingAddress")
	public String createShippingAddress(HttpServletRequest request, Model model){
		
		model.addAttribute("countries", Country.values());
		model.addAttribute("shippingAddress", new ShippingAddress());
		model.addAttribute(Constants.ACTIVE_PAGE, CustomerPage.CREATE_SHIPPING_ADDRESS);
		return "customer_home";
	}
}
