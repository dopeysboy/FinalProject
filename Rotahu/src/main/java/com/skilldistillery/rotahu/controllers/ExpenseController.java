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

import com.skilldistillery.rotahu.entities.Expense;
import com.skilldistillery.rotahu.services.ExpenseService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("expense")
	public List<Expense> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		List<Expense> todos = expenseService.index(username);
		if (todos.size() == 0) {
			res.setStatus(404);
		}
		return todos;
	}

	@GetMapping("expense/{expenseId}")
	public Expense show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer expenseId, Principal principal) {
		String username = principal.getName();
		Expense expense = expenseService.show(username, expenseId);
		if (expense == null) {
			res.setStatus(404);
		}
		return expense;
	}

	@PostMapping("expense")
	public Expense create(HttpServletRequest req, HttpServletResponse res, @RequestBody Expense expense, Principal principal) {
		String username = principal.getName();
		try {
			expense = expenseService.create(username, expense);
			if (expense == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(expense.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			expense = null;
		}
		return expense;
	}

	@PutMapping("expense/{expenseId}")
	public Expense update(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer expenseId, @RequestBody Expense expense, Principal principal) {
		String username = principal.getName();
		try {
			expense = expenseService.update(username, expenseId, expense);
			if (expense == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			expense = null;
		}
		return expense;
	}

	@DeleteMapping("expense/{expenseId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer expenseId, Principal principal) {
		String username = principal.getName();
		try {
			boolean deleted = expenseService.delete(username, expenseId);
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
