package com.qbryx.tommystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Inventory;
import com.qbryx.tommystore.domain.InventoryHistory;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.StockMonitor;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.AdminPage;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.util.Constants;
import com.qbryx.tommystore.util.ProductHelper;
import com.qbryx.tommystore.util.RegisterUser;
import com.qbryx.tommystore.validator.CategoryValidator;
import com.qbryx.tommystore.validator.InventoryValidator;
import com.qbryx.tommystore.validator.ProductValidator;
import com.qbryx.tommystore.validator.RegistrationValidator;
import com.qbryx.tommystore.validator.UpdateProductValidator;
import com.qbryx.tommystrore.exception.CategoryHasProductsException;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.DuplicateUserException;
import com.qbryx.tommystrore.exception.ExistingOrderException;
import com.qbryx.tommystrore.exception.InvalidStockException;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private RegistrationValidator registrationValidator;

	@Autowired
	private CategoryValidator categoryValidator;

	@Autowired
	private ProductValidator productValidator;

	@Autowired
	private UpdateProductValidator updateProductValidator;

	@Autowired
	private InventoryValidator inventoryValidator;

	/*
	 * 
	 *Dashboard 
	 * 
	 */
	
	@RequestMapping("/dashboard")
	public String home(Model model) {
		
		StockMonitor stockMonitor = inventoryService.findStockMonitor();
		
		List<Inventory> inventories = inventoryService.findByStock(stockMonitor);
		
		model.addAttribute("stockMonitor", stockMonitor);
		model.addAttribute("inventories", inventories);
		model.addAttribute("users", userService.findNewUsers());
		model.addAttribute("activePage", AdminPage.DASHBOARD);
		return "admin_home";
	}
	
	/*
	 * 
	 * Update stock monitor
	 * 
	 */
	
	@RequestMapping("/updateStockMonitor")
	public String updateStockMonitor(@ModelAttribute("stockMonitor") StockMonitor stockMonitor, Model model){
		
		if(stockMonitor.getStock() <= 0){
			return "redirect:/admin/dashboard";
		}
		
		inventoryService.updateStockMonitor(stockMonitor);
		
		return "redirect:/admin/dashboard";
	}

	/*
	 * 
	 * View user list
	 * 
	 */

	@RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
	public String viewUsersGet(@RequestParam("userType") String userType, Model model) {

		List<User> users = new ArrayList<>();

		try {

			users = userService.findByType(UserType.valueOf(userType));
		} catch (IllegalArgumentException e) {

			users = userService.findAll();
		}

		model.addAttribute("users", users);
		model.addAttribute("activePage", AdminPage.USER_LIST);
		return "admin_home";
	}

	/*
	 * 
	 * Add new administrator
	 * 
	 */

	@RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
	public String addAdminGet(Model model) {

		model.addAttribute("registerUser", new RegisterUser());
		model.addAttribute("activePage", AdminPage.ADD_ADMINISTRATOR);
		return "admin_home";
	}

	@RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
	public String addAdminPost(@Validated @ModelAttribute("registerUser") RegisterUser registerUser,
			BindingResult bindingResult, Model model) {

		model.addAttribute("activePage", AdminPage.ADD_ADMINISTRATOR);

		registrationValidator.validate(registerUser, bindingResult);

		if (bindingResult.hasErrors()) {
			return "admin_home";
		}

		try {

			userService.save(registerUser.buildAdministrator());

			model.addAttribute("newUser", registerUser);
			model.addAttribute("registerUser", new RegisterUser());
		} catch (DuplicateUserException e) {

			model.addAttribute("duplicateUser", registerUser);
		}

		return "admin_home";
	}

	/*
	 * 
	 * Add category
	 * 
	 */

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public String addCategoryGet(Model model) {

		model.addAttribute("category", new Category());
		model.addAttribute("activePage", AdminPage.ADD_CATEGORY);
		return "admin_home";
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategoryPost(@Validated @ModelAttribute("category") Category category, BindingResult bindingResult,
			Model model) {

		model.addAttribute("activePage", AdminPage.ADD_CATEGORY);

		categoryValidator.validate(category, bindingResult);

		if (bindingResult.hasErrors()) {
			return "admin_home";
		}

		try {

			categoryService.save(category);
			model.addAttribute("newCategory", category);
			model.addAttribute("category", new Category());
		} catch (DuplicateCategoryException e) {

			model.addAttribute("duplicateCategory", category);
		}

		return "admin_home";
	}

	/*
	 * 
	 * View category
	 * 
	 */

	@RequestMapping("/viewCategories")
	public String viewCategories(Model model) {

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_CATEGORY);
		return "admin_home";
	}

	/*
	 * 
	 * Delete category
	 * 
	 */

	@RequestMapping("/deleteCategory")
	public String deletecategory(@RequestParam("categoryName") String categoryName, Model model) {

		Category category = null;

		try {

			category = categoryService.findByName(categoryName);
			categoryService.delete(category);
		} catch (CategoryHasProductsException e) {

			model.addAttribute("category", category);
		} catch (CategoryNotFoundException e) {

			model.addAttribute("categoryNotFound", "Category <strong>" + categoryName + "</strong> not found.");
		}

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_CATEGORY);
		return "admin_home";
	}

	/*
	 * 
	 * Update category
	 * 
	 */

	@RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
	public String updateCategoryGet(@RequestParam("categoryName") String categoryName, Model model) {

		Category category;

		try {

			category = categoryService.findByName(categoryName);
		} catch (CategoryNotFoundException e) {

			model.addAttribute("activePage", AdminPage.VIEW_CATEGORY);
			model.addAttribute("categories", categoryService.findAll());
			model.addAttribute("categoryNotFound", "Category <strong>" + categoryName + "</strong> not found.");
			return "admin_home";
		}

		model.addAttribute("category", category);
		model.addAttribute("activePage", AdminPage.UPDATE_CATEGORY);
		return "admin_home";
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategoryPost(@Validated @ModelAttribute("category") Category category,
			BindingResult bindingResult, Model model) {

		model.addAttribute("activePage", AdminPage.UPDATE_CATEGORY);

		categoryValidator.validate(category, bindingResult);

		if (bindingResult.hasErrors()) {
			return "admin_home";
		}

		try {

			categoryService.update(category);
			model.addAttribute("updatedCategory", category);
		} catch (DuplicateCategoryException e) {

			model.addAttribute("duplicateCategory", category);
		}

		return "admin_home";
	}

	/*
	 * 
	 * Add product and inventory
	 * 
	 */

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String addProductGet(Model model) {

		model.addAttribute("product", new ProductHelper());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.ADD_PRODUCT);
		return "admin_home";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Validated @ModelAttribute("product") ProductHelper productHelper,
			BindingResult bindingResult, Model model) throws CategoryNotFoundException {

		model.addAttribute("activePage", AdminPage.ADD_PRODUCT);
		model.addAttribute("categories", categoryService.findAll());

		productValidator.validate(productHelper, bindingResult);

		if (bindingResult.hasErrors()) {
			return "admin_home";
		}

		Product product = productHelper.buildProduct(categoryService);
		Inventory inventory = productHelper.buildInventory(product, userService);

		try {

			productService.save(product);
			inventoryService.save(inventory);
			model.addAttribute("newProduct", productHelper.buildProduct(categoryService));
			model.addAttribute("product", new ProductHelper());
		} catch (DuplicateProductException e) {

			model.addAttribute("duplicateProduct", productHelper.buildProduct(categoryService));
		}

		return "admin_home";
	}

	/*
	 * 
	 * Update product
	 * 
	 */

	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String updateProductGet(@RequestParam("name") String name, Model model) {

		Product product = null;
		
		try {
			
			product = productService.findByName(name);
		} catch (ProductNotFoundException e) {
			
			model.addAttribute("productName", name);
			return "redirect:/admin/viewProducts?category=";
		}
		
		

		model.addAttribute("product", ProductHelper.buildProductHelper(product));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.UPDATE_PRODUCT);
		return "admin_home";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProductPost(@Validated @ModelAttribute("product") ProductHelper productHelper,
			BindingResult bindingResult, Model model) throws CategoryNotFoundException {

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.UPDATE_PRODUCT);

		updateProductValidator.validate(productHelper, bindingResult);

		if (bindingResult.hasErrors()) {
//			model.addAttribute("product", productService.findByProductId(productHelper.getProductId()));
			return "admin_home";
		}

		Product product = productHelper.buildProductToUpdate(categoryService);

		try {

			productService.update(product);
			model.addAttribute("updatedProduct", product);
		} catch (DuplicateProductException e) {

			model.addAttribute("product", productService.findByProductId(productHelper.getProductId()));
			model.addAttribute("duplicateProduct", product);
		}

		return "admin_home";
	}

	/*
	 * 
	 * Delete product
	 * 
	 */

	@RequestMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("name") String productName, Model model) {

		try {

			Product product = productService.findByName(productName);

			Inventory inventory = inventoryService.findByProduct(product);
			
			productService.delete(product);
			inventoryService.delete(inventory);
		} catch (ProductNotFoundException e) {

			model.addAttribute("productNotFound", productName);
		} catch (ExistingOrderException e) {

			model.addAttribute("existingOrder", productName);
		}

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("inventories", inventoryService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_PRODUCT);
		return "admin_home";
	}

	/*
	 * 
	 * View products
	 * 
	 */

	@RequestMapping("/viewProducts")
	public String viewProduct(@RequestParam("category") String categoryName, Model model) {

		List<Product> products = null;

		try {

			products = productService.findByCategory(categoryService.findByName(categoryName));

			model.addAttribute("products", products);
			model.addAttribute("category", categoryName);
		} catch (CategoryNotFoundException e) {

			model.addAttribute("products", productService.findAll());
		}

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_PRODUCT);
		return "admin_home";
	}

	/*
	 * 
	 * Update inventory
	 * 
	 */

	@RequestMapping(value = "/updateInventory", method = RequestMethod.GET)
	public String updateInventoryGet(@RequestParam("name") String productName, Model model) {

		Product product = null;
		Inventory inventory = null;
		
		try {

			product = productService.findByName(productName);
			inventory = inventoryService.findByProduct(product);
		} catch (ProductNotFoundException e) {
			
			return "redirect:/admin/viewProducts?category=";
		}

		model.addAttribute("inventory", inventory);
		model.addAttribute("activePage", AdminPage.UPDATE_INVENTORY);
		return "admin_home";
	}

	@RequestMapping(value = "/updateInventory", method = RequestMethod.POST)
	public String updateInventoryPost(@Validated @ModelAttribute("inventory") Inventory inventory,
			BindingResult bindingResult, Model model) {
		
		model.addAttribute("activePage", AdminPage.UPDATE_INVENTORY);

		inventoryValidator.validate(inventory, bindingResult);

		if (bindingResult.hasErrors()) {
			return "admin_home";
		}
	
		try {
			
			inventoryService.updateRestock(inventory);
			
			model.addAttribute("inventoryUpdate", "<strong>" + inventory.getProduct().getName() + "</strong> stock succesfully replenished.");
			model.addAttribute("inventory", inventory);
		} catch (InvalidStockException e) {
			
			model.addAttribute("inventoryUpdateFailed", "<strong>" + inventory.getProduct().getName() + "</strong> stock update failed. Must be greater than the remaining stock");
			model.addAttribute("inventory", inventoryService.findById(inventory.getId()));
		}
		
		
		return "admin_home";
	}
	
	/*
	 * 
	 * View inventory
	 * 
	 */
	
	@RequestMapping(value = "/viewInventory", method = RequestMethod.GET)
	public String updateInventoryGet(Model model) {

		model.addAttribute("inventories", inventoryService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_INVENTORY);
		return "admin_home";
	}
	
	/*
	 * 
	 * View inventory history
	 * 
	 */
	
	@RequestMapping("/viewInventoryHistory")
	public String viewInvetoryHistory(@RequestParam("prodId") String productId, Model model){
		
		List<InventoryHistory> inventoryHistories = null;
		
		try {
			
			inventoryHistories = inventoryService.findInventoryHistoryByProductId(productId);
			model.addAttribute("inventoryHistories", inventoryHistories);
		} catch (ProductNotFoundException e) {
			
			model.addAttribute("inventoryHistoriesAll", inventoryService.findAllInventoryHistory());
		}
		
		model.addAttribute("products", productService.findAll());
		model.addAttribute("activePage", AdminPage.VIEW_INVENTORY_HISTORY);
		return "admin_home";
	}
	
	/*
	 * 
	 * View orders 
	 * 
	 */
	
	@RequestMapping("/viewOrders")
	public String viewOrders(Model model){
		
		model.addAttribute("orders", productService.findAllOrders());
		model.addAttribute(Constants.ACTIVE_PAGE, AdminPage.VIEW_ORDERS);
		return "admin_home";
	}
}
