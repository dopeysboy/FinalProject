package com.skilldistillery.rotahu.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.rotahu.repositories.FrequencyRepository;

public class FrequencyServiceImpl implements FrequencyService {
	
	@Autowired
	FrequencyRepository frequencyRepo;
	
	

}
