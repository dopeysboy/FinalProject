package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.DebtType;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtTypeRepository;

@Service
public class DebtTypeServiceImpl implements DebtTypeService {
	
	@Autowired
	DebtTypeRepository debtTypeRepo;
	
	public List<DebtType> findAll(){
		return debtTypeRepo.findAll();
	}

	@Override
	public DebtType findByDescription(String desc) {
		return debtTypeRepo.findByDescription(desc);
	}

	@Override
	public DebtType findById(int debtTypeId) {
		Optional <DebtType> opDebtType = debtTypeRepo.findById(debtTypeId);
		DebtType d = null;
		if(opDebtType.isPresent()) {
			d = opDebtType.get();
		}
		return d;
	}

	@Override
	public DebtType createDebtType(DebtType debtType, User user) {
		if(user.getRole().equals("admin")) {
			return debtTypeRepo.saveAndFlush(debtType);
		}
		else {
			return null;
		}
	}

	@Override
	public DebtType updateDebtType(DebtType debtType, String name, User user) {
		if(user.getRole().equals("admin")) {
		DebtType managed = findByDescription(name);
		managed.setDefaultPriority(debtType.getDefaultPriority());
		managed.setDescription(debtType.getDescription());
		return managed;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean destroyDebtType(DebtType debtType, User user) {
			if(user.getRole().equals("admin")) {
				debtTypeRepo.delete(debtType);
				
				}
				if(!debtTypeRepo.findById(debtType.getId()).isPresent()) {
					return true;
			}
		return false;
	}

}
