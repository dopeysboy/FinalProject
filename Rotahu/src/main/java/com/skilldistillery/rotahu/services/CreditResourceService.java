package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.CreditResource;
import com.skilldistillery.rotahu.entities.User;

public interface CreditResourceService {

	List<CreditResource> findAll();

	CreditResource findById(Integer id);
	
	CreditResource createCreditResource(CreditResource cr, User user);

	CreditResource updateCreditResource(CreditResource newCr, User user, Integer oldCrId);

}
