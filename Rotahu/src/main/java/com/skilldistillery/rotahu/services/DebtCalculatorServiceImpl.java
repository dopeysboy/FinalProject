package com.skilldistillery.rotahu.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtType;

@Service
public class DebtCalculatorServiceImpl implements DebtCalculatorService{

	private Double remainingIncome;
	private List<DebtType> debtTypesWithLowPrio = new ArrayList<>();
	
	{
		debtTypesWithLowPrio.add(new DebtType(10));
		debtTypesWithLowPrio.add(new DebtType(9));
	}
	
	@Override
	public Map<Integer, Double> calculatePayments(Debt debt, double payment){
		Map<Integer, Double> payments = new HashMap<>();
		double interestRate = debt.getAnnualPercentageRate();
		double debtTotal = debt.getCurrentBalance();
		
		//turn from annual to monthly
		interestRate /= 12;
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
	
	//TODO: fix issue where if minimum payment is met but does not cause the highest interest, there is a chance for the total to increase
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
				Map<Integer, Double> map = calculatePayments(debt, paymentAmounts.get(debt.getName()));
				if(map.get(59) != null && map.get(1) < map.get(59)) {
					interestPaid.put(debt.getName(), Double.MAX_VALUE);
				} else if(debtTypesWithLowPrio.contains(debt.getDebtType()) && debt.getPriority() >= debt.getDebtType().getDefaultPriority()){
					interestPaid.put(debt.getName(), Double.MIN_VALUE);
				} else {
					interestPaid.put(debt.getName(), map.get(-1));
				}
			});
			
			//"sort" the map, so the highest interest paid is the first entry in the stream
			Stream<Map.Entry<String, Double>> sorted = interestPaid.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

			Map.Entry<String, Double> highestInterest = sorted.findFirst().get();
			
			//the name of the debt for highest interest paid
			String key =  highestInterest.getKey();
			
			remainingIncome = (double) Math.round(remainingIncome / 4 * 100.0) / 100;
			Double value = paymentAmounts.get(key) + remainingIncome;
			
			paymentAmounts.put(key, value);
			
			if(remainingIncome < .25) {
				break;
			}
		}
		debts.stream().forEach( (debt) -> {
			Map<Integer, Double> payPlan = calculatePayments(debt, paymentAmounts.get( debt.getName()));
			payPlan.put(-2, paymentAmounts.get(debt.getName()));
			
			returnMap.put(debt.getName(), payPlan);	
		});
		
		return returnMap;
	}
}
