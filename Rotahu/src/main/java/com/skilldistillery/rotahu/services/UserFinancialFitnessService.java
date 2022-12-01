package com.skilldistillery.rotahu.services;

import com.skilldistillery.rotahu.entities.User;

public interface UserFinancialFitnessService {

	Integer determineFitness(User user);

	Integer calculateResidualIncomeFitness(User user);
	
	Integer calculateDebtFitness(User user);
}
