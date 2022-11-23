package com.skilldistillery.rotahu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Expense;
import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.IncomeRepository;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class IncomeServiceImpl implements IncomeService {
	
	@Autowired
	private IncomeRepository incomeRepo;
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Income> index(String username) {
		List<Income> incomes = incomeRepo.findByUserUsername(username);
		return incomes;
	}

	@Override
	public Income show(String username, Integer incomeId) {
		Income income = incomeRepo.findByIdAndUserUsername(incomeId, username);
		return income;
	}

	@Override
	public Income create(String username, Income income) {
		User user = userRepo.findByUsername(username);
		income.setUser(user);
		income = incomeRepo.saveAndFlush(income);
		return income;
	}

	@Override
	public Income update(String username, Integer incomeId, Income income) {
		Income dbIncome = show(username, incomeId);
		if (dbIncome != null) {
			dbIncome.setDescription(income.getDescription());
			dbIncome.setAmount(income.getAmount());
			dbIncome.setCategory(income.getCategory());
			dbIncome.setFrequency(income.getFrequency());
			incomeRepo.saveAndFlush(dbIncome);
			return dbIncome;
		}
		return null;
	}

	@Override
	public Boolean delete(String username, Integer incomeId) {
		Income dbIncome = show(username, incomeId);
		if (dbIncome != null) {
			incomeRepo.deleteById(incomeId);
			return true;
		}
		return false;
	}
	
}
