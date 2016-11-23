package com.qbryx.tommystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * 
	 * Home
	 *
	 */
	
	@RequestMapping("/home")
	public String home(Model model){
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("inventories", inventoryService.findAllInStock());
		return "customer_home";
	}
	
	/*
	 * 
	 * View products
	 * 
	 */
	
	@RequestMapping("/viewProducts")
	public String viewProduct(@RequestParam("catName") String categoryName, Model model){
		
		try {
			
			Category category = categoryService.findByName(categoryName);
			List<Inventory> inventories = inventoryService.findByCategory(category);
			model.addAttribute("inventories", inventories);
		} catch (CategoryNotFoundException e) {
			
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("inventories", inventoryService.findAllInStock());
		}
		
		return "customer_home";
	}
}
