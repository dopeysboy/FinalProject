package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.DebtLender;
import com.skilldistillery.rotahu.entities.Income;
import com.skilldistillery.rotahu.entities.Rating;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtLenderRepository;
import com.skilldistillery.rotahu.repositories.RatingRepository;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	RatingRepository ratingRepo;
	@Autowired
	DebtLenderRepository lenderRepo;
	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Rating> indexByDebtLender(Integer debtLenderId) {
		List<Rating> ratings = ratingRepo.findByDebtLenderId(debtLenderId);
		return ratings;
	}
	
	@Override
	public List<Rating> indexByUser(String username) {
		List<Rating> ratings = ratingRepo.findByUserUsername(username);
		return ratings;
	}

	@Override
	public Rating showByIdAndUsername(Integer ratingId, String username) {
		Rating rating = ratingRepo.findByIdAndUserUsername(ratingId, username);
		return rating;
	}
	
	@Override
	public Rating create(String username, Rating rating, Integer debtLenderId) {
		Optional<DebtLender> op = lenderRepo.findById(debtLenderId);
		User user = userRepo.findByUsername(username);
		if (op.isPresent()) {
			rating.setDebtLender(op.get());
			rating.setUser(user);
			rating = ratingRepo.saveAndFlush(rating);
		}
		return rating;
	}

	@Override
	public Rating update(String username, Integer ratingId, Rating rating) {
		Rating dbRating = showByIdAndUsername(ratingId, username);
		if (dbRating != null) {
			dbRating.setRate(rating.getRate());
			dbRating.setDescription(rating.getDescription());
			dbRating.setEnabled(rating.getEnabled());
			ratingRepo.saveAndFlush(dbRating);
			return dbRating;
		}
		return null;
	}

	@Override
	public Boolean delete(String username, Integer ratingId) {
		Rating dbRating = showByIdAndUsername(ratingId, username);
		if (dbRating != null) {
			ratingRepo.deleteById(ratingId);
			return true;
		}
		return false;
	}

}
