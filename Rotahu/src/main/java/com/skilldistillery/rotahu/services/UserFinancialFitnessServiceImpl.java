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
	
	private List<DebtType> goodDebtTypes = new ArrayList<>();
	
	{
		goodDebtTypes.add(new DebtType(2));
		goodDebtTypes.add(new DebtType(5));
	}
	
	private final double GOOD_DEBT_WEIGHT = .1;
	
	private final double MIN_RI = 100;
	private final double MAX_RI = 5000;
	
	private final double MIN_DA = 1000;
	private final double MAX_DA = 100000;
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
		
		if(resIncome > MAX_RI) {
			fitness = 50;
		} else if(resIncome < MIN_RI) {
			fitness = 0;
		} else {
			fitness = riFitnessFunction(resIncome);
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
				totalDebtAmt += (d.getCurrentBalance() * GOOD_DEBT_WEIGHT);
			} else {
				totalDebtAmt += d.getCurrentBalance();
			}
		}
		
		if(totalDebtAmt > MAX_DA) {
			fitness = 0;
		} else if(totalDebtAmt < MIN_DA) {
			fitness = 50;
		} else {
			fitness = debtFitnessFunction(totalDebtAmt);
		}
		return fitness;
	}
	
	private Integer riFitnessFunction(Double ri) {
		return (int) ( ((48 * ri) + MAX_RI - (49 * MIN_RI)) / (MAX_RI - MIN_RI) );
	}
	
	private Integer debtFitnessFunction(Double da) {
		return (int) ( ((48 * da) + MAX_DA - (49 * MIN_DA)) / (MAX_DA - MIN_DA) );
	}
}