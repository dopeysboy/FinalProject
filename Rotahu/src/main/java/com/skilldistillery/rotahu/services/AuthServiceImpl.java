package com.skilldistillery.rotahu.services;

import java.util.Optional;

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

	@Override
	public User update(User user) {
		if(user.getRole().equals("admin")) {
			Optional<User> opUser = userRepo.findById(user.getId());
			
			if(!opUser.isPresent()) {
				return null;
			}
			user.setEmail(user.getEmail());
			user.setPassword(user.getPassword());
			user.setUsername(user.getUsername());
		}
		return null;
	}

	@Override
	public boolean destroy(User user) {
		if(user.getRole().equals("admin")) {
			userRepo.delete(user);
			
			if(!userRepo.findById(user.getId()).isPresent()) {
				return true;
			}
		}
		return false;
	}

}
