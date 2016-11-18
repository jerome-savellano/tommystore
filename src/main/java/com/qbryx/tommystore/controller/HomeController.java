package com.qbryx.tommystore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.util.LoginUser;
import com.qbryx.tommystore.util.RegisterUser;
import com.qbryx.tommystore.validator.RegistrationValidator;
import com.qbryx.tommystrore.exception.DuplicateUserException;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationValidator registrationValidator;
	
	/*
	 * 
	 * Initial controller
	 * 
	 */

	@RequestMapping("/initial")
	public String initial(HttpServletRequest request, Model model) {
		
		User user = (User) request.getSession().getAttribute("user");
		
		return redirectToHome(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("user") != null){
			return new ModelAndView("home");
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

		return redirectToHome(user);
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

			userService.createUser(registerUser.buildCustomer());
			
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
		model.addAttribute("user", new User());
		return "home";
	}
	
	/*
	 * 
	 * Misc
	 * 
	 */
	
	private String redirectToHome(User user){
		
		if(user == null){
			return "home";
		}
		
		return (user.getUserType() == UserType.CUSTOMER) ? "redirect:/customer/home" : "redirect:/admin/home";
	}
}
