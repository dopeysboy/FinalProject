package com.skilldistillery.rotahu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.DebtType;
import com.skilldistillery.rotahu.services.DebtTypeService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost"})
public class DebtTypeController {
	@Autowired
	private DebtTypeService dtServ;
	
	@GetMapping("debttype")
	public List<DebtType> getAll(){
		List<DebtType> debtTypes = dtServ.findAll();
		
		return debtTypes;
	}
}
