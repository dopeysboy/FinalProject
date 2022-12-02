package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.skilldistillery.rotahu.entities.CreditResource;
import com.skilldistillery.rotahu.entities.User;

public interface CreditResourceRepository extends JpaRepositoryImplementation<CreditResource, Integer>{

	List<CreditResource> findByCreatedBy(User creator);
}
