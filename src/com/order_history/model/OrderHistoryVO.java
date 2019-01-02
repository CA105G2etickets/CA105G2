package com.order_history.model;

import java.sql.Timestamp;

public class OrderHistoryVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 3974699486176517592L;
	
	private String order_no;
	private String member_no;
	private Double order_price;
	private String pay_methods;
	private String shipping_methods;
	private Timestamp order_date;
	private Timestamp order_etd;
	private Timestamp pickup_date;
	private String receiver_add;
	private String receiver_name;
	private String receiver_tel;
	private String order_status;
	
	public OrderHistoryVO() {
		
	}
	public OrderHistoryVO(String order_no, String member_no, Double order_price, String pay_methods, 
			String shipping_methods, Timestamp order_date, Timestamp order_etd, Timestamp pickup_date,
			String receiver_add, String receiver_name, String receiver_tel, String order_status) {
		this.order_no = order_no;
		this.member_no = member_no;
		this.order_price = order_price;
		this.pay_methods = pay_methods;
		this.shipping_methods = shipping_methods;
		this.order_date = order_date;
		this.order_etd = order_etd;
		this.pickup_date = pickup_date;
		this.receiver_add = receiver_add;
		this.receiver_name = receiver_name;
		this.receiver_tel = receiver_tel;
		this.order_status = order_status;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public Double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(Double order_price) {
		this.order_price = order_price;
	}
	public String getPay_methods() {
		return pay_methods;
	}
	public void setPay_methods(String pay_methods) {
		this.pay_methods = pay_methods;
	}
	public String getShipping_methods() {
		return shipping_methods;
	}
	public void setShipping_methods(String shipping_methods) {
		this.shipping_methods = shipping_methods;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public Timestamp getOrder_etd() {
		return order_etd;
	}
	public void setOrder_etd(Timestamp order_etd) {
		this.order_etd = order_etd;
	}
	public Timestamp getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(Timestamp pickup_date) {
		this.pickup_date = pickup_date;
	}
	public String getReceiver_add() {
		return receiver_add;
	}
	public void setReceiver_add(String receiver_add) {
		this.receiver_add = receiver_add;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_tel() {
		return receiver_tel;
	}
	public void setReceiver_tel(String receiver_tel) {
		this.receiver_tel = receiver_tel;
	}
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
			
}
