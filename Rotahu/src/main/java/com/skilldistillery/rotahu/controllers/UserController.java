package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.UserService;

@RestController
@RequestMapping("user")
@CrossOrigin({ "*", "http://localhost" })
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("disable")
	public void disableAcct(@RequestBody User user, HttpServletResponse res) {
		String username = user.getUsername();
		
		boolean disabled = userService.disable(username);
		if (!disabled) {
			res.setStatus(404);
		} else {
			res.setStatus(204);
		}
	}
	
	@PutMapping("changePassword")
	public User changePassword(@RequestBody String newPassword, HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		user = userService.changePassword(user, newPassword);
		return user;
	}
	
	@PutMapping("updateAccount")
	public User updateAccount(@RequestBody User user, HttpServletResponse res, Principal principal) {
		user = userService.updateAccount(user);
		return user;
	}
	
	@GetMapping("admin")
	public List<User> index(HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		if (user.getRole().equals("admin")) {
			List<User> users = userService.index();
			return users;
		}
		res.setStatus(401);
		return null;
	}
	
	@PutMapping("admin/toggleEnable")
	public void toggleEnable(@RequestBody String username, HttpServletResponse res, Principal principal) {
		User user = userService.findByUsername(username);
		boolean oldStatus = user.getEnabled();
		user = userService.enableDisableAccount(user);
		if (oldStatus == user.getEnabled()) {
			res.setStatus(400);
		}
	}
	
}
