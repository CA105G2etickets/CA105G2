package com.ticketorder.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.seating_area.model.SeatingArea_H5_VO;
import com.ticket.model.TicketVO;

public class TicketOrderVO implements Serializable{
	
	private String ticket_order_no;
	private String member_no;
	
//	private String ticarea_no;
	private SeatingArea_H5_VO seatingarea_h5VO;
	
	private Integer total_price;
	private Integer total_amount;
	private Timestamp ticket_order_time;
	private String payment_method;
	private String ticket_order_status;
	private Set<TicketVO> tickets = new HashSet<TicketVO>();
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
	public SeatingArea_H5_VO getSeatingarea_h5VO() {
		return seatingarea_h5VO;
	}
	public void setSeatingarea_h5VO(SeatingArea_H5_VO seatingarea_h5VO) {
		this.seatingarea_h5VO = seatingarea_h5VO;
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
	public Set<TicketVO> getTickets() {
		return tickets;
	}
	public void setTickets(Set<TicketVO> tickets) {
		this.tickets = tickets;
	}
	
	
//	public String getTicarea_no() {
//		return ticarea_no;
//	}
//	public void setTicarea_no(String ticarea_no) {
//		this.ticarea_no = ticarea_no;
//	}
	
	
}
