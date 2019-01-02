package com.android.news.model;

import java.io.Serializable;
import java.sql.Date;

public class NewsVO implements Serializable {
	
	private String news_no;
	private String news_classification;
	private String news_title;
	private String news_content;
	private Date announce_date;
	private String administrator_no;

	public NewsVO() {
		// TODO Auto-generated constructor stub
	}

	public NewsVO(String news_no, String news_classification, String news_title, String news_content,
			Date announce_date, String administrator_no) {
		super();
		this.news_no = news_no;
		this.news_classification = news_classification;
		this.news_title = news_title;
		this.news_content = news_content;
		this.announce_date = announce_date;
		this.administrator_no = administrator_no;
	}

	public String getNews_no() {
		return news_no;
	}

	public void setNews_no(String news_no) {
		this.news_no = news_no;
	}

	public String getNews_classification() {
		return news_classification;
	}

	public void setNews_classification(String news_classification) {
		this.news_classification = news_classification;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public Date getAnnounce_date() {
		return announce_date;
	}

	public void setAnnounce_date(Date announce_date) {
		this.announce_date = announce_date;
	}

	public String getAdministrator_no() {
		return administrator_no;
	}

	public void setAdministrator_no(String administrator_no) {
		this.administrator_no = administrator_no;
	}

//	@Override
//	public String toString() {
//		return "NewsVO [news_no=" + news_no + ", news_classification_no=" + news_classification + ", news_title="
//				+ news_title + ", news_content=" + news_content + ", announce_date=" + announce_date
//				+ ", administrator_no=" + administrator_no + "]";
//	}

}