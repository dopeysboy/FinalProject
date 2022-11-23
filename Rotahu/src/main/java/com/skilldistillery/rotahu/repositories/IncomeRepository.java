package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Expense;
import com.skilldistillery.rotahu.entities.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
	
	List<Income> findByUserUsername(String username);
	Income findByIdAndUserUsername(Integer incomeId, String username);
	
}
