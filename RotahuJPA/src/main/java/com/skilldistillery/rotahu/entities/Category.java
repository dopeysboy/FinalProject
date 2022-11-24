package com.skilldistillery.rotahu.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@JsonIgnore //Properties("category")
	@OneToMany(mappedBy = "category")
	private List<Expense> expenses;
	
	@JsonIgnore //Properties("category")
	@OneToMany(mappedBy = "category")
	private List<Income> incomes;
	
	public Category() {}

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

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public void addExpense(Expense expense) {
		if(expenses == null) {
			expenses = new ArrayList<>();
		}
		
		if(!expenses.contains(expense)) {
			expenses.add(expense);
			expense.setCategory(this);
		}
	}
	
	public void removeExpense(Expense expense) {
		if(expenses != null && expenses.contains(expense)) {
			expenses.remove(expense);
			expense.setCategory(null);
		}
	}
	
	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public void addIncome(Income income) {
		if(incomes == null) {
			incomes = new ArrayList<>(); 
		}
		
		if(!incomes.contains(income)) {
			incomes.add(income);
			income.setCategory(this);
		}
	}
	
	public void removeIncome(Income income) {
		if(incomes != null && incomes.contains(income)) {
			income.setCategory(null);
			incomes.remove(income);
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
		Category other = (Category) obj;
		return id == other.id;
	}
	
	
}
