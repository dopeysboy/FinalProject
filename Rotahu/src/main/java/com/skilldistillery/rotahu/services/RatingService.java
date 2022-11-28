package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.entities.Rating;

public interface RatingService {
	
	List<Rating> indexByDebtLender(Integer debtLenderId);
	List<Rating> indexByUser(String username);
	Rating showByIdAndUsername(Integer ratingId, String username);
	Rating create(String username, Rating rating, Integer debtLenderId);
	Rating update(String username, Integer ratingId, Rating rating);
	Boolean delete(String username, Integer ratingId);
	
}
