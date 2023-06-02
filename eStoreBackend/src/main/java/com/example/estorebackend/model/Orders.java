package com.example.estorebackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer orderId;
	Date orderDate;
	Integer orderStatus;
	Integer totalItems;
	Double itemsSubTotal;
	Double shipmentCharges;
	Double totalAmount;
	Integer paymentStatus;
	String paymentStatusTitle;
	Integer paymentMethod;
	String paymentMethodTitle;
	Integer userId;
	String name;
	String email;
	Long contact;
	String address;
	
	
	public Orders() {
		
	}


	public Orders(Integer orderId, Date orderDate, Integer orderStatus, Integer totalItems,
			Double itemsSubTotal, Double shipmentCharges, Double totalAmount, Integer paymentStatus,
			String paymentStatusTitle, Integer paymentMethod, String paymentMethodTitle, Integer userId, String name,
			String email, Long contact, String address) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.totalItems = totalItems;
		this.itemsSubTotal = itemsSubTotal;
		this.shipmentCharges = shipmentCharges;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.paymentStatusTitle = paymentStatusTitle;
		this.paymentMethod = paymentMethod;
		this.paymentMethodTitle = paymentMethodTitle;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.address = address;
	}





	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Integer getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Integer getTotalItems() {
		return totalItems;
	}


	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}


	public Double getItemsSubTotal() {
		return itemsSubTotal;
	}


	public void setItemsSubTotal(Double itemsSubTotal) {
		this.itemsSubTotal = itemsSubTotal;
	}


	public Double getShipmentCharges() {
		return shipmentCharges;
	}


	public void setShipmentCharges(Double shipmentCharges) {
		this.shipmentCharges = shipmentCharges;
	}


	public Double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Integer getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}


	public String getPaymentStatusTitle() {
		return paymentStatusTitle;
	}


	public void setPaymentStatusTitle(String paymentStatusTitle) {
		this.paymentStatusTitle = paymentStatusTitle;
	}


	public Integer getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public String getPaymentMethodTitle() {
		return paymentMethodTitle;
	}


	public void setPaymentMethodTitle(String paymentMethodTitle) {
		this.paymentMethodTitle = paymentMethodTitle;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getContact() {
		return contact;
	}


	public void setContact(Long contact) {
		this.contact = contact;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", totalItems=" + totalItems + ", itemsSubTotal=" + itemsSubTotal
				+ ", shipmentCharges=" + shipmentCharges + ", totalAmount=" + totalAmount + ", paymentStatus="
				+ paymentStatus + ", paymentStatusTitle=" + paymentStatusTitle + ", paymentMethod=" + paymentMethod
				+ ", paymentMethodTitle=" + paymentMethodTitle + ", userId=" + userId + ", name=" + name + ", email="
				+ email + ", contact=" + contact + ", Address=" + address + "]";
	}

	
}
