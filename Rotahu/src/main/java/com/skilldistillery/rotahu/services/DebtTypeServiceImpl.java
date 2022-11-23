package com.skilldistillery.rotahu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.rotahu.entities.DebtType;
import com.skilldistillery.rotahu.repositories.DebtTypeRepository;

public class DebtTypeServiceImpl implements DebtTypeService {
	
	@Autowired
	DebtTypeRepository debtTypeRepo;
	
	public List<DebtType> findAll(){
		return debtTypeRepo.findAll();
	}

}
