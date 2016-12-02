package com.qbryx.tommystore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qbryx.tommystore.domain.CartProduct;
import com.qbryx.tommystore.domain.Category;
import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystore.enums.VisitorPage;
import com.qbryx.tommystore.service.CategoryService;
import com.qbryx.tommystore.service.InventoryService;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.util.CartHelper;
import com.qbryx.tommystore.util.Constants;
import com.qbryx.tommystore.util.LoginUser;
import com.qbryx.tommystore.util.RegisterUser;
import com.qbryx.tommystore.validator.RegistrationValidator;
import com.qbryx.tommystrore.exception.CategoryNotFoundException;
import com.qbryx.tommystrore.exception.DuplicateUserException;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Controller
public class HomeController {
	
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationValidator registrationValidator;
	
	@Autowired
	private CartHelper cartHelper;
	
	/*
	 * 
	 * Initial controller
	 * 
	 */

	@RequestMapping("/initial")
	public String initial(HttpServletRequest request, Model model) {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user != null){
			return redirectUser(user);
		}
		
		cartHelper.createCart(request);
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.findAll());
		model.addAttribute("cartProduct", new CartProduct());
		model.addAttribute(Constants.ACTIVE_PAGE, VisitorPage.HOME);
		return "visitor_home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("user") != null){
			return new ModelAndView("customer_home");
		}
		
		return new ModelAndView("login", "user", new LoginUser());
	}
	
	/*
	 * 
	 * Login controller
	 * 
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@Valid @ModelAttribute("user") LoginUser loginUser, BindingResult bindingResult,
			HttpServletRequest request, Model model) {

		if (bindingResult.hasErrors()) {
			return "login";
		}
				
		User user = null;

		try {

			user = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
			request.getSession().setAttribute("user", user);
		} catch (FailedLoginException e) {

			model.addAttribute("user", new LoginUser());
			model.addAttribute("email", loginUser.getEmail());
			return "login";
		}

		return redirectUser(user);
	}
	
	/*
	 * 
	 * Registration controller
	 * 
	 */

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerCustomer() {
		return new ModelAndView("register", "registerUser", new RegisterUser());
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute("registerUser") RegisterUser registerUser,
			BindingResult bindingResult, Model model) {

		registrationValidator.validate(registerUser, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		try {

			userService.save(registerUser.buildCustomer());
			
			model.addAttribute("newUser", registerUser);			
			model.addAttribute("registerUser", new RegisterUser());
		} catch (DuplicateUserException e) {
			
			model.addAttribute("duplicateUser", registerUser);
		}

		return "register";
	}
	
	/*
	 * 
	 * Logout controller
	 * 
	 */

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {

		request.getSession().invalidate();
		return "redirect:/initial";
	}
	
	/*
	 * 
	 * View visitor cart
	 * 
	 */
	
	@RequestMapping("/viewCart")
	public String viewCart(HttpServletRequest request, Model model){
				
		model.addAttribute("cartProducts", cartHelper.getProductsInCart(request, inventoryService));
		model.addAttribute("cartProduct", new CartProduct());	
		model.addAttribute(Constants.ACTIVE_PAGE, VisitorPage.VIEW_CART);
		return "visitor_home";
	}
	
	private String redirectUser(User user){
		return user.getUserType() == UserType.CUSTOMER ? "redirect:/customer/home" : "redirect:/admin/dashboard"; 
	}
	
	/*
	 * 
	 * Visitor view products
	 * 
	 */
	
	@RequestMapping("/viewProducts")
	public String viewProduct(@RequestParam("category") String categoryName, Model model) {

		try {
			Category category = categoryService.findByName(categoryName);
			
			List<Product> products= productService.findByCategory(category);
			model.addAttribute("products", products);
		} catch (CategoryNotFoundException e) {
	
			model.addAttribute("products", productService.findAll());
		}
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("cartProduct", new CartProduct());	
		model.addAttribute(Constants.ACTIVE_PAGE, VisitorPage.HOME);
		return "visitor_home";
	}
	
	/*
	 * 
	 * Categories
	 * 
	 */
	
	@RequestMapping("/categories")
	public String categories(Model model){
		
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute(Constants.ACTIVE_PAGE, VisitorPage.CATEGORIES);
		return "visitor_home";
	}
	
	/*
	 * 
	 * Add to cart (AJAX)
	 * 
	 */
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	public @ResponseBody CartProduct addToCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
		
		cartProduct.setProduct(productService.findByProductId(cartProduct.getProduct().getProductId()));
		
		cartHelper.addProductToCart(request, cartProduct);
		
		return cartProduct;
	}
	
	/*
	 * 
	 * Remove from cart (AJAX)
	 * 
	 */
	
	@RequestMapping(value="/removeFromCart", method = RequestMethod.POST)
	public @ResponseBody int removeFromCart(@ModelAttribute CartProduct cartProduct, HttpServletRequest request){
	
		cartHelper.removeProductFromCart(request, cartProduct);
		return cartHelper.getCartSize(request);
	}
	
	/*
	 * 
	 * Clear cart
	 * 
	 */
	
	@RequestMapping(value="/clearCart", method = RequestMethod.POST)
	public String clearCart(HttpServletRequest request, Model model){
		
		cartHelper.clearCart(request);
	
		return "redirect:/initial";
	}	
	
	/*
	 * 
	 * Find by name or category
	 * 
	 */
	
	@RequestMapping("/findProduct")
	public String findByNameOrCategory(@RequestParam("name") String name, Model model){
	
		List<Product> products = productService.findByNameOrCategory(name);
		
		model.addAttribute("products", products);
		model.addAttribute("cartProduct", new CartProduct());
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute(Constants.ACTIVE_PAGE, VisitorPage.HOME);
		return "visitor_home";
	}
}
