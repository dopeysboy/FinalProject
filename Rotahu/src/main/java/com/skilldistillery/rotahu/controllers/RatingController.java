package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Rating;
import com.skilldistillery.rotahu.entities.Rating;
import com.skilldistillery.rotahu.services.RatingService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@GetMapping("debtlender/{dlid}/rating")
	public List<Rating> findByDebtLender(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer dlid) {
		List<Rating> ratings = ratingService.indexByDebtLender(dlid);
		if (ratings.size() == 0) {
			res.setStatus(404);
		}
		return ratings;
	}
	
	@GetMapping("user/{uid}/rating")
	public List<Rating> findByUser(HttpServletRequest req, HttpServletResponse res, Principal principal, @PathVariable Integer uid) {
		String username = principal.getName();
		List<Rating> ratings = ratingService.indexByUser(username);
		if (ratings.size() == 0) {
			res.setStatus(404);
		}
		return ratings;
	}
	
	@GetMapping("rating/{ratingId}")
	public Rating show(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer ratingId, Principal principal) {
		String username = principal.getName();
		Rating rating = ratingService.showByIdAndUsername(ratingId, username);
		if (rating == null) {
			res.setStatus(404);
		}
		return rating;
	}
	
	@PostMapping("debtlender/{dlid}")
	public Rating create(HttpServletRequest req, HttpServletResponse res, @RequestBody Rating rating, @PathVariable Integer dlid, Principal principal) {
		String username = principal.getName();
		try {
			rating = ratingService.create(username, rating, dlid);
			if (rating == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(rating.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			rating = null;
		}
		return rating;
	}

	@PutMapping("rating/{ratingId}")
	public Rating update(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer ratingId, @RequestBody Rating rating, Principal principal) {
		String username = principal.getName();
		try {
			rating = ratingService.update(username, ratingId, rating);
			if (rating == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			rating = null;
		}
		return rating;
	}

	@DeleteMapping("rating/{ratingId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer ratingId, Principal principal) {
		String username = principal.getName();
		try {
			boolean deleted = ratingService.delete(username, ratingId);
			if (!deleted) {
				res.setStatus(404);
			}
			res.setStatus(204);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
