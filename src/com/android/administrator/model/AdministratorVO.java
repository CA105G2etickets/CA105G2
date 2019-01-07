package com.android.administrator.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class AdministratorVO implements Serializable{
	
	private String administrator_no;
	private String administrator_name;
	private String administrator_account;
	private String administrator_password;
	private Timestamp creation_date;
	private byte[] administrator_picture;
	private String administrator_status;

	public AdministratorVO() {
		// TODO Auto-generated constructor stub
	}

	public AdministratorVO(String administrator_no, String administrator_name, String administrator_account,
			String administrator_password, Timestamp creation_date, byte[] administrator_picture,
			String administrator_status) {
		super();
		this.administrator_no = administrator_no;
		this.administrator_name = administrator_name;
		this.administrator_account = administrator_account;
		this.administrator_password = administrator_password;
		this.creation_date = creation_date;
		this.administrator_picture = administrator_picture;
		this.administrator_status = administrator_status;
	}

	public String getAdministrator_no() {
		return administrator_no;
	}

	public void setAdministrator_no(String administrator_no) {
		this.administrator_no = administrator_no;
	}

	public String getAdministrator_name() {
		return administrator_name;
	}

	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}

	public String getAdministrator_account() {
		return administrator_account;
	}

	public void setAdministrator_account(String administrator_account) {
		this.administrator_account = administrator_account;
	}

	public String getAdministrator_password() {
		return administrator_password;
	}

	public void setAdministrator_password(String administrator_password) {
		this.administrator_password = administrator_password;
	}

	public Timestamp getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Timestamp creation_date) {
		this.creation_date = creation_date;
	}

	public byte[] getAdministrator_picture() {
		return administrator_picture;
	}

	public void setAdministrator_picture(byte[] administrator_picture) {
		this.administrator_picture = administrator_picture;
	}

	public String getAdministrator_status() {
		return administrator_status;
	}

	public void setAdministrator_status(String administrator_status) {
		this.administrator_status = administrator_status;
	}

	@Override
	public String toString() {
		return "AdministratorVO [administrator_no=" + administrator_no + ", administrator_name=" + administrator_name
				+ ", administrator_account=" + administrator_account + ", administrator_password="
				+ administrator_password + ", creation_date=" + creation_date + ", administrator_picture="
				+ Arrays.toString(administrator_picture) + ", administrator_status=" + administrator_status + "]";
	}

}
