package com.skilldistillery.rotahu.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Rating {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private Integer rate;
	
	private  String description;
	
	@CreationTimestamp
	@Column(name = "rating_date")
	private LocalDateTime ratingDate;
	
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "debt_lender_id")
	private DebtLender debtLender;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Rating() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getRatingDate() {
		return ratingDate;
	}

	public void setRatingDate(LocalDateTime ratingDate) {
		this.ratingDate = ratingDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
		Rating other = (Rating) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", rate=" + rate + ", description=" + description + ", ratingDate=" + ratingDate
				+ ", enabled=" + enabled + "]";
	}
	
}
