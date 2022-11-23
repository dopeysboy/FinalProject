package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.DebtService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost"})
public class DebtController {
	@Autowired
	private DebtService debtService;
	@Autowired
	private AuthService authService;
	
	@GetMapping("debt")
	public List<Debt> getAllDebts(Principal principal, HttpServletRequest req, HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		List<Debt> debts = debtService.getDebtsByUser(user);
		
		return debts;
	}
	
	@GetMapping("debt/{id}")
	public Debt getDebtById(Principal principal, @PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		Debt debt = debtService.getDebtById(id, user);
		
		return debt;
	}
	
	@PostMapping("debt")
	public Debt createDebt(Principal principal, @RequestBody Debt debt,
			HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		debt = debtService.createDebt(debt, user);
		if(debt != null) {
			resp.setStatus(201);
			
			StringBuffer url = req.getRequestURL();
			url.append(debt.getId());
			
			resp.setHeader("Location", url.toString());
		}
		
		return debt;
	}
	
	@PutMapping("debt/{id}")
	public Debt editDebt(Principal principal, @RequestBody Debt debt,
			@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		debt = debtService.updateDebt(debt, id, user);
		
		if(debt == null) {
			resp.setStatus(400);
		} else {
			resp.setHeader("Location", req.getRequestURL().toString());
		}

		return debt;
	}
	
	@DeleteMapping("debt/{id}")
	public void deleteDebt(Principal principal, @PathVariable int id,
			HttpServletResponse resp, HttpServletRequest req) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return;
		}
		Debt debt = debtService.getDebtById(id, user);
		boolean successful = debtService.destroyDebt(debt, user);
		
		if(successful) {
			resp.setStatus(200);
		} else {
			resp.setStatus(400);
		}
	}
}
