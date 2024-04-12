package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String getHome(HttpSession session)
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
		
		return "admin/index";
		
	}
	
	@GetMapping("/Report")
	public String getReport(HttpSession session)
	{
		if(session.getAttribute("validuser")==null) {
			return "login";
			
		}
		
		
		return "admin/Report";
		
	}
	

	
	
	
	
	
	
	
	
	


}
