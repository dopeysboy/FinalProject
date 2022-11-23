package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtRepository;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class DebtServiceImpl implements DebtService{

	@Autowired
	private DebtRepository debtRepo;
	@Autowired
	private UserRepository userRepo;
	
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
		user.addDebt(debt);
		
		userRepo.saveAndFlush(user);
		debt = debtRepo.saveAndFlush(debt);
		
		return debt;
	}
	
	@Override
	public boolean destroyDebt(Debt debt, User user) {
		if(debtRepo.findByUser(user).contains(debt)) {
			user.removeDebt(debt);
			
			userRepo.saveAndFlush(user);
			/**
			 * MIGHT HAVE TO ITERATIVELY DELETE EACH ASSOCIATION
			 */
			debtRepo.delete(debt);
			
			if(debtRepo.findByUser(user).contains(debt)) {
				//Remains in the database
				return false;
			} else {
				//Was successfully deleted
				return true;
			}
		} else {
			//User does not own that debt
			return false;
		}
	}
	
	@Override
	public Debt updateDebt(Debt newDebt, int debtId, User user) {
		Optional<Debt> debtOpt = debtRepo.findById(debtId);
		
		//If debt doesn't exist by that id
		//OR
		//If debt isn't owned by the user passed
		if(!debtOpt.isPresent() || debtOpt.get().getUser() != user) {
			return null;
		}
		user.removeDebt(debtOpt.get());
		
		newDebt.setId(debtId);
		user.addDebt(newDebt);
		
		userRepo.saveAndFlush(user);
		debtRepo.saveAndFlush(newDebt);
		return newDebt;
	}
}
