package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.rotahu.entities.Frequency;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.FrequencyRepository;

public class FrequencyServiceImpl implements FrequencyService {
	
	@Autowired
	FrequencyRepository frequencyRepo;

	@Override
	public List<Frequency> findAll() {
		return frequencyRepo.findAll();
	}

	@Override
	public Frequency findByName(String name) {
		return frequencyRepo.findByFrequency_Name(name);
	}

	@Override
	public Frequency findById(int frequencyId) {
		Optional<Frequency> opFreq = frequencyRepo.findById(frequencyId);
		Frequency f = null;
		if(opFreq.isPresent()) {
			f = opFreq.get();
		}
		return f;
	}

	@Override
	public Frequency createFrequency(Frequency frequency, User user) {
		if(user.getRole().equals("admin")) {
			return frequencyRepo.saveAndFlush(frequency);
	}else {
		return null;
	}
	}

	@Override
	public Frequency updateFrequency(Frequency frequency, String name, User user) {
		if(user.getRole().equals("admin")) {
			Frequency managed = findByName(name);
			managed.setName(name);
			return frequencyRepo.save(managed);
	}else {
		return null;
	}
	}

	@Override
	public boolean destroyFrequency(Frequency frequency, User user) {
		if(user.getRole().equals("admin")) {
		frequencyRepo.deleteById(frequency.getId());
			if(!frequencyRepo.findById(frequency.getId()).isPresent()) {
				return true;
			}else {
				return false;
			}
		

	}
	
	

}
