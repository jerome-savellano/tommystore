package com.qbryx.tommystore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.enums.CustomerPage;
import com.qbryx.tommystore.service.CategoryService;
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
}
