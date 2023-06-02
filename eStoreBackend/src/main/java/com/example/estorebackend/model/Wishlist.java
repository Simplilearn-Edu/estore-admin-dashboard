package com.example.estorebackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wishlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer wishListId;
	Integer productId;
	Integer userId;
	
	public Wishlist() {
		
	}

	public Wishlist(Integer wishListId, Integer productId, Integer userId) {
		this.wishListId = wishListId;
		this.productId = productId;
		this.userId = userId;
	}
	

	public Integer getWishListId() {
		return wishListId;
	}

	public void setWishListId(Integer wishListId) {
		this.wishListId = wishListId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Wishlist [wishListId=" + wishListId + ", productId=" + productId + ", userId=" + userId + "]";
	}
	
	
	
}
