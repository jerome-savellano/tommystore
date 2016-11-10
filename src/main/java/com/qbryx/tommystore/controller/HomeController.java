package com.qbryx.tommystore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		model.addAttribute("user", new User());
		return "home";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("loginUser") User loginUser, Model model){
		
		User user = null;
		System.out.println(loginUser.getPassword());
		try {
			
			user = userService.authenticate(loginUser.getEmail(), loginUser.getPassword());
			model.addAttribute("user", user);
		} catch (FailedLoginException e) {
			System.out.println("Failed");
			model.addAttribute("user", new User());
			model.addAttribute("email", loginUser.getEmail());
		}
		
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model){
		
		request.getSession().invalidate();
		model.addAttribute("user", new User());
		return "home";
	}
}
