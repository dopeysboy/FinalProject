package com.skilldistillery.rotahu.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.UserFinancialFitnessService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class FitnessController {
	@Autowired
	private UserFinancialFitnessService fitServ;
	@Autowired
	private AuthService authService;
	
	@GetMapping("fitness")
	public Integer calculateUserFitness(Principal principal, HttpServletRequest req,
			HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		Integer fitnessScore = fitServ.determineFitness(user);
		
		return fitnessScore;
	}
}
