package com.skilldistillery.rotahu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Expense;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.ExpenseRepository;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Expense> index(String username) {
		List<Expense> expenses = expenseRepo.findByUserUsername(username);
		return expenses;
	}

	@Override
	public Expense show(String username, Integer expenseId) {
		Expense expense = expenseRepo.findByIdAndUserUsername(expenseId, username);
		return expense;
	}

	@Override
	public Expense create(String username, Expense expense) {
		User user = userRepo.findByUsername(username);
		expense.setUser(user);
		expense = expenseRepo.saveAndFlush(expense);
		return expense;
	}

	@Override
	public Expense update(String username, Integer expenseId, Expense expense) {
		Expense dbExpense = show(username, expenseId);
		if (dbExpense != null) {
			dbExpense.setDescription(expense.getDescription());
			dbExpense.setAmount(expense.getAmount());
			dbExpense.setCategory(expense.getCategory());
			dbExpense.setFrequency(expense.getFrequency());
			expenseRepo.saveAndFlush(dbExpense);
			return dbExpense;
		}
		return null;
	}

	@Override
	public Boolean delete(String username, Integer expenseId) {
		Expense dbExpense = show(username, expenseId);
		if (dbExpense != null) {
			expenseRepo.deleteById(expenseId);
			return true;
		}
		return false;
	}
	
}
