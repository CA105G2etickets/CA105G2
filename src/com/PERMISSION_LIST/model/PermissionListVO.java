package com.PERMISSION_LIST.model;

public class PermissionListVO {
	
	private String permissionListNo;
	private String permission;

	public PermissionListVO() {
		// TODO Auto-generated constructor stub
	}

	public PermissionListVO(String permissionListNo, String permission) {
		super();
		this.permissionListNo = permissionListNo;
		this.permission = permission;
	}

	public String getPermissionListNo() {
		return permissionListNo;
	}

	public void setPermissionListNo(String permissionListNo) {
		this.permissionListNo = permissionListNo;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}
