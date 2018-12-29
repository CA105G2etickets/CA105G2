package com.administrator.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class AdministratorVO {
	
	private String administratorNo;
	private String administratorName;
	private String administratorAccount;
	private String administratorPassword;
	private Timestamp creationDate;
	private byte[] administratorPicture;
	private String administratorStatus;

	public AdministratorVO() {
		// TODO Auto-generated constructor stub
	}

	public AdministratorVO(String administratorNo, String administratorName, String administratorAccount,
			String administratorPassword, Timestamp creationDate, byte[] administratorPicture,
			String administratorStatus) {
		super();
		this.administratorNo = administratorNo;
		this.administratorName = administratorName;
		this.administratorAccount = administratorAccount;
		this.administratorPassword = administratorPassword;
		this.creationDate = creationDate;
		this.administratorPicture = administratorPicture;
		this.administratorStatus = administratorStatus;
	}

	public String getAdministratorNo() {
		return administratorNo;
	}

	public void setAdministratorNo(String administratorNo) {
		this.administratorNo = administratorNo;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}

	public String getAdministratorAccount() {
		return administratorAccount;
	}

	public void setAdministratorAccount(String administratorAccount) {
		this.administratorAccount = administratorAccount;
	}

	public String getAdministratorPassword() {
		return administratorPassword;
	}

	public void setAdministratorPassword(String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getAdministratorPicture() {
		return administratorPicture;
	}

	public void setAdministratorPicture(byte[] administratorPicture) {
		this.administratorPicture = administratorPicture;
	}

	public String getAdministratorStatus() {
		return administratorStatus;
	}

	public void setAdministratorStatus(String administratorStatus) {
		this.administratorStatus = administratorStatus;
	}

	@Override
	public String toString() {
		return "AdministratorVO [administratorNo=" + administratorNo + ", administratorName=" + administratorName
				+ ", administratorAccount=" + administratorAccount + ", administratorPassword=" + administratorPassword
				+ ", creationDate=" + creationDate + ", administratorPicture=" + Arrays.toString(administratorPicture)
				+ ", administratorStatus=" + administratorStatus + "]";
	}

}
