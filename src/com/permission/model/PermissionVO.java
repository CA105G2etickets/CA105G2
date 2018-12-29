package com.permission.model;

public class PermissionVO {
	
	private String permissionListNo;
	private String administratorNo;

	public PermissionVO() {
		// TODO Auto-generated constructor stub
	}

	public PermissionVO(String permissionListNo, String administratorNo) {
		super();
		this.permissionListNo = permissionListNo;
		this.administratorNo = administratorNo;
	}

	public String getPermissionListNo() {
		return permissionListNo;
	}

	public void setPermissionListNo(String permissionListNo) {
		this.permissionListNo = permissionListNo;
	}

	public String getAdministratorNo() {
		return administratorNo;
	}

	public void setAdministratorNo(String administratorNo) {
		this.administratorNo = administratorNo;
	}

}
