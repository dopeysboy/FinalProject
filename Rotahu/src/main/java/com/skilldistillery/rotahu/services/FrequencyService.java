package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Frequency;
import com.skilldistillery.rotahu.entities.User;

public interface FrequencyService {
	
	List<Frequency> findAll();
	
	Frequency findByName(String name);
	
	Frequency findById(int frequencyId);
	
	Frequency createFrequency(Frequency frequency, User user);
	
	Frequency updateFrequency(Frequency frequency, String name, User user);
	
	boolean destroyFrequency(Frequency frequency, User user);
}
