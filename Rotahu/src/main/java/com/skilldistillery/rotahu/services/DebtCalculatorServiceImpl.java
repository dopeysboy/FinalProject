package com.skilldistillery.rotahu.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.skilldistillery.rotahu.entities.Debt;
import com.skilldistillery.rotahu.entities.DebtType;

import net.bytebuddy.dynamic.scaffold.inline.DecoratingDynamicTypeBuilder;


@Service
public class DebtCalculatorServiceImpl implements DebtCalculatorService {

	private Double remainingIncome;
	private Integer monthCounter;
	private List<DebtType> debtTypesWithLowPrio = new ArrayList<>();

	{
		debtTypesWithLowPrio.add(new DebtType(2));
		debtTypesWithLowPrio.add(new DebtType(3));
		debtTypesWithLowPrio.add(new DebtType(5));
	}

	@Override
	public Map<Integer, Double> calculatePayments(Debt debt, double payment, Integer monthNum) {
		Map<Integer, Double> payments = new HashMap<>();
		double interestRate = debt.getAnnualPercentageRate();
		double debtTotal = debt.getCurrentBalance();

		// turn from annual to monthly
		interestRate /= 12;
		// turn from percent to decimal
		interestRate /= 100;

		Integer month = monthNum;
		double totalInterest = 0;

		payments.put(month - 1, debtTotal);
		
		while (debtTotal > 0 && month < 60) {
			debtTotal -= payment;

			double interest = debtTotal * interestRate;

			// rounding off interest to remove wacky numbers
			interest = (double) Math.round(interest * 100.0) / 100;

			totalInterest += interest;
			debtTotal += interest;

			// rounding off debt total to remove floating point issue
			debtTotal = (double) Math.round(debtTotal * 100.0) / 100;

			payments.put(month++, debtTotal);
		}

		// rounding off totalInterest to remove floating point issue...again
		totalInterest = (double) Math.round(totalInterest * 100.0) / 100;

		payments.put(-1, totalInterest);

		return payments;
	}

	// TODO: add priority based ordering
	@Override
	public Map<String, Map<Integer, Double>> calculateDebtsAfterDebtGoesToZero(
			Map<String, Map<Integer, Double>> debtPlan, List<String> paidDebts, Integer monthNum, Double residualIncome,
			List<Debt> debts2) {
		/**
		 * starting from month -> monthNum for each debt in debtPlan
		 * calculateBestPayment starting from monthNum and balance at the month if at a
		 * month < 60, balance == 0, repeat process setting monthNum = month where
		 * balance == 0
		 */
		Map<String, Double> paymentAmounts = new HashMap<>();
		Map<String, Double> interestPaid = new HashMap<>();

		//first iteration of the function
		if(debtPlan == null) {
			debtPlan = new HashMap<>();
		}
		
		// because I'm not 100% sure I won't be changing the original debts -- SOMETHING
		// WE DON'T WANT FOR THIS
		List<Debt> debts = new ArrayList<>(debts2);
		// using a class level variable so it can be used in a stream
		remainingIncome = residualIncome;
		monthCounter = monthNum;
		/**
		 * remove the paid debt from the list of debts to pay
		 */
		if(paidDebts != null) {
			final List<String> paidList = paidDebts; 
			debts.removeIf((d) -> paidList.contains(d.getName()));
		}
		
		if(debts.size() < 1) {
			System.out.println("returning debtPlan");
			return debtPlan;
		}
		/**
		 * make sure remainingIncome can cover the minimum payments of each payment,
		 * will set the payment for the debt to 0 if it cannot cover min payment, then
		 * set the balance of the debt to what was left in the returnmap
		 */
		for(Debt debt : debts) {
			if (remainingIncome >= debt.getMinimumMonthlyPayment()) {
				paymentAmounts.put(debt.getName(), debt.getMinimumMonthlyPayment());
				remainingIncome -= debt.getMinimumMonthlyPayment();
			} else {
				paymentAmounts.put(debt.getName(), 0.0);
			}
			//if a recursive call to this function
//			if(debtPlan.size() > 0) {
//				debt.setCurrentBalance(debtPlan.get(debt.getName()).get(monthCounter));
//			}
			
		}
		
		// will iterate until you run out of excess monthly income after minimum
		// payments
		while (remainingIncome > 0) {
			/**
			 * on first iteration: will calculate the payment plan on each debt for minimum
			 * payments
			 * 
			 * on following iterations: will calculate the payment plan on each debt using
			 * the adjusted payment amounts
			 */
			
			for(Debt debt : debts) {
				Map<Integer, Double> map = calculatePayments(debt, paymentAmounts.get(debt.getName()), monthCounter);
				
				if (map.get(59) != null && map.get(monthCounter) < map.get(59)) {
					interestPaid.put(debt.getName(), Double.MAX_VALUE);
					
				} else if (debtTypesWithLowPrio.contains(debt.getDebtType())
						&& debt.getPriority() >= debt.getDebtType().getDefaultPriority()) {
					interestPaid.put(debt.getName(), Double.MIN_VALUE);
					
				} else {
					interestPaid.put(debt.getName(), map.get(-1));
				}
			}

			// "sort" the map, so the highest interest paid is the first entry in the stream
			Stream<Map.Entry<String, Double>> sorted = interestPaid.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

			Map.Entry<String, Double> highestInterest = sorted.findFirst().get();

			// the name of the debt for highest interest paid
			String key = highestInterest.getKey();

			remainingIncome = (double) Math.round(remainingIncome / 4 * 100.0) / 100;
			Double value = paymentAmounts.get(key) + remainingIncome;

			paymentAmounts.put(key, value);

			if (remainingIncome < .25) {
				break;
			}
		}
		
		// update current debtPlan with new projection points
		if(debtPlan.size() > 1) {
			for(Debt debt : debts) {
				Map<Integer, Double> map = calculatePayments(debt, paymentAmounts.get(debt.getName()), monthCounter);
		
				Map<Integer, Double> existingPayPlan = debtPlan.entrySet().stream()
						.filter((ent) -> ent.getKey().equals(debt.getName())).findFirst().get().getValue();
		
				for (int i = monthCounter; i < 60; i++) {
					if (map.get(i) != null) {
						existingPayPlan.put(i, map.get(i));
					} else {
						existingPayPlan.remove(i);
					}
				}
		
				debtPlan.put(debt.getName(), existingPayPlan);
			}
		} else {
			for(Debt d : debts) {
				Map<Integer, Double> map = calculatePayments(d, paymentAmounts.get(d.getName()), monthCounter);
				
				debtPlan.put(d.getName(), map);
			}
		}
		// check for paid off debts after monthNum
		// name of the debt, month ended
		Map<String, Integer> debtEndPoints = new HashMap<>();

		debtPlan.entrySet().stream().forEach((ent) -> {
			// should be the lowest balance in the <Integer, Double> map
			Entry<Integer, Double> payPlan = ent.getValue().entrySet().stream().sorted(Map.Entry.comparingByValue())
					.findFirst().get();

			// if the debt ever reaches 0 or below, will take the month it happened and
			// put it in the debtEndPoints map
			if (payPlan.getValue() <= 0) {
				debtEndPoints.put(ent.getKey(), payPlan.getKey());
			}

		});
		// remove any 0 balances that happen before monthNum, because they should not
		// have been touched
		debtEndPoints.entrySet().removeIf((ent) -> ent.getValue() <= monthCounter);
		
		if(paidDebts == null) {
			paidDebts = new ArrayList<>();
		}
		
		if (debtEndPoints.size() > 0) {
			// sort the map so we can get the first new end debt
			// then set an entry object to that endpoint for access

			Entry<String, Integer> firstEndPoint = debtEndPoints.entrySet().stream()
					.sorted(Map.Entry.comparingByValue()).findFirst().get();

			for(Debt debt: debts) {
				debt.setCurrentBalance(debtPlan.get(debt.getName()).get(firstEndPoint.getValue()));
			}
			
			monthCounter = firstEndPoint.getValue() +1;
			
			for(Entry<String, Integer> ent : debtEndPoints.entrySet()) {
				if(ent.getValue() <= monthCounter) {
					paidDebts.add(ent.getKey());
				}
			}
			
			return calculateDebtsAfterDebtGoesToZero(debtPlan, paidDebts, monthCounter, residualIncome, debts);
		} else {
			return debtPlan;
		}
	}
}
	