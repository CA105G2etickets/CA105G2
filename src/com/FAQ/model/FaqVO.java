package com.FAQ.model;

public class FaqVO {
	
	private String faqNo;
	private String question;
	private String answer;
	private String faqClassification;

	public FaqVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FaqVO(String faqNo, String question, String answer, String faqClassification) {
		super();
		this.faqNo = faqNo;
		this.question = question;
		this.answer = answer;
		this.faqClassification = faqClassification;
	}

	public String getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
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

	public String getFaqClassification() {
		return faqClassification;
	}

	public void setFaqClassification(String faqClassification) {
		this.faqClassification = faqClassification;
	}

	@Override
	public String toString() {
		return "FaqVO [faqNo=" + faqNo + ", question=" + question + ", answer=" + answer + ", faqClassification="
				+ faqClassification + "]";
	}

}
