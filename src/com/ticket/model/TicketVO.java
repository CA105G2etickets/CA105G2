package com.ticket.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticketorder.model.TicketOrderVO;

public class TicketVO implements Serializable{
    //test Commit/pull potential conflict 2
	
	private String ticket_no;
	
//	private String ticarea_no;
	private SeatingArea_H5_VO seatingarea_h5VO;
	
	//	private String ticket_order_no;
	private TicketOrderVO ticketorderVO;
	
	
	private String member_no;
	private String ticket_status;
	private Timestamp ticket_create_time;
	private String ticket_resale_status;
	private Integer ticket_resale_price;
	private String is_from_resale;
	
	private Set<ResaleOrderVO> resaleords = new HashSet<ResaleOrderVO>();
	
//	public SeatingAreaVO getSeatingareaVO() {
//		return seatingareaVO;
//	}
//	public void setSeatingareaVO(SeatingAreaVO seatingareaVO) {
//		this.seatingareaVO = seatingareaVO;
//	}
	
	public TicketOrderVO getTicketorderVO() {
		return ticketorderVO;
	}
	public void setTicketorderVO(TicketOrderVO ticketorderVO) {
		this.ticketorderVO = ticketorderVO;
	}
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}
//	public String getTicarea_no() {
//		return ticarea_no;
//	}
//	public void setTicarea_no(String ticarea_no) {
//		this.ticarea_no = ticarea_no;
//	}
//	public String getTicket_order_no() {
//		return ticket_order_no;
//	}
//	public void setTicket_order_no(String ticket_order_no) {
//		this.ticket_order_no = ticket_order_no;
//	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getTicket_status() {
		return ticket_status;
	}
	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}
	public Timestamp getTicket_create_time() {
		return ticket_create_time;
	}
	public void setTicket_create_time(Timestamp ticket_create_time) {
		this.ticket_create_time = ticket_create_time;
	}
	public String getTicket_resale_status() {
		return ticket_resale_status;
	}
	public void setTicket_resale_status(String ticket_resale_status) {
		this.ticket_resale_status = ticket_resale_status;
	}
	public Integer getTicket_resale_price() {
		return ticket_resale_price;
	}
	public void setTicket_resale_price(Integer ticket_resale_price) {
		this.ticket_resale_price = ticket_resale_price;
	}
	public String getIs_from_resale() {
		return is_from_resale;
	}
	public void setIs_from_resale(String is_from_resale) {
		this.is_from_resale = is_from_resale;
	}
	public Set<ResaleOrderVO> getResaleords() {
		return resaleords;
	}
	public void setResaleords(Set<ResaleOrderVO> resaleords) {
		this.resaleords = resaleords;
	}
	public SeatingArea_H5_VO getSeatingarea_h5VO() {
		return seatingarea_h5VO;
	}
	public void setSeatingarea_h5VO(SeatingArea_H5_VO seatingarea_h5VO) {
		this.seatingarea_h5VO = seatingarea_h5VO;
	}
	
	
}
