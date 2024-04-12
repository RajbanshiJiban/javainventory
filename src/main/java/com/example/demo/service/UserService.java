package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	void signup(User user);
	 
	User login(String un, String psw);
	 
	User isUserExist(String un);
	 
	List<User> getAllInfo(); 
	 
	User getUserById(int uid);
	 
	User isEmailExist(String em);
}
