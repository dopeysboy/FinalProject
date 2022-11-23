package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.DebtLenderService;
import com.skilldistillery.rotahu.services.DebtService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost"})
public class DebtLenderController {
	@Autowired
	private DebtLenderService dlService;
	@Autowired
	private DebtService debtService;
	@Autowired
	private AuthService authService;
	
	@GetMapping("debtlender")
	public List<DebtLender> getAllDebtLenders(){
		List<DebtLender> debtLenders = dlService.findAll();
		
		return debtLenders;
	}
	
	@GetMapping("debtlender/{id}")
	public DebtLender getDebtLenderById(@PathVariable int id, 
			HttpServletRequest req, HttpServletResponse resp) {
		DebtLender dl = dlService.findById(id);
		
		if(dl == null) {
			resp.setStatus(404);
		}
		
		return dl;
	}
	
	@PutMapping("debtlender/{dlId}/add/debt/{dId}")
	public DebtLender addDebtToDebtLender(@PathVariable int dlId, @PathVariable int dId,
			HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		DebtLender dl = dlService.findById(dlId);
		Debt debt = debtService.getDebtById(dId, user);
		
		if(dl == null || debt == null) {
			resp.setStatus(404);
			return null;
		}
		
		dl = dlService.addDebtToDebtLender(dl, debt, user);
		
		if(dl == null) {
			resp.setStatus(400);
		} else {
			resp.setStatus(200);
		}
		return dl;
	}
	
	@PutMapping("debtlender/{dlId}/remove/debt/{dId}")
	public DebtLender removeDebtFromDebtLender(@PathVariable int dlId, @PathVariable int dId,
			HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		DebtLender dl = dlService.findById(dlId);
		Debt debt = debtService.getDebtById(dId, user);
		
		if(dl == null || debt == null) {
			resp.setStatus(404);
			return null;
		}

		dl = dlService.removeDebtFromDebtLender(dl, debt, user);
		
		if(dl == null) {
			resp.setStatus(400);
		} else {
			resp.setStatus(200);
		}
		return dl;
	}
}
