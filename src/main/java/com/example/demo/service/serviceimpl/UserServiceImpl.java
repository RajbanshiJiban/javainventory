package com.example.demo.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl  implements UserService{
  @Autowired
	private UserRepository userRepo;
	
	@Override
	public void signup(User user) {
		userRepo.save(user);
		
		
	}

	@Override
	public User login(String un, String psw) {
		
		return userRepo.findByUsernameAndPassword(un, psw);	
		}

	@Override
	public User isUserExist(String un) {
		
		 return userRepo.findByUsername(un);
	}

	@Override
	public List<User> getAllInfo() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int uid) {
		return userRepo.findById(uid).get();
	}

	@Override
	public User isEmailExist(String em) {
		return userRepo.findByEmail(em);
	}
	
	

}
