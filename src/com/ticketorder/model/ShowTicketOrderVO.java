package com.ticketorder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticketorder.model.TicketOrderVO;

public class ShowTicketOrderVO implements Serializable{
    
	private String ticket_order_no;
	private String member_no;
	private Integer total_price;
	private Integer total_amount;
	private Timestamp ticket_order_time;
	private String payment_method;
	private String ticket_order_status;
	
	private String eve_sessionname;
	private Timestamp eve_startdate;
	private Timestamp eve_enddate;
	
	private String evetit_name;
	
	private String venue_name;
	private String address;
	public String getTicket_order_no() {
		return ticket_order_no;
	}
	public void setTicket_order_no(String ticket_order_no) {
		this.ticket_order_no = ticket_order_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public Integer getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	public Integer getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	public Timestamp getTicket_order_time() {
		return ticket_order_time;
	}
	public void setTicket_order_time(Timestamp ticket_order_time) {
		this.ticket_order_time = ticket_order_time;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getTicket_order_status() {
		return ticket_order_status;
	}
	public void setTicket_order_status(String ticket_order_status) {
		this.ticket_order_status = ticket_order_status;
	}
	public String getEve_sessionname() {
		return eve_sessionname;
	}
	public void setEve_sessionname(String eve_sessionname) {
		this.eve_sessionname = eve_sessionname;
	}
	public Timestamp getEve_startdate() {
		return eve_startdate;
	}
	public void setEve_startdate(Timestamp eve_startdate) {
		this.eve_startdate = eve_startdate;
	}
	public Timestamp getEve_enddate() {
		return eve_enddate;
	}
	public void setEve_enddate(Timestamp eve_enddate) {
		this.eve_enddate = eve_enddate;
	}
	public String getEvetit_name() {
		return evetit_name;
	}
	public void setEvetit_name(String evetit_name) {
		this.evetit_name = evetit_name;
	}
	public String getVenue_name() {
		return venue_name;
	}
	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
