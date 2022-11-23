package com.skilldistillery.rotahu.services;

import java.util.List;

import com.skilldistillery.rotahu.entities.Payment;

public interface PaymentService {
	
	List<Payment> index(String username);
	List<Payment> indexForGivenDebt(String username, Integer debtId);
	Payment show(String username, Integer paymentId);
	Payment create(String username, Integer debtId, Payment payment);
	Payment update(String username, Integer debtId, Integer paymentId, Payment payment);
	Boolean delete(String username, Integer debtId, Integer paymentId);
	
}
