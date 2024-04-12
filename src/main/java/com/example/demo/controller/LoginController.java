package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private UserService userser;
	
	@GetMapping("/")
 public String getIndex()
 {
	 return "index";
 }
	
	@GetMapping("/login")
	 public String getLogin()
	 {
		 return "login";
	 }
		
	
	@PostMapping("/login")
	 public String postLogin(@ModelAttribute User user, Model model, HttpSession session)
	 
	 {
		
		User usr = userser.login(user.getUsername(), user.getPassword());
		if(usr != null) {
		session.setAttribute("validuser", usr);
		session.setMaxInactiveInterval(200);
		return "/admin/index";}
		model.addAttribute("message","user not found!!");
		return "login";
	 }
	
	
	
	@GetMapping("/signup")
	 public String getSignUp()
	 {
		 return "signup";
	 }

	
	@PostMapping("/signup")
	 public String postSignUp(@RequestParam("username") String username,@RequestParam("password") String password, @ModelAttribute User user,Model model)
	 
	 {
		user.setUsername(username);
		 
	    user.setPassword(password);
	 
	    if (userser.isUserExist(user.getUsername()) != null) {
	 
	        model.addAttribute("message", "User already exists");
	 
	        return "signup"; // Return the signup form again or redirect to an error page
	 
	    }
	 
	    userser.signup(user);
	 
	    return "login";
	 }
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	session.invalidate();
	return "login";
	}
	
	
	@GetMapping("/error")
	public String getError()
	{
		return "error";
	}
	
	
}
