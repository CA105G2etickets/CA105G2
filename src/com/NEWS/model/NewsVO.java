package com.NEWS.model;

import java.sql.Date;

public class NewsVO {
	
	private String newsNo;
	private String newsClassificationNo;
	private String newsTitle;
	private String newsContent;
	private Date announceDate;
	private String administratorNo;

	public NewsVO() {
		// TODO Auto-generated constructor stub
	}

	public NewsVO(String newsNo, String newsClassificationNo, String newsTitle, String newsContent, Date announceDate,
			String administratorNo) {
		super();
		this.newsNo = newsNo;
		this.newsClassificationNo = newsClassificationNo;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.announceDate = announceDate;
		this.administratorNo = administratorNo;
	}

	public String getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(String newsNo) {
		this.newsNo = newsNo;
	}

	public String getNewsClassificationNo() {
		return newsClassificationNo;
	}

	public void setNewsClassificationNo(String newsClassificationNo) {
		this.newsClassificationNo = newsClassificationNo;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getAnnounceDate() {
		return announceDate;
	}

	public void setAnnounceDate(Date announceDate) {
		this.announceDate = announceDate;
	}

	public String getAdministratorNo() {
		return administratorNo;
	}

	public void setAdministratorNo(String administratorNo) {
		this.administratorNo = administratorNo;
	}

	@Override
	public String toString() {
		return "NewsVO [newsNo=" + newsNo + ", newsClassificationNo=" + newsClassificationNo + ", newsTitle="
				+ newsTitle + ", newsContent=" + newsContent + ", announceDate=" + announceDate + ", administratorNo="
				+ administratorNo + "]";
	}

}
