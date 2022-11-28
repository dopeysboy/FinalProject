package com.skilldistillery.rotahu.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rotahu.entities.Payment;
import com.skilldistillery.rotahu.services.PaymentService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost/"})
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("payment")
	public List<Payment> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		String username = principal.getName();
		List<Payment> todos = paymentService.index(username);
		if (todos.size() == 0) {
			res.setStatus(404);
		}
		return todos;
	}
	
	@GetMapping("payment/{debtId}")
	public List<Payment> indexForGivenDebt(String username, @PathVariable Integer debtId ,HttpServletRequest req, HttpServletResponse res, Principal principal){
		return null;
		
	}
	
	@PostMapping("payment")
	public Payment create(HttpServletRequest req, HttpServletResponse res, @RequestBody Payment payment, Integer debtId, Principal principal) {
		debtId = payment.getDebt().getId();
		String username = principal.getName();
		try {
			payment = paymentService.create(username, debtId, payment);
			if (payment == null) {
				res.setStatus(401);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(payment.getId());
				res.setHeader("Location", url.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			payment = null;
		}
		return payment;
	}

	@PutMapping("payment/{paymentId}")
	public Payment update(HttpServletRequest req, HttpServletResponse res, Integer debtId, @PathVariable Integer paymentId, @RequestBody Payment payment, Principal principal) {
		System.out.println("payment");
		String username = principal.getName();
		try {
			payment = paymentService.update(username, debtId, paymentId, payment);
			if (payment == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			payment = null;
		}
		return payment;
	}

	@DeleteMapping("payment/{paymentId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, Integer debtId, @PathVariable Integer paymentId,  Principal principal) {
		String username = principal.getName();
		try {
			boolean deleted = paymentService.delete(username, debtId, paymentId);
			if (!deleted) {
				res.setStatus(404);
			}
			res.setStatus(204);
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
