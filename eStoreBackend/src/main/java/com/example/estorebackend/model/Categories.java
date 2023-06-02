package com.example.estorebackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer categoryId;
	String categoryName;
	String categoryDescription;
	String catgeoryImageUrl;
	Integer active;
	Date addedOn;
	
	public Categories() {
		
	}
	
	public Categories(Integer categoryId, String categoryName, String categoryDescription, String catgeoryImageUrl,
			Integer active, Date addedOn) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.catgeoryImageUrl = catgeoryImageUrl;
		this.active = active;
		this.addedOn = addedOn;
	}
	

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCatgeoryImageUrl() {
		return catgeoryImageUrl;
	}

	public void setCatgeoryImageUrl(String catgeoryImageUrl) {
		this.catgeoryImageUrl = catgeoryImageUrl;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + ", catgeoryImageUrl=" + catgeoryImageUrl + ", active=" + active + ", addedOn="
				+ addedOn + "]";
	}
	
}
