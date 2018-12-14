package com.resaleorder.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class ResaleOrderVO implements Serializable{
	private String resale_ordno;
	private String ticket_no;
	private String member_seller_no;
	private String member_buyer_no;
	private Integer resale_ordprice;
	private String resale_ordstatus;
	private Timestamp resale_ord_createtime;
	private Timestamp resale_ord_completetime;
	private String payment_method;
	public String getResale_ordno() {
		return resale_ordno;
	}
	public void setResale_ordno(String resale_ordno) {
		this.resale_ordno = resale_ordno;
	}
	public String getTicket_no() {
		return ticket_no;
	}
	public void setTicket_no(String ticket_no) {
		this.ticket_no = ticket_no;
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
	
}
