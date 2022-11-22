package com.skilldistillery.rotahu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private PasswordEncoder passEncode;

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User register(User user) {
		
		String encryptedPassword = passEncode.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		
		user.setEnabled(true);
		user.setRole("standard");
		
		user = userRepo.saveAndFlush(user);
		
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		
		return userRepo.findByUsername(username);
		
	}

}
