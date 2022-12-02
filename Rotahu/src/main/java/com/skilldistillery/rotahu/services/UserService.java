package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.User;

public interface UserService {
	
	List<User> index();
	
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
	
	User updateAccount(User user);
	
	User enableDisableAccount(User user);

}
