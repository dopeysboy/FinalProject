package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtLenderRepository;
import com.skilldistillery.rotahu.repositories.DebtRepository;

@Service
public class DebtLenderServiceImpl implements DebtLenderService {
	@Autowired
	private DebtLenderRepository dlRepo;
	@Autowired
	private DebtRepository debtRepo;
	
	@Override
	public List<DebtLender> findAll() {
		List<DebtLender> debtLenders = dlRepo.findAll();
		
		return debtLenders;
	}

	@Override
	public List<DebtLender> findByKeyword(String keyword) {
		List<DebtLender> debtLenders = dlRepo.findByNameContains(keyword);
		
		return debtLenders;
	}

	@Override
	public DebtLender findById(int id) {
		Optional<DebtLender> dlOpt = dlRepo.findById(id);
		
		if(!dlOpt.isPresent()) {
			return null;
		}
		
		return dlOpt.get();
	}

	@Override
	public DebtLender create(DebtLender debtLender, User user) {
		if(!user.getRole().equals("admin")) {
			return null;
		}
		debtLender = dlRepo.saveAndFlush(debtLender);
		
		return debtLender;
	}

	@Override
	public DebtLender update(DebtLender debtLender, int id, User user) {
		if(!user.getRole().equals("admin")) {
			return null;
		}
		Optional<DebtLender> dlOpt = dlRepo.findById(id);
		
		if(!dlOpt.isPresent()) {
			return null;
		}
		
		debtLender.setId(id);

		debtLender = dlRepo.saveAndFlush(debtLender);
		return debtLender;
	}

	@Override
	public boolean destroy(DebtLender debtLender, User user) {
		if(!user.getRole().equals("admin")) {
			return false;
		}
		
		dlRepo.delete(debtLender);
		
		if(dlRepo.findById(debtLender.getId()).isPresent()) {
			return false;
		}
		return true;
	}
	
	@Override
	public DebtLender addDebtToDebtLender(DebtLender dl, Debt debt, User user) {
		if(!debt.getUser().equals(user)) {
			return null;
		}
		
		dl.addDebt(debt);
		
		debtRepo.saveAndFlush(debt);
		dl = dlRepo.saveAndFlush(dl);
		
		return dl;
	}
	
	@Override
	public DebtLender removeDebtFromDebtLender(DebtLender dl, Debt debt, User user) {
		if(!debt.getUser().equals(user)) {
			return null;
		}
		dl.removeDebt(debt);
		
		debtRepo.saveAndFlush(debt);
		dl = dlRepo.saveAndFlush(dl);
		
		return dl;
	}
}
