package com.qbryx.tommystore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/home")
	public String home(){
		
		return "admin_home";
	}
	
}
