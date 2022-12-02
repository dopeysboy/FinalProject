package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.CreditResource;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.CreditResourceRepository;

@Service
public class CreditResourceServiceImpl implements CreditResourceService{
	@Autowired
	private CreditResourceRepository crRepo;
	
	@Override
	public List<CreditResource> findAll(){
		List<CreditResource> crs = crRepo.findAll();
		
		return crs;
	}
	
	@Override
	public CreditResource findById(Integer id) {
		Optional<CreditResource> cr = crRepo.findById(id);
		
		if(cr.isPresent()) {
			return cr.get();
		} else {
			return null;
		}
	}
	
	@Override
	public CreditResource createCreditResource(CreditResource cr, User user) {
		cr.setCreatedBy(user);
		cr.setEnabled(true);
		
		cr = crRepo.saveAndFlush(cr);
		
		return cr;
	}
	
	@Override
	public CreditResource updateCreditResource(CreditResource newCr, User user, Integer oldCrId) {
		Optional<CreditResource> oldCr = crRepo.findById(oldCrId);
		
		if(!oldCr.isPresent()) {
			return null;
		}
		if(user.getRole().equals("admin") || user.equals(oldCr.get().getCreatedBy())) {
			newCr.setId(oldCrId);
			
			return crRepo.saveAndFlush(newCr);
		} else {
			return null;
		}
	}
}
