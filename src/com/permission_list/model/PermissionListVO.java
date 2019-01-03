package com.permission_list.model;

import java.io.Serializable;

public class PermissionListVO implements Serializable{
	
	private String permission_list_no;
	private String permission;

	public PermissionListVO() {
		// TODO Auto-generated constructor stub
	}

	public PermissionListVO(String permission_list_no, String permission) {
		super();
		this.permission_list_no = permission_list_no;
		this.permission = permission;
	}

	public String getPermission_list_no() {
		return permission_list_no;
	}

	public void setPermission_list_no(String permission_list_no) {
		this.permission_list_no = permission_list_no;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
