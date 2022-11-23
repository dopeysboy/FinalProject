package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.DebtType;
import com.skilldistillery.rotahu.entities.User;

public interface DebtTypeService {
	
	List<DebtType> findAll();
	
	DebtType findByDescription(String desc);
	
	DebtType findById(int debtTypeId);
	
	DebtType createDebtType(DebtType debtType, User user);
	
	DebtType updateDebtType(DebtType debtType, String name, User user);
	
	boolean destroyDebtType(DebtType debtType, User user);


}
