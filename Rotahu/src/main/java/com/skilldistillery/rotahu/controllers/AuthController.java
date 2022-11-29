package com.skilldistillery.rotahu.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost" })
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserService userService;

	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		System.out.println(user);
		if (user == null) {
			res.setStatus(400);
			return null;
		}
		user = authService.register(user);
		return user;
	}

	@GetMapping("authenticate")
	public User authenticate(Principal principal, HttpServletResponse res) {
		if (principal == null) { // no Authorization header sent
			res.setStatus(401);
			res.setHeader("WWW-Authenticate", "Basic");
			return null;
		}
		return authService.getUserByUsername(principal.getName());
	}
	
	@PutMapping("user/disable")
	public void disableAcct(@RequestBody User user, HttpServletResponse res) {
		String username = user.getUsername();
		
		boolean disabled = userService.disable(username);
		if (!disabled) {
			res.setStatus(404);
		} else {
			res.setStatus(204);
		}
	}
	
}
