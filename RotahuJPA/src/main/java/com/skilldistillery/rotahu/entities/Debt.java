package com.skilldistillery.rotahu.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Debt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="monthly_interest_rate")
	private double monthlyInterestRate;
	
	@Column(name="minimum_monthly_payment")
	private double minimumMonthlyPayment;
	
	@Column(name="initial_balance")
	private double initialBalance;
	
	@Column(name="current_balance")
	private Double currentBalance;
	
	private Integer priority;
	
	@ManyToOne
	@JoinColumn(name="debt_type_id")
	private DebtType debtType;
	
	@ManyToOne
	@JoinColumn(name="debt_lender_id")
	private DebtLender debtLender;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "debt")
	private List<Payment>payments;
	
	public Debt() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public void setMonthlyInterestRate(double monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public double getMinimumMonthlyPayment() {
		return minimumMonthlyPayment;
	}

	public void setMinimumMonthlyPayment(double minimumMonthlyPayment) {
		this.minimumMonthlyPayment = minimumMonthlyPayment;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public DebtType getDebtType() {
		return debtType;
	}

	public void setDebtType(DebtType debtType) {
		this.debtType = debtType;
	}

	public DebtLender getDebtLender() {
		return debtLender;
	}

	public void setDebtLender(DebtLender debtLender) {
		this.debtLender = debtLender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public void addPayment(Payment payment) {
		if(payments == null) {
			payments = new ArrayList<>(); 
		}
		
		if(!payments.contains(payment)) {
			payments.add(payment);
			payment.setDebt(this);
		}
	}
	
	public void removePayment(Payment payment) {
		if(payments != null && payments.contains(payment)) {
			payments.remove(payment);
			payment.setDebt(null);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Debt other = (Debt) obj;
		return id == other.id;
	}
}
