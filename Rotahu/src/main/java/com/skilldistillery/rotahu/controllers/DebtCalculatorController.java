package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.services.AuthService;
import com.skilldistillery.rotahu.services.DebtCalculatorService;
import com.skilldistillery.rotahu.services.DebtService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200/"})
public class DebtCalculatorController {

	@Autowired
	private DebtCalculatorService debtCalc;
	@Autowired
	private DebtService debtService;
	@Autowired
	private AuthService authService;
	
	@GetMapping("calculator/{dId}")
	public Map<Integer, Double> calculateLoggedInDebt(@PathVariable int dId, Principal principal,
			HttpServletRequest req, HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		Debt debt = debtService.getDebtById(dId, user);
		
		if(debt == null) {
			resp.setStatus(400);
			return null;
		}
		
		Map<Integer, Double> map = debtCalc.calculatePayments(debt, debt.getMinimumMonthlyPayment(), 1);
		
		return map;
	}
	
	@PostMapping("calculator/loggedout")
	public Map<Integer, Double> calculatedLoggedOutDebt(@RequestBody Debt debt, HttpServletRequest req,
			HttpServletResponse resp){
		Map<Integer, Double> map = debtCalc.calculatePayments(debt, debt.getMinimumMonthlyPayment(), 1);
		
		return map;
	}
	
	@PostMapping("calculator/{resInc}")
	public Map<String, Map<Integer, Double>> calculateUserDebts(@RequestBody List<Debt> debts, @PathVariable Double resInc,
			Principal principal, HttpServletRequest req, HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		
		Map<String, Double> paymentAmounts = new HashMap<>();
		
		debts.stream().forEach( (debt) -> {
			paymentAmounts.put(debt.getName(), debt.getMinimumMonthlyPayment());
		});
		
		Map<String, Map<Integer, Double>> map = debtCalc.calculateBestPayment(debts, paymentAmounts, resInc);
		
		return map;
	}
	
	@GetMapping("calculator/inc/{resInc}")
	public Map<String, Map<Integer, Double>> calculateDebtsGivenUser(@PathVariable double resInc,
			Principal principal, HttpServletRequest req, HttpServletResponse resp){
		User user = authService.getUserByUsername(principal.getName());
		
		if(user == null) {
			resp.setStatus(401);
			return null;
		}
		List<Debt> debts = user.getDebts();
		
		Map<String, Double> paymentAmounts = new HashMap<>();
		
		debts.stream().forEach( (debt) -> {
			paymentAmounts.put(debt.getName(), debt.getMinimumMonthlyPayment());
		});
		
		Map<String, Map<Integer, Double>> map = debtCalc.calculateBestPayment(debts, paymentAmounts, resInc);

		
		return map;
	}
}
