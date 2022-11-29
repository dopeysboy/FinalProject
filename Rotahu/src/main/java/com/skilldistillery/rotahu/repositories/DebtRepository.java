package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.User;

public interface DebtRepository extends JpaRepository<Debt, Integer>{
	List<Debt> findByUser(User user);
	
	List<Debt> findByDebtLenderAndUser(DebtLender debtLender, User user);
	
	List<Debt> findByAnnualPercentageRateAndUser(Double apr, User user);
}
