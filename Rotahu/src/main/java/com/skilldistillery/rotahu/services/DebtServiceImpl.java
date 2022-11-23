package com.skilldistillery.rotahu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtRepository;

@Service
public class DebtServiceImpl implements DebtService{

	@Autowired
	private DebtRepository debtRepo;
	
	@Override
	public List<Debt> getDebtsByUser(User user){
		List<Debt> debts = debtRepo.findByUser(user);
		
		return debts;
	}
	
	@Override
	public List<Debt> getDebtsByDebtLender(DebtLender debtLender, User user){
		List<Debt> debts = debtRepo.findByDebtLenderAndUser(debtLender, user);
		
		return debts;
	}
	
	@Override
	public List<Debt> getDebtsByApr(Double apr, User user){
		List<Debt> debts = debtRepo.findByMonthlyInterestRateAndUser(apr, user);
		
		return debts;
	}
	
	@Override
	public Debt createDebt(Debt debt, User user) {
		/*
		 * checks for things we want to ensure are filled out
		 * will go before save and flush
		 */
		debt.setUser(user);
		debt = debtRepo.saveAndFlush(debt);
		
		return debt;
	}
	
	@Override
	public boolean destroyDebt(Debt debt, User user) {
		debtRepo.delete(debt);
		
		if(debtRepo.findByUser(debt.getUser()).contains(debt)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Debt updateDebt(Debt debt, int debtId, User user) {
		if(debtRepo.findById(debtId))
	}
}
