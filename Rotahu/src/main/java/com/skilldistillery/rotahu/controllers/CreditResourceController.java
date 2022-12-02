package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.CreditResource;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.CreditResourceService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class CreditResourceController {
	@Autowired
	private CreditResourceService crServ;
	@Autowired
	private AuthService authService;
	
	@GetMapping("creditresource")
	public List<CreditResource> getAll(Principal principal, HttpServletRequest req,
			HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		List<CreditResource> crs = crServ.findAll();
		
		return crs;
	}
	
	@GetMapping("creditresource/{username}")
	public List<CreditResource> getByUser(@PathVariable String username, Principal principal, 
			HttpServletRequest req, HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null || user.getUsername() != username || user.getRole() != "admin") {
			resp.setStatus(401);
			return null;
		}
		
		List<CreditResource> crs = crServ.findByCreator(authService.getUserByUsername(username));
		
		if(crs == null) {
			resp.setStatus(404);
		}
		
		return crs;
	}
	
	@GetMapping("creditresource/{crId}")
	public CreditResource getById(@PathVariable Integer crId, Principal principal,
			HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		CreditResource cr = crServ.findById(crId);
		
		if(cr == null) {
			resp.setStatus(404);
		}
		
		return cr;
	}
	@PostMapping("creditresource")
	public CreditResource createCreditResource(@RequestBody CreditResource cr, Principal principal,
			HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		cr = crServ.createCreditResource(cr, user);
		
		if(cr == null) {
			resp.setStatus(400);
		} else {
			resp.setStatus(201);
		}
		
		return cr;
	}
	
	@PutMapping("creditresource/{crId}")
	public CreditResource updateCreditResource(@RequestBody CreditResource cr, @PathVariable Integer crId,
			Principal principal, HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		cr = crServ.updateCreditResource(cr, user, crId);
		
		if(cr == null) {
			resp.setStatus(400);
		} else {
			resp.setStatus(200);
		}
		
		return cr;
	}
}
