package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Category;
import com.skilldistillery.rotahu.services.CategoryService;


@RestController
@CrossOrigin({"*", "http://localhost/"})
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired

	
	@GetMapping("category")
	public List<Category> index(HttpServletRequest req, HttpServletResponse resp, Principal principal){
		String username = principal.getName();
		List<Category> todos = categoryService.findAll(username);
		if(todos.size() == 0) {
			resp.setStatus(404);
		}
		return todos;
		
	}
	
	@GetMapping("category/{categoryId}")
	public Category findById(HttpServletRequest req, HttpServletResponse res, @PathVariable Integer categoryId, Principal principal) {
		String username = principal.getName();
		Category category = categoryService.findById(categoryId, username);
		if(category == null) {
			res.setStatus(404);
		}
		return category;
		
	}

}
