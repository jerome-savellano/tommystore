package com.qbryx.tommystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.service.CartProductService;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartProductService cartProductService;

	/*
	 * 
	 * Home
	 *
	 */

	@RequestMapping("/home")
	public String home(Model model) {

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("inventories", inventoryService.findAllInStock());
		model.addAttribute("cartProduct", new CartProduct());
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
		return "customer_home";
	}
	
	
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
    public @ResponseBody CartProduct createSmartphone(@ModelAttribute CartProduct smartphone) {
		System.out.println(smartphone.getProduct().getProductId());
        return smartphone;
    }
	
	/*
	 * 
	 * Add product to cart
	 * 
	 */

//	@ResponseBody
//	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
//	public CartProduct addProductToCart(@ModelAttribute CartProduct cartProduct){
//		
//		User user = userService.findByEmail(cartProduct.getUser().getEmail());
//		Product product = productService.findByProductId(cartProduct.getProduct().getProductId());
//		
//		cartProduct.setUser(user);
//		cartProduct.setProduct(product);
//		cartProduct.setQuantity(CartProduct.INITIAL_QUANTITY);
//		
//		cartProductService.createCartProduct(cartProduct);
//		
//		return cartProduct;
//	}
}
