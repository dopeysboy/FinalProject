package com.skilldistillery.rotahu.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="debt_type")
public class DebtType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;
	
	@OneToMany(mappedBy = "debtType")
	private List<Debt> debts;
	
	public DebtType() {}

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

	public List<Debt> getDebts() {
		return debts;
	}

	public void setDebts(List<Debt> debts) {
		this.debts = debts;
	}

	public void addDebt(Debt debt) {
		if(debts == null) {
			debts = new ArrayList<>();
		}
		
		if(!debts.contains(debt)) {
			debts.add(debt);
			debt.setDebtType(this);
		}
	}
	
	public void removeDebt(Debt debt) {
		if(debts != null && debts.contains(debt)) {
			debts.remove(debt);
			debt.setDebtType(null);
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
		DebtType other = (DebtType) obj;
		return id == other.id;
	}
}
