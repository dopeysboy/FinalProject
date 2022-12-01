package com.skilldistillery.rotahu.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtType;
import com.skilldistillery.rotahu.entities.Expense;
import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.entities.User;

@Service
public class UserFinancialFitnessServiceImpl implements UserFinancialFitnessService{
	
	private final double goodDebtWeight = .1;
	private List<DebtType> goodDebtTypes = new ArrayList<>();
	
	{
		goodDebtTypes.add(new DebtType(2));
		goodDebtTypes.add(new DebtType(5));
	}
	
	/*
	 * determines a fitness score, with 100 being perfect
	 * and 0 being the worst possible
	 */
	@Override
	public Integer determineFitness(User user) {
		Integer resFitness = calculateResidualIncomeFitness(user);
		Integer debtFitness = calculateDebtFitness(user);
		
		return resFitness + debtFitness;
	}
	
	@Override
	public Integer calculateResidualIncomeFitness(User user) {
		Integer fitness = 0;
		Double resIncome = 0.0;
		
		List<Debt> debts = user.getDebts();
		List<Income> incomes = user.getIncomes();
		List<Expense> expenses = user.getExpenses();
		
		for(int i = 0; i < incomes.size(); i++) {
			Income inc = incomes.get(i);
			
			if(inc.getFrequency().getName().equals("quarterly")) {
				resIncome += (inc.getAmount() / 4);
			} else if(inc.getFrequency().getName().equals("weekly")) {
				resIncome += (inc.getAmount() * 4);
			} else {
				resIncome += inc.getAmount();
			}
		}
		
		for(int i = 0; i < expenses.size(); i++) {
			Expense exp = expenses.get(i);
			
			if(exp.getFrequency().getName().equals("quarterly")) {
				resIncome -= (exp.getAmount() / 4);
			} else if(exp.getFrequency().getName().equals("weekly")) {
				resIncome -= (exp.getAmount() * 4);
			} else {
				resIncome -= exp.getAmount();
			}
		}
		
		for(int i = 0; i < debts.size(); i++) {
			Debt debt = debts.get(i);
		
			resIncome -= debt.getMinimumMonthlyPayment();
		
		}
		
		if(resIncome > 5000) {
			fitness = 50;
		} else if(resIncome < 100) {
			fitness = 0;
		} else {
			fitness = (int)(( (12 * resIncome) + 25) / 1225);
		}
		
		return fitness;
	}
	
	@Override
	public Integer calculateDebtFitness(User user) {
		Integer fitness = 0;
		Double totalDebtAmt = 0.0;
		
		List<Debt> debts = user.getDebts();
		
		for(Debt d : debts) {
			if(goodDebtTypes.contains(d.getDebtType())) {
				totalDebtAmt += (d.getCurrentBalance() * goodDebtWeight);
			} else {
				totalDebtAmt += d.getCurrentBalance();
			}
		}
		
		if(totalDebtAmt > 100000) {
			fitness = 0;
		} else if(totalDebtAmt < 1000) {
			fitness = 50;
		} else {
			fitness = (int) (((-2 * totalDebtAmt) + 200125)  / 2);
		}
		return fitness;
	}
}