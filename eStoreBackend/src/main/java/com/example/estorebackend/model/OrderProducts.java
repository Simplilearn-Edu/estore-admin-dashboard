package com.example.estorebackend.model;

import java.util.Date;
import java.util.List;

public class OrderProducts extends Orders{
	
	List<OrderItems> products;
	
	

	public OrderProducts(Integer orderId, Date orderDate, Integer orderStatus, Integer totalItems, Double itemsSubTotal,
			Double shipmentCharges, Double totalAmount, Integer paymentStatus, String paymentStatusTitle,
			Integer paymentMethod, String paymentMethodTitle, Integer userId, String name, String email, Long contact,
			String address, List<OrderItems> products) {
		super(orderId, orderDate, orderStatus, totalItems, itemsSubTotal, shipmentCharges, totalAmount, paymentStatus,
				paymentStatusTitle, paymentMethod, paymentMethodTitle, userId, name, email, contact, address);
		this.products = products;
	}

	public List<OrderItems> getProducts() {
		return products;
	}

	public void setProducts(List<OrderItems> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OrderProducts [products=" + products + ", orderId=" + orderId + ", orderDate=" + orderDate
				+ ", orderStatus=" + orderStatus + ", totalItems=" + totalItems + ", itemsSubTotal=" + itemsSubTotal
				+ ", shipmentCharges=" + shipmentCharges + ", totalAmount=" + totalAmount + ", paymentStatus="
				+ paymentStatus + ", paymentStatusTitle=" + paymentStatusTitle + ", paymentMethod=" + paymentMethod
				+ ", paymentMethodTitle=" + paymentMethodTitle + ", userId=" + userId + ", name=" + name + ", email="
				+ email + ", contact=" + contact + ", address=" + address + "]";
	}
	
	
}
