package com.GROUP_MEMBER.model;

import java.sql.Timestamp;

public class Group_MemberVO {
	
	private String MEMBER_NO;
	private String GROUP_NO;
	private Timestamp JOIN_TIME;
	private Integer PRODUCT_QUANTITY;
	private String PAY_STATUS;
	private String GROUP_MEMBER_STATUS;
	private String LOG_OUT_REASON;
	private Integer ORDER_PHONE;
	private String PAY_METHODS;
	
	public Group_MemberVO(){}
	
	
	public Group_MemberVO(String mEMBER_NO, String gROUP_NO, Timestamp jOIN_TIME, Integer pRODUCT_QUANTITY,
			String pAY_STATUS, String gROUP_MEMBER_STATUS, String lOG_OUT_REASON, Integer oRDER_PHONE,
			String pAY_METHODS) {
		super();
		MEMBER_NO = mEMBER_NO;
		GROUP_NO = gROUP_NO;
		JOIN_TIME = jOIN_TIME;
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
		PAY_STATUS = pAY_STATUS;
		GROUP_MEMBER_STATUS = gROUP_MEMBER_STATUS;
		LOG_OUT_REASON = lOG_OUT_REASON;
		ORDER_PHONE = oRDER_PHONE;
		PAY_METHODS = pAY_METHODS;
	}
	public String getMEMBER_NO() {
		return MEMBER_NO;
	}
	public void setMEMBER_NO(String mEMBER_NO) {
		MEMBER_NO = mEMBER_NO;
	}
	public String getGROUP_NO() {
		return GROUP_NO;
	}
	public void setGROUP_NO(String gROUP_NO) {
		GROUP_NO = gROUP_NO;
	}
	public Timestamp getJOIN_TIME() {
		return JOIN_TIME;
	}
	public void setJOIN_TIME(Timestamp jOIN_TIME) {
		JOIN_TIME = jOIN_TIME;
	}
	public Integer getPRODUCT_QUANTITY() {
		return PRODUCT_QUANTITY;
	}
	public void setPRODUCT_QUANTITY(Integer pRODUCT_QUANTITY) {
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
	}
	public String getPAY_STATUS() {
		return PAY_STATUS;
	}
	public void setPAY_STATUS(String pAY_STATUS) {
		PAY_STATUS = pAY_STATUS;
	}
	public String getGROUP_MEMBER_STATUS() {
		return GROUP_MEMBER_STATUS;
	}
	public void setGROUP_MEMBER_STATUS(String gROUP_MEMBER_STATUS) {
		GROUP_MEMBER_STATUS = gROUP_MEMBER_STATUS;
	}
	public String getLOG_OUT_REASON() {
		return LOG_OUT_REASON;
	}
	public void setLOG_OUT_REASON(String lOG_OUT_REASON) {
		LOG_OUT_REASON = lOG_OUT_REASON;
	}
	public Integer getORDER_PHONE() {
		return ORDER_PHONE;
	}
	public void setORDER_PHONE(Integer oRDER_PHONE) {
		ORDER_PHONE = oRDER_PHONE;
	}
	public String getPAY_METHODS() {
		return PAY_METHODS;
	}
	public void setPAY_METHODS(String pAY_METHODS) {
		PAY_METHODS = pAY_METHODS;
	}
	

}
