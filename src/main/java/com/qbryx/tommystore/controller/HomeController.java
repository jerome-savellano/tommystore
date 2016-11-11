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
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystore.validator.LoginValidator;
import com.qbryx.tommystore.validator.RegistrationValidator;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationValidator registrationValidator;

	@RequestMapping("/initial")
	public String initial(Model model) {

		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {

		return new ModelAndView("login", "user", new LoginValidator());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("user") LoginValidator loginUser, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()){
			return "login";
		}

		User user = null;
		
		try {

			user = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
			model.addAttribute("user", user);
		} catch (FailedLoginException e) {
			
			model.addAttribute("user", new User());
			model.addAttribute("email", loginUser.getEmail());
			return "login";
		}

		return "home";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(){
		return new ModelAndView("register", "registerUser", new User());
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("registerUser") User registerUser,
			BindingResult bindingResult){
		
		registrationValidator.validate(registerUser, bindingResult);
		
		if(bindingResult.hasErrors()){
			return "register";
		}
		
		System.out.println(registerUser.toString());
		return "register";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {

		request.getSession().invalidate();
		model.addAttribute("user", new User());
		return "home";
	}
}
