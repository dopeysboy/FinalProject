package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	List<Category> findByName(String name);
	
	Category findByExpenses_Description(String description);
	
	Category findByExpenses_id(int id);
	
	Category findByIncome_Description(String description);
	
	Category findByIncome_id(int id);
}
