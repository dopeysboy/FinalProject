package com.skilldistillery.rotahu.services;

import com.skilldistillery.rotahu.entities.User;

public interface AuthService {
	public User register(User user);
	
	public User getUserByUsername(String username);
}
