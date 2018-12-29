package com.transaction_history.model;

import java.sql.Timestamp;

public class TransactionHistoryVO {
	
	private String transactionHistoryNo;
	private String memberNo;
	private Timestamp transactionDatetime;
	private String transactionCategory;
	private Integer expenditures;
	private Integer receipt;
	private Integer ewalletBalance;
	private String description;

	public TransactionHistoryVO() {
		// TODO Auto-generated constructor stub
	}

	public TransactionHistoryVO(String transactionHistoryNo, String memberNo, Timestamp transactionDatetime,
			String transactionCategory, Integer expenditures, Integer receipt, Integer ewalletBalance,
			String description) {
		super();
		this.transactionHistoryNo = transactionHistoryNo;
		this.memberNo = memberNo;
		this.transactionDatetime = transactionDatetime;
		this.transactionCategory = transactionCategory;
		this.expenditures = expenditures;
		this.receipt = receipt;
		this.ewalletBalance = ewalletBalance;
		this.description = description;
	}

	public String getTransactionHistoryNo() {
		return transactionHistoryNo;
	}

	public void setTransactionHistoryNo(String transactionHistoryNo) {
		this.transactionHistoryNo = transactionHistoryNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public Timestamp getTransactionDatetime() {
		return transactionDatetime;
	}

	public void setTransactionDatetime(Timestamp transactionDatetime) {
		this.transactionDatetime = transactionDatetime;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public Integer getExpenditures() {
		return expenditures;
	}

	public void setExpenditures(Integer expenditures) {
		this.expenditures = expenditures;
	}

	public Integer getReceipt() {
		return receipt;
	}

	public void setReceipt(Integer receipt) {
		this.receipt = receipt;
	}

	public Integer getEwalletBalance() {
		return ewalletBalance;
	}

	public void setEwalletBalance(Integer ewalletBalance) {
		this.ewalletBalance = ewalletBalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
