package com.skilldistillery.rotahu.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.Payment;
import com.skilldistillery.rotahu.entities.User;
import com.skilldistillery.rotahu.repositories.DebtRepository;
import com.skilldistillery.rotahu.repositories.PaymentRepository;
import com.skilldistillery.rotahu.repositories.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private DebtRepository debtRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<Payment> index(String username) {
		List<Payment> payments = paymentRepo.findByDebtUserUsername(username);
		return payments;
	}
	
	@Override
	public List<Payment> indexForGivenDebt(String username, Integer debtId) {
		List<Payment> payments = paymentRepo.findByDebtUserUsername(username);
		List<Payment> result = new ArrayList<>();
		for (Payment payment : payments) {
			if (payment.getDebt().getId() == debtId) {
				result.add(payment);
			}
		}
		return result;
	}

	@Override
	public Payment show(String username, Integer paymentId) {
		Payment payment = paymentRepo.findByIdAndDebtUserUsername(paymentId, username);
		return payment;
	}

	@Override
	public Payment create(String username, Integer debtId, Payment payment) {
		User user = userRepo.findByUsername(username);
		Optional<Debt> op = debtRepo.findById(debtId);
		if (op.isPresent()) {
			payment.setDebt(op.get());
			if (payment.getDebt().getUser() == null) {
				payment.getDebt().setUser(user);
			}
			payment = paymentRepo.saveAndFlush(payment);
			return payment;
		}
		return null;
	}

	@Override
	public Payment update(String username, Integer debtId, Integer paymentId, Payment payment) {
		Payment dbPayment = show(username, paymentId);
		if (dbPayment != null && dbPayment.getDebt().getId() == debtId) {
			dbPayment.setComment(payment.getComment());
			dbPayment.setAmount(payment.getAmount());
			paymentRepo.saveAndFlush(dbPayment);
			return dbPayment;
		}
		return null;
	}

	@Override
	public Boolean delete(String username, Integer debtId, Integer paymentId) {
		Payment dbPayment = show(username, paymentId);
		if (dbPayment != null) {
			paymentRepo.deleteById(paymentId);
			return true;
		}
		return false;
	}

}
