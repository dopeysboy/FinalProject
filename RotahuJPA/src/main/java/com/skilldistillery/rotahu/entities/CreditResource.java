package com.skilldistillery.rotahu.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="credit_resource")
public class CreditResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	
	@Column(name="video_url")
	private String videoUrl;
	
	@Column(name="site_url")
	private String siteUrl;
	
	@Column(name="debt_intensity")
	private Integer debtIntensity;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private Boolean enabled;
	
	@ManyToMany
	@JoinTable(name="user_has_credit_resources",
	joinColumns = @JoinColumn(name="credit_resource_id"),
	inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<User> servedTo;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User createdBy;
	
	public CreditResource() {}

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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public Integer getDebtIntensity() {
		return debtIntensity;
	}

	public void setDebtIntensity(Integer debtIntensity) {
		this.debtIntensity = debtIntensity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	public List<User> getServedTo() {
		return servedTo;
	}

	public void setServedTo(List<User> servedTo) {
		this.servedTo = servedTo;
	}

	public void addServedTo(User user) {
		if(servedTo == null) {
			servedTo = new ArrayList<>();
		}
		
		if(!servedTo.contains(user)) {
			servedTo.add(user);
			user.addCreditResource(this);
		}
	}
	
	public void removeServedTo(User user) {
		if(servedTo != null && servedTo.contains(user)) {
			servedTo.add(user);
			user.addCreditResource(this);
		}
	}
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
		CreditResource other = (CreditResource) obj;
		return id == other.id;
	}
	
	
}
