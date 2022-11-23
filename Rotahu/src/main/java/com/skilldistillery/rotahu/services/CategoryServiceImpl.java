package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Category;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Category> findAll() {
		List<Category> cats = catRepo.findAll();
		
		return cats;
	}

	@Override
	public Category findById(int id) {
		Optional<Category> catOpt = catRepo.findById(id);
		
		if(catOpt.isPresent()) {
			return catOpt.get();
		} else {
			return null;
		}
	}

	@Override
	public Category create(Category category, User user) {
		if(user.getRole().equals("admin")) {
			category = catRepo.saveAndFlush(category);
		
			return category;
		} else {
			return null;
		}
	}

	@Override
	public Category update(Category category, int catId, User user) {
		if(user.getRole().equals("admin")) {
			Optional<Category> catOpt = catRepo.findById(catId);
		
			if(!catOpt.isPresent()) {
				return null;
			}
			
			category.setId(catId);
			
			category = catRepo.saveAndFlush(category);
			return category;
		} else {
			return null;
		}
	}

	@Override
	public boolean destroy(Category category, User user) {
		if(user.getRole().equals("admin")) {
			catRepo.delete(category);
			
			if(!catRepo.findById(category.getId()).isPresent()) {
				return true;
			}
		}
		
		return false;
	}
	
}
