package com.skilldistillery.rotahu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Category;
import com.skilldistillery.rotahu.services.CategoryService;


@RestController
@CrossOrigin({"*", "http://localhost"})
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired

	
	@GetMapping("category")
	public List<Category> allCategories(){
		return categoryService.findAll();
		
	}

}
