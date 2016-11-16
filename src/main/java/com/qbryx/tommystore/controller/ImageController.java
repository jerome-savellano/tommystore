package com.qbryx.tommystore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qbryx.tommystore.domain.Product;
import com.qbryx.tommystore.service.ProductService;
import com.qbryx.tommystrore.exception.ProductNotFoundException;

@Controller
public class ImageController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/image", method = RequestMethod.GET)
	public void showImage(@RequestParam("name") String name, HttpServletResponse response) throws ProductNotFoundException, IOException{
		
		Product product = productService.findByName(name);
		
		response.setContentType("image/jpeg, image/png, image/jpg");
		response.getOutputStream().write(product.getImage());
		
		response.getOutputStream().close();
	}
}
