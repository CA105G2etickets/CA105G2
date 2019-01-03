package com.permission.model;

import java.io.Serializable;

public class PermissionVO implements Serializable{
	
	private String permission_list_no;
	private String administrator_no;

	public PermissionVO() {
		// TODO Auto-generated constructor stub
	}

	public PermissionVO(String permission_list_no, String administrator_no) {
		super();
		this.permission_list_no = permission_list_no;
		this.administrator_no = administrator_no;
	}

	public String getPermission_list_no() {
		return permission_list_no;
	}

	public void setPermission_list_no(String permission_list_no) {
		this.permission_list_no = permission_list_no;
	}

	public String getAdministrator_no() {
		return administrator_no;
	}

	public void setAdministrator_no(String administrator_no) {
		this.administrator_no = administrator_no;
	}

}
