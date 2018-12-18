package com.ORDER_HISTORY.model;

import java.sql.Timestamp;

public class OrderHistoryVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 3974699486176517592L;
	
	private String orderNo;
	private String memberNo;
	private Double orderPrice;
	private String payMethods;
	private String shippingMethods;
	private Timestamp orderDate;
	private Timestamp orderEtd;
	private Timestamp pickupDate;
	private String receiverAdd;
	private String receiverName;
	private String receiverTel;
	private String orderStatus;
	
	public OrderHistoryVO() {
		
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getPayMethods() {
		return payMethods;
	}
	public void setPayMethods(String payMethods) {
		this.payMethods = payMethods;
	}
	public String getShippingMethods() {
		return shippingMethods;
	}
	public void setShippingMethods(String shippingMethods) {
		this.shippingMethods = shippingMethods;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Timestamp getOrderEtd() {
		return orderEtd;
	}
	public void setOrderEtd(Timestamp orderEtd) {
		this.orderEtd = orderEtd;
	}
	public Timestamp getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(Timestamp pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getReceiverAdd() {
		return receiverAdd;
	}
	public void setReceiverAdd(String receiverAdd) {
		this.receiverAdd = receiverAdd;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverTel() {
		return receiverTel;
	}
	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
