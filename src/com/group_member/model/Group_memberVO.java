package com.group_member.model;

import java.sql.Timestamp;

public class Group_memberVO implements java.io.Serializable {
	
	private String member_no;     //
	private String group_no;      //
	private Timestamp join_time;  //
	private Integer  product_quantity;
	private String  pay_status;   //
	private String group_member_status; 
	private String log_out_reason;  
	private String order_phone;
	private String pay_method;
	
	public Group_memberVO(){}
	
	public Group_memberVO(String member_no, String group_no, Timestamp join_time, Integer product_quantity,
			String pay_status, String group_member_status, String log_out_reason, String order_phone,
			String pay_method) {
		super();
		this.member_no = member_no;
		this.group_no = group_no;
		this.join_time = join_time;
		this.product_quantity = product_quantity;
		this.pay_status = pay_status;
		this.group_member_status = group_member_status;
		this.log_out_reason = log_out_reason;
		this.order_phone = order_phone;
		this.pay_method = pay_method;
	}







	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getGroup_no() {
		return group_no;
	}

	public void setGroup_no(String group_no) {
		this.group_no = group_no;
	}

	public Timestamp getJoin_time() {
		return join_time;
	}

	public void setJoin_time(Timestamp join_time) {
		this.join_time = join_time;
	}

	public Integer getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getGroup_member_status() {
		return group_member_status;
	}

	public void setGroup_member_status(String group_member_status) {
		this.group_member_status = group_member_status;
	}

	public String getLog_out_reason() {
		return log_out_reason;
	}

	public void setLog_out_reason(String log_out_reason) {
		this.log_out_reason = log_out_reason;
	}

	public String getOrder_phone() {
		return order_phone;
	}

	public void setOrder_phone(String order_phone) {
		this.order_phone = order_phone;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	
}
