package com.skilldistillery.rotahu.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="debt_type")
public class DebtType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;
	
	@Column(name = "default_priority")
	private int defaultPriority;
	
	@OneToMany(mappedBy = "debtType")
	@JsonIgnore //Properties("debtType")
	private List<Debt> debts;
	
	public DebtType() {}

	public DebtType(int id) {
		this.id = id;
	}
	
	public DebtType(int id, String description, int defaultPriority, List<Debt> debts) {
		super();
		this.id = id;
		this.description = description;
		this.defaultPriority = defaultPriority;
		this.debts = debts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDefaultPriority() {
		return defaultPriority;
	}

	public void setDefaultPriority(int defaultPriority) {
		this.defaultPriority = defaultPriority;
	}

	public List<Debt> getDebts() {
		return debts;
	}

	public void setDebts(List<Debt> debts) {
		this.debts = debts;
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

	@Override
	public String toString() {
		return "DebtType [id=" + id + ", description=" + description + ", defaultPriority=" + defaultPriority + "]";
	}

	
}
