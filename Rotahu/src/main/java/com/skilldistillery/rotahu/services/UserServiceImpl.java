package com.skilldistillery.rotahu.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
//
//	@Override
//	public User findByUserId(int userId) {
//		Optional<User> opUser = userRepo.findById(userId);
//		User u = null;
//		if(opUser.isPresent()) {
//			u = opUser.get();
//		}
//		return u;
//	}

//	@Override
//	public User createUser(User user) {
//		return userRepo.saveAndFlush(user);
//	}

//	@Override
//	public User updateUser(User user, String username) {
//		User managed = findByUsername(username);
//		managed.setUsername(username);
//		managed.setPassword(username);
//		managed.setEmail(username);
//		return userRepo.save(managed);
//	}
//
//	@Override
//	public boolean destroyUser(int userId) {
//		userRepo.deleteById(userId);
//		return !userRepo.existsById(userId);
//	}

}
