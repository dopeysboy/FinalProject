package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	
	List<Rating> findByDebtLenderId(Integer lenderId);
	
	List<Rating> findByUserUsername(String username);
	Rating findByIdAndUserUsername(Integer ratingId, String username);
	
}
