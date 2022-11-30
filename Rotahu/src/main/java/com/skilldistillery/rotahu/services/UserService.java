package com.skilldistillery.rotahu.services;

import com.skilldistillery.rotahu.entities.User;

public interface UserService {
	
	User findByUsername(String username);
//	
//	User findByUserId(int userId);
//	
//	User createUser(User user);
//	
//	User updateUser(User user, String username);
//	
//	boolean destroyUser(int userId);
	
	boolean disable(String username);
	
	User changePassword(User user, String newPassword);

}
