package com.NEWS_CLASSIFICATION.model;

public class NewsClassificationVO {
	
	private String newsClassificationNo;
	private String newsClassification;

	public NewsClassificationVO() {
		// TODO Auto-generated constructor stub
	}

	public NewsClassificationVO(String newsClassificationNo, String newsClassification) {
		super();
		this.newsClassificationNo = newsClassificationNo;
		this.newsClassification = newsClassification;
	}

	public String getNewsClassificationNo() {
		return newsClassificationNo;
	}

	public void setNewsClassificationNo(String newsClassificationNo) {
		this.newsClassificationNo = newsClassificationNo;
	}

	public String getNewsClassification() {
		return newsClassification;
	}

	public void setNewsClassification(String newsClassification) {
		this.newsClassification = newsClassification;
	}

}
