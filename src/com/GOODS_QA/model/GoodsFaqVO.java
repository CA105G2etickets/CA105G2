package com.GOODS_QA.model;

import java.sql.Timestamp;



public class GoodsFaqVO implements java.io.Serializable{

	private static final long serialVersionUID = 1449708921454718391L;
	private String gfaq_no;
	private String goods_no;
	private String member_no;
	private String administrator;
	private String questions_content;
	private String answer_content;
	private Timestamp qustions_date;
	private Timestamp answer_date;
	
	public GoodsFaqVO() {
		super();
	}
	
	public GoodsFaqVO(String gfaq_no, String goods_no, String member_no,	String administrator,String questions_content
,String answer_content,Timestamp qustions_date,Timestamp answer_date) {
		super();
	
	this.gfaq_no = gfaq_no; 
	this.goods_no = goods_no;
	this.member_no = member_no;
	this.administrator = administrator;
	this.questions_content = questions_content;
	this.answer_content = answer_content;
	this.qustions_date = qustions_date;
	this.answer_date = answer_date;
	}

	public String getGfaq_no() {
		return gfaq_no;
	}

	public void setGfaq_no(String gfaq_no) {
		this.gfaq_no = gfaq_no;
	}

	public String getGoods_no() {
		return goods_no;
	}

	public void setGoods_no(String goods_no) {
		this.goods_no = goods_no;
	}

	public String getMember_no() {
		return member_no;
	}

	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public String getQuestions_content() {
		return questions_content;
	}

	public void setQuestions_content(String questions_content) {
		this.questions_content = questions_content;
	}

	public String getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}

	public Timestamp getQustions_date() {
		return qustions_date;
	}

	public void setQustions_date(Timestamp qustions_date) {
		this.qustions_date = qustions_date;
	}

	public Timestamp getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(Timestamp answer_date) {
		this.answer_date = answer_date;
	}
	
}
