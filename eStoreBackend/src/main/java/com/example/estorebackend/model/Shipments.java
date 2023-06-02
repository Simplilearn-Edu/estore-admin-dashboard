package com.example.estorebackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shipments {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer shipmentId;
	
	Integer orderId;
	Integer shipmentStatus;
	String shipmentTitle;
	Date shipmentDate;
	String shipmentMethod;
	String shipmentCompany;
	Date expectedDeliveryDate;
	
	public Shipments() {
		
	}
	
	public Shipments(Integer shipmentId, Integer shipmentStatus, String shipmentTitle, Date shipmentDate,
			String shipmentMethod, String shipmentCompany) {
		this.shipmentId = shipmentId;
		this.shipmentStatus = shipmentStatus;
		this.shipmentTitle = shipmentTitle;
		this.shipmentDate = shipmentDate;
		this.shipmentMethod = shipmentMethod;
		this.shipmentCompany = shipmentCompany;
	}

	public Shipments(Integer shipmentId, Integer orderId, Integer shipmentStatus, String shipmentTitle,
			Date shipmentDate, String shipmentMethod, String shipmentCompany, Date expectedDeliveryDate) {
		this.shipmentId = shipmentId;
		this.orderId = orderId;
		this.shipmentStatus = shipmentStatus;
		this.shipmentTitle = shipmentTitle;
		this.shipmentDate = shipmentDate;
		this.shipmentMethod = shipmentMethod;
		this.shipmentCompany = shipmentCompany;
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public Integer getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Integer shipmentId) {
		this.shipmentId = shipmentId;
	}

	public Integer getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(Integer shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getShipmentTitle() {
		return shipmentTitle;
	}

	public void setShipmentTitle(String shipmentTitle) {
		this.shipmentTitle = shipmentTitle;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getShipmentMethod() {
		return shipmentMethod;
	}

	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}

	public String getShipmentCompany() {
		return shipmentCompany;
	}

	public void setShipmentCompany(String shipmentCompany) {
		this.shipmentCompany = shipmentCompany;
	}
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	@Override
	public String toString() {
		return "Shipments [shipmentId=" + shipmentId + ", orderId=" + orderId + ", shipmentStatus=" + shipmentStatus
				+ ", shipmentTitle=" + shipmentTitle + ", shipmentDate=" + shipmentDate + ", shipmentMethod="
				+ shipmentMethod + ", shipmentCompany=" + shipmentCompany + ", expectedDeliveryDate="
				+ expectedDeliveryDate + "]";
	}
	
}
