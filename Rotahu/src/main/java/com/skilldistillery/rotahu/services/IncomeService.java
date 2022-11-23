package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Income;

public interface IncomeService {
	
	List<Income> index(String username);
	Income show(String username, Integer incomeId);
	Income create(String username, Income income);
	Income update(String username, Integer incomeId, Income income);
	Boolean delete(String username, Integer incomeId);
	
}
