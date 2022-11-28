package com.skilldistillery.rotahu.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;

@Service
public class DebtCalculatorServiceImpl implements DebtCalculatorService{

	private Double remainingIncome;
	
	@Override
	public Map<Integer, Double> calculatePayments(Debt debt, double payment){
		Map<Integer, Double> payments = new HashMap<>();
		double interestRate = debt.getMonthlyInterestRate();
		double debtTotal = debt.getCurrentBalance();
		
		//turn from percent to decimal
		interestRate /= 100;
		
		Integer month = 1;
		double totalInterest = 0;
		
		while(debtTotal > 0 && month < 60) {
			debtTotal -= payment;
			
			double interest = debtTotal * interestRate;
			
			//rounding off interest to remove wacky numbers
			interest = (double) Math.round(interest * 100.0) / 100;
			
			totalInterest += interest;
			debtTotal += interest;
			
			//rounding off debt total to remove floating point issue
			debtTotal = (double) Math.round(debtTotal * 100.0) / 100;
			
			payments.put(month++, debtTotal);
		}
		
		//rounding off totalInterest to remove floating point issue...again
		totalInterest = (double) Math.round(totalInterest * 100.0) / 100;
		
		payments.put(-1, totalInterest);
		
		return payments;
	}
	
	@Override
	public Map<String, Map<Integer, Double>> calculateBestPayment(List<Debt> debts, double leftoverIncome){
		Map<String, Map<Integer, Double>> returnMap = new HashMap<>();
		
		Map<String, Double> interestPaid = new HashMap<>();
		Map<String, Double> paymentAmounts = new HashMap<>();
		
		remainingIncome = leftoverIncome;
		/**
		 * populate paymentAmounts with the minimum payments of each debt
		 */

		debts.stream().forEach( (debt) -> {
			if(remainingIncome >= debt.getMinimumMonthlyPayment()) {
				remainingIncome -= debt.getMinimumMonthlyPayment();
				paymentAmounts.put(debt.getName(), debt.getMinimumMonthlyPayment());
			} else {
				paymentAmounts.put(debt.getName(), 0.0);
			}
		});
		
		//will iterate until you run out of excess monthly income after minimum payments
		while(remainingIncome > 0) {
			/**
			 * on first iteration:
			 * 		will calculate the payment plan on each debt for minimum payments
			 * 
			 * on following iterations:
			 * 		will calculate the payment plan on each debt using the adjusted payment amounts
			 */
			debts.stream().forEach( (debt) -> {
				interestPaid.put(debt.getName(), 
						calculatePayments(debt, paymentAmounts.get( debt.getName() ) ).get(-1));
			});
			
			//"sort" the map, so the highest interest paid is the first entry in the stream
			Stream<Map.Entry<String, Double>> sorted = interestPaid.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

			Map.Entry<String, Double> highestInterest = sorted.findFirst().get();
			
			//the name of the debt for highest interest paid
			String key =  highestInterest.getKey();
			
			remainingIncome = (double) Math.round(remainingIncome / 2 * 100.0) / 100;
			Double value = paymentAmounts.get(key) + remainingIncome;
			
			paymentAmounts.put(key, value);
			
			if(remainingIncome < .25) {
				break;
			}
			System.out.println("key: " + key + "\tvalue: " + value);
		}
		debts.stream().forEach( (debt) -> {
			returnMap.put(debt.getName(), 
					calculatePayments(debt, paymentAmounts.get( debt.getName())));
		});
		return returnMap;
	}
}
