package com.skilldistillery.rotahu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.DebtType;

public interface DebtTypeRepository extends JpaRepository <DebtType, Integer> {
	
	DebtType findByName (String name);

}
