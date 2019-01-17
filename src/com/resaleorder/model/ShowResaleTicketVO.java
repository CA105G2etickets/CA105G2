package com.resaleorder.model;
import java.io.Serializable;
import java.sql.Timestamp;

import com.ticket.model.TicketVO;

public class ShowResaleTicketVO implements Serializable{
	
	//for combine use
	private String ticket_no;
	
	private String resale_ordno;
	private String member_seller_no;
	private String member_buyer_no;
	private Integer resale_ordprice;
	private String resale_ordstatus;
	private Timestamp resale_ord_createtime;
	private Timestamp resale_ord_completetime; //there's no use, prevent 500 so disable it.
												//however, it use on paymentDone.jsp...wtf
	private String payment_method;
	
	private String ticarea_no;
	private String ticket_status;
	private String ticket_resale_status;
	private Integer ticket_resale_price;
	private String is_from_resale;
	
	private String eve_no;
	private String tictype_no;
	private String ticarea_name;
	private String ticarea_color;
	
	private String tictype_name;
	private Integer tictype_price;
	
	private String evetit_no;
	private String venue_no;
	private String eve_sessionname;
	private Timestamp eve_startdate;
	
	private String venue_name;
	private String address;
	
	private String evetit_name;

	public String getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getResale_ordno() {
		return resale_ordno;
	}

	public void setResale_ordno(String resale_ordno) {
		this.resale_ordno = resale_ordno;
	}

	public String getMember_seller_no() {
		return member_seller_no;
	}

	public void setMember_seller_no(String member_seller_no) {
		this.member_seller_no = member_seller_no;
	}

	public String getMember_buyer_no() {
		return member_buyer_no;
	}

	public void setMember_buyer_no(String member_buyer_no) {
		this.member_buyer_no = member_buyer_no;
	}

	public Integer getResale_ordprice() {
		return resale_ordprice;
	}

	public void setResale_ordprice(Integer resale_ordprice) {
		this.resale_ordprice = resale_ordprice;
	}

	public String getResale_ordstatus() {
		return resale_ordstatus;
	}

	public void setResale_ordstatus(String resale_ordstatus) {
		this.resale_ordstatus = resale_ordstatus;
	}

	public Timestamp getResale_ord_createtime() {
		return resale_ord_createtime;
	}

	public void setResale_ord_createtime(Timestamp resale_ord_createtime) {
		this.resale_ord_createtime = resale_ord_createtime;
	}

	public Timestamp getResale_ord_completetime() {
		return resale_ord_completetime;
	}

	public void setResale_ord_completetime(Timestamp resale_ord_completetime) {
		this.resale_ord_completetime = resale_ord_completetime;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getTicarea_no() {
		return ticarea_no;
	}

	public void setTicarea_no(String ticarea_no) {
		this.ticarea_no = ticarea_no;
	}

	public String getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
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

	public String getEve_no() {
		return eve_no;
	}

	public void setEve_no(String eve_no) {
		this.eve_no = eve_no;
	}

	public String getTictype_no() {
		return tictype_no;
	}

	public void setTictype_no(String tictype_no) {
		this.tictype_no = tictype_no;
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

	public String getEvetit_no() {
		return evetit_no;
	}

	public void setEvetit_no(String evetit_no) {
		this.evetit_no = evetit_no;
	}

	public String getVenue_no() {
		return venue_no;
	}

	public void setVenue_no(String venue_no) {
		this.venue_no = venue_no;
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

	public String getEvetit_name() {
		return evetit_name;
	}

	public void setEvetit_name(String evetit_name) {
		this.evetit_name = evetit_name;
	}

	
	
	//先不做 賣家賣出此票的日期時間
	
	
	
	
}
