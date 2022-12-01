package com.skilldistillery.rotahu.services;

import java.util.List;
import java.util.Map;

import com.skilldistillery.rotahu.entities.Debt;

public interface DebtCalculatorService {

	Map<Integer, Double> calculatePayments(Debt debt, double payment, Integer monthNum);

//	Map<String, Map<Integer, Double>> calculateBestPayment(List<Debt> debts, Map<String, Double> paymentAmounts,
//			double leftoverIncome);

	Map<String, Map<Integer, Double>> calculateDebtsAfterDebtGoesToZero(Map<String, Map<Integer, Double>> debtPlan,
			List<String> paidDebts, Integer monthNum, Double residualIncome, List<Debt> debts2);

}
