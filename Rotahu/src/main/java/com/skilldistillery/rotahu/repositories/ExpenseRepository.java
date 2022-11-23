package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
	
	List<Expense> findByUserUsername(String username);
	Expense findByIdAndUserUsername(Integer expenseId, String username);
	
}
