package com.skilldistillery.rotahu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rotahu.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	
	List<Payment> findByDebtUserUsername(String username);
	Payment findByIdAndDebtUserUsername(Integer paymentId, String username);
	
}
