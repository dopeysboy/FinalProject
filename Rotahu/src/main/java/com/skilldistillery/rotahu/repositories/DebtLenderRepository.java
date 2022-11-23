package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.DebtLender;

public interface DebtLenderRepository extends JpaRepository<DebtLender, Integer>{

	List<DebtLender> findByNameContains(String keyword);
}
