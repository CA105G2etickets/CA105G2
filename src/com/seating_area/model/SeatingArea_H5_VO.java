package com.seating_area.model;

import java.util.HashSet;
import java.util.Set;

import com.event.model.Event_H5_VO;
import com.ticket.model.TicketVO;
import com.ticket_type.model.TicketType_H5_VO;
import com.ticketorder.model.TicketOrderVO;

public class SeatingArea_H5_VO {
	
	private String ticarea_no;
//	private String eve_no;
	private Event_H5_VO eve_h5VO;
	
//	private String tictype_no;
	private TicketType_H5_VO tickettype_h5VO;
	
	private String ticarea_color;
	private String ticarea_name;
	private Integer tictotalnumber;
	private Integer ticbookednumber;
	
	private Set<TicketVO> tickets = new HashSet<TicketVO>();
	private Set<TicketOrderVO> ticketorders = new HashSet<TicketOrderVO>();
	
	public Set<TicketVO> getTickets() {
		return tickets;
	}
	public void setTickets(Set<TicketVO> tickets) {
		this.tickets = tickets;
	}
	public String getTicarea_no() {
		return ticarea_no;
	}
	public void setTicarea_no(String ticarea_no) {
		this.ticarea_no = ticarea_no;
	}
	public Event_H5_VO getEve_h5VO() {
		return eve_h5VO;
	}
	public void setEve_h5VO(Event_H5_VO eve_h5VO) {
		this.eve_h5VO = eve_h5VO;
	}
	public TicketType_H5_VO getTickettype_h5VO() {
		return tickettype_h5VO;
	}
	public void setTickettype_h5VO(TicketType_H5_VO tickettype_h5VO) {
		this.tickettype_h5VO = tickettype_h5VO;
	}
	public String getTicarea_color() {
		return ticarea_color;
	}
	public void setTicarea_color(String ticarea_color) {
		this.ticarea_color = ticarea_color;
	}
	public String getTicarea_name() {
		return ticarea_name;
	}
	public void setTicarea_name(String ticarea_name) {
		this.ticarea_name = ticarea_name;
	}
	public Integer getTictotalnumber() {
		return tictotalnumber;
	}
	public void setTictotalnumber(Integer tictotalnumber) {
		this.tictotalnumber = tictotalnumber;
	}
	public Integer getTicbookednumber() {
		return ticbookednumber;
	}
	public void setTicbookednumber(Integer ticbookednumber) {
		this.ticbookednumber = ticbookednumber;
	}
	public Set<TicketOrderVO> getTicketorders() {
		return ticketorders;
	}
	public void setTicketorders(Set<TicketOrderVO> ticketorders) {
		this.ticketorders = ticketorders;
	}
	
	
	
	
	
//	public String getEve_no() {
//		return eve_no;
//	}
//	public void setEve_no(String eve_no) {
//		this.eve_no = eve_no;
//	}
	
//	public String getTictype_no() {
//		return tictype_no;
//	}
//	public void setTictype_no(String tictype_no) {
//		this.tictype_no = tictype_no;
//	}

	
}
