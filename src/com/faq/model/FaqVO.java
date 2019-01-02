package com.faq.model;

import java.io.Serializable;

public class FaqVO implements Serializable{
	
	private String faq_no;
	private String question;
	private String answer;
	private String faq_classification;

	public FaqVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FaqVO(String faq_no, String question, String answer, String faq_classification) {
		super();
		this.faq_no = faq_no;
		this.question = question;
		this.answer = answer;
		this.faq_classification = faq_classification;
	}

	public String getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(String faq_no) {
		this.faq_no = faq_no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFaq_classification() {
		return faq_classification;
	}

	public void setFaq_classification(String faq_classification) {
		this.faq_classification = faq_classification;
	}

	@Override
	public String toString() {
		return "FaqVO [faq_no=" + faq_no + ", question=" + question + ", answer=" + answer + ", faq_classification="
				+ faq_classification + "]";
	}

}
