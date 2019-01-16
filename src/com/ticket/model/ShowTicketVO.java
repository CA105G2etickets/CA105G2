package com.ticket.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.resaleorder.model.ResaleOrderVO;
import com.seating_area.model.SeatingAreaVO;
import com.seating_area.model.SeatingArea_H5_VO;
import com.ticketorder.model.TicketOrderVO;

public class ShowTicketVO implements Serializable{
    
	private String ticket_no;
	private String member_no;
	private String ticket_status;
	
	private String ticket_resale_status;
	private Integer ticket_resale_price;
	private String is_from_resale;
	private String ticket_order_no;
	
	private String ticarea_name;
	private String ticarea_color;
	
	private String tictype_name;
	private Integer tictype_price;
	
	private String eve_sessionname;
	private byte[] eve_seatmap;
	private Timestamp eve_startdate;
	private Timestamp eve_enddate;
	private Timestamp eve_offsaledate;
	
	private String evetit_name;
	private byte[] evetit_poster;
	
	private String venue_name;
	private String address;
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}
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
	public String getTicarea_name() {
		return ticarea_name;
	}
	public void setTicarea_name(String ticarea_name) {
		this.ticarea_name = ticarea_name;
	}
	public String getTicarea_color() {
		return ticarea_color;
	}
	public void setTicarea_color(String ticarea_color) {
		this.ticarea_color = ticarea_color;
	}
	public String getTictype_name() {
		return tictype_name;
	}
	public void setTictype_name(String tictype_name) {
		this.tictype_name = tictype_name;
	}
	public Integer getTictype_price() {
		return tictype_price;
	}
	public void setTictype_price(Integer tictype_price) {
		this.tictype_price = tictype_price;
	}
	public String getEve_sessionname() {
		return eve_sessionname;
	}
	public void setEve_sessionname(String eve_sessionname) {
		this.eve_sessionname = eve_sessionname;
	}
	public byte[] getEve_seatmap() {
		return eve_seatmap;
	}
	public void setEve_seatmap(byte[] eve_seatmap) {
		this.eve_seatmap = eve_seatmap;
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
	public Timestamp getEve_offsaledate() {
		return eve_offsaledate;
	}
	public void setEve_offsaledate(Timestamp eve_offsaledate) {
		this.eve_offsaledate = eve_offsaledate;
	}
	public String getEvetit_name() {
		return evetit_name;
	}
	public void setEvetit_name(String evetit_name) {
		this.evetit_name = evetit_name;
	}
	public byte[] getEvetit_poster() {
		return evetit_poster;
	}
	public void setEvetit_poster(byte[] evetit_poster) {
		this.evetit_poster = evetit_poster;
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
	public String getTicket_order_no() {
		return ticket_order_no;
	}
	public void setTicket_order_no(String ticket_order_no) {
		this.ticket_order_no = ticket_order_no;
	}
	
}
