package com.skilldistillery.rotahu.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="debt_lender")
public class DebtLender {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@Column(name="site_url")
	private String siteUrl;
	
	@OneToMany(mappedBy = "debtLender")
	@JsonIgnoreProperties("debtLender")
	private List<Debt> debts;
	
	@OneToMany(mappedBy = "debtLender")
	@JsonIgnoreProperties("debtLender")
	private List<Rating> ratings;
	
	public DebtLender() {}

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public List<Debt> getDebts() {
		return debts;
	}

	public void setDebts(List<Debt> debts) {
		this.debts = debts;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public void addDebt(Debt debt) {
		if(debts == null) {
			debts = new ArrayList<>();
		}
		if(!debts.contains(debt)) {
			debts.add(debt);
			debt.setDebtLender(this);
		}
	}
	
	public void removeDebt(Debt debt) {
		if(debts != null && debts.contains(debt)) {
			debts.remove(debt);
			debt.setDebtLender(null);
		}
	}
	
	public void addRating(Rating rating) {
		if(ratings == null) {
			ratings = new ArrayList<>();
		}
		
		if(!ratings.contains(rating)) {
			ratings.add(rating);
			rating.setDebtLender(this);
		}
	}
	
	public void removeRating(Rating rating) {
		if(ratings != null && ratings.contains(rating)) {
			ratings.remove(rating);
			rating.setDebtLender(null);
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
		DebtLender other = (DebtLender) obj;
		return id == other.id;
	}
}
