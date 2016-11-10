package com.qbryx.tommystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.service.UserService;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/initial")
	public String initial(Model model){
		
		model.addAttribute("loginUser", new User());
		return "home";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("loginUser") User loginUser, Model model){
		
		User user = null;
		
		try {
			
			user = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
			model.addAttribute("user", user);
		} catch (FailedLoginException e) {
			
			model.addAttribute("loginUser", new User());
			model.addAttribute("email", loginUser.getEmail());
		}
		
		return "home";
	}
}
