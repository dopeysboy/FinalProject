package com.skilldistillery.rotahu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passEncode;

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
	
	@Override
	public boolean disable(String username) {
		User dbUser = findByUsername(username);
		dbUser.setEnabled(false);
		dbUser = userRepo.saveAndFlush(dbUser);
		if (dbUser.getEnabled()) {
			return false;
		}
		return true;
	}

	@Override
	public User changePassword(User user, String newPassword) {
		String encryptedPassword = passEncode.encode(newPassword);
		user.setPassword(encryptedPassword);
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User updateAccount(User user) {
		User dbUser = userRepo.findByUsername(user.getUsername());
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setEmail(user.getEmail());
		userRepo.saveAndFlush(dbUser);
		return dbUser;
	}

}
