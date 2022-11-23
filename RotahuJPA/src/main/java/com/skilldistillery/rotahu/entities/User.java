package com.skilldistillery.rotahu.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String username;
	
	private String password;
	
	private Boolean enabled;
	
	private String role;
	
	private String email;
	
	@OneToMany(mappedBy = "user")
	private List<Income> incomes;
	
	@OneToMany(mappedBy = "user")
	private List<Expense> expenses;
	
	@OneToMany(mappedBy = "user")
	private List<Debt> debts;
	
	@OneToMany(mappedBy = "createdBy")
	private List<CreditResource> createdResources;
	
	@ManyToMany(mappedBy = "servedTo")
	private List<CreditResource> creditResources;
	
	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;

	public User() {}

	public User(int id, String username, String password, Boolean enabled, String role, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Income> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Income> incomes) {
		this.incomes = incomes;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public List<Debt> getDebts() {
		return debts;
	}

	public void setDebts(List<Debt> debts) {
		this.debts = debts;
	}

	public List<CreditResource> getCreatedResources() {
		return createdResources;
	}

	public void setCreatedResources(List<CreditResource> createdResources) {
		this.createdResources = createdResources;
	}

	public List<CreditResource> getCreditResources() {
		return creditResources;
	}

	public void setCreditResources(List<CreditResource> creditResources) {
		this.creditResources = creditResources;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + ", email=" + email + "]";
	}
	
}
