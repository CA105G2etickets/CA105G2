package com.FORUM.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;

import oracle.sql.CLOB;

public class ForumVO implements Serializable {

	private String FORUM_NO;
	private String GROUP_NO;
	private String MEMBER_NO;
	private String FORUM_CONTENT;
	private Timestamp FORUM_TIME;

	public ForumVO() {
	}

	public ForumVO(String fORUM_NO, String gROUP_NUMBER, String mEMBER_NO, String fORUM_CONTENT, Timestamp fORUM_TIME) {
		super();
		this.FORUM_NO = fORUM_NO;
		this.GROUP_NO = gROUP_NUMBER;
		this.MEMBER_NO = mEMBER_NO;
		this.FORUM_CONTENT = fORUM_CONTENT;
		this.FORUM_TIME = fORUM_TIME;

	}

	public String getFORUM_NO() {
		return FORUM_NO;
	}

	public void setFORUM_NO(String fORUM_NO) {
		FORUM_NO = fORUM_NO;
	}

	public String getGROUP_NO() {
		return GROUP_NO;
	}

	public void setGROUP_NO(String gROUP_NUMBER) {
		GROUP_NO = gROUP_NUMBER;
	}

	public String getMEMBER_NO() {
		return MEMBER_NO;
	}

	public void setMEMBER_NO(String mEMBER_NO) {
		MEMBER_NO = mEMBER_NO;
	}

	public String getFORUM_CONTENT() {

		return FORUM_CONTENT;
	}

	public void setFORUM_CONTENT(String fORUM_CONTENT) {
		FORUM_CONTENT = fORUM_CONTENT;
	}

	public Timestamp getFORUM_TIME() {
		return FORUM_TIME;
	}

	public void setFORUM_TIME(Timestamp fORUM_TIME) {
		FORUM_TIME = fORUM_TIME;
	}

}
