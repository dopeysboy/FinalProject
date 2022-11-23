package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Category;
import com.skilldistillery.rotahu.entities.User;

public interface CategoryService {

	/**
	 * Will return all Categories persisted
	 * @return List of all Categories
	 * @author tylertanner
	 */
	List<Category> findAll();
	
	/**
	 * Will return the Category represented by the given Id, or null
	 * if no Category is found
	 * @param id - the id of the Category you want to find
	 * @return Category or null if no Category is found
	 * @author tylertanner
	 */
	Category findById(int id);
	
	/**
	 * Will create a Category in the database if the passed User has a role of "admin"
	 * @param category - The Category to be persisted
	 * @param user - The User attempting to create a Category
	 * @return The Category persisted
	 * @author tylertanner
	 */
	Category create(Category category, User user);
	
	/**
	 * Given a newCategory will update the old Category if the passed User has a role of "admin"
	 * @param newCategory - The new Category to be persisted
	 * @param catId - The id of the old Category you want updated
	 * @param user - The User attempting to update the Category
	 * @return The Category that was persisted
	 * @author tylertanner
	 */
	Category update(Category newCategory, int catId, User user);
	
	/**
	 * Given a Category, will remove it from the database if the passed User has a role of "admin"
	 * @param category - The Category to be destroyed
	 * @param user - The User attempting to destroy the category
	 * @return boolean of whether the operation was successful or not
	 * @author tylertanner
	 */
	boolean destroy(Category category, User user);
}
