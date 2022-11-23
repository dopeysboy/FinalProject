package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Expense;

public interface ExpenseService {
	
	List<Expense> index(String username);
	Expense show(String username, Integer expenseId);
	Expense create(String username, Expense expense);
	Expense update(String username, Integer expenseId, Expense expense);
	Boolean delete(String username, Integer expenseId);
	
}
