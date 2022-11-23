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

import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.services.IncomeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class IncomeController {
	
	@Autowired
	private IncomeService incomeService;
	
	@GetMapping("income")
	public List<Income> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		List<Income> todos = incomeService.index(username);
		if (todos.size() == 0) {
			res.setStatus(404);
		}
		return todos;
	}

	@GetMapping("income/{incomeId}")
	public Income show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer incomeId, Principal principal) {
		String username = principal.getName();
		Income income = incomeService.show(username, incomeId);
		if (income == null) {
			res.setStatus(404);
		}
		return income;
	}

	@PostMapping("income")
	public Income create(HttpServletRequest req, HttpServletResponse res, @RequestBody Income income, Principal principal) {
		String username = principal.getName();
		try {
			income = incomeService.create(username, income);
			if (income == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(income.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			income = null;
		}
		return income;
	}

	@PutMapping("income/{incomeId}")
	public Income update(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer incomeId, @RequestBody Income income, Principal principal) {
		String username = principal.getName();
		try {
			income = incomeService.update(username, incomeId, income);
			if (income == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			income = null;
		}
		return income;
	}

	@DeleteMapping("income/{incomeId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer incomeId, Principal principal) {
		String username = principal.getName();
		try {
			boolean deleted = incomeService.delete(username, incomeId);
			if (!deleted) {
				res.setStatus(404);
			}
			res.setStatus(204);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
}
