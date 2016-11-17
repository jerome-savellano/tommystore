package com.qbryx.tommystore.controller;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.AdminPage;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.validator.CategoryValidator;
import com.qbryx.tommystore.validator.ProductHelper;
import com.qbryx.tommystore.validator.ProductValidator;
import com.qbryx.tommystore.validator.RegisterUser;
import com.qbryx.tommystore.validator.RegistrationValidator;
import com.qbryx.tommystrore.exception.CategoryHasProductsException;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.DuplicateCategoryException;
import com.qbryx.tommystrore.exception.DuplicateProductException;
import com.qbryx.tommystrore.exception.DuplicateUserException;
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
	private RegistrationValidator registrationValidator;

	@Autowired
	private CategoryValidator categoryValidator;

	@Autowired
	private ProductValidator productValidator;

	@RequestMapping("/home")
	public String home(Model model) {

		model.addAttribute("activePage", AdminPage.DASHBOARD);
		return "admin_home";
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

			userService.createUser(registerUser.buildAdministrator());

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

			categoryService.createCategory(category);
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
			categoryService.deleteCategory(category);
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

			categoryService.updateCategory(category);
			model.addAttribute("updatedCategory", category);
		} catch (DuplicateCategoryException e) {

			model.addAttribute("duplicateCategory", category);
		}

		return "admin_home";
	}

	/*
	 * 
	 * Add product
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

		try {

			productService.createProduct(productHelper.buildProduct(categoryService));
			model.addAttribute("newProduct", productHelper.buildProduct(categoryService));
			model.addAttribute("product", new ProductHelper());
		} catch (DuplicateProductException e) {

			model.addAttribute("duplicateProduct", productHelper.buildProduct(categoryService));
		}
		
		return "admin_home";
	}

	/*
	 * 
	 * View products
	 * 
	 */

	@RequestMapping("/viewProducts")
	public String viewProduct(@RequestParam("category") String categoryName, Model model) {

		List<Product> products;

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
}
