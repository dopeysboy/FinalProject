package com.skilldistillery.rotahu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Frequency;

public interface FrequencyRepository extends JpaRepository<Frequency, Integer> {
	
	Frequency findByFrequency_Name (String name);

}
