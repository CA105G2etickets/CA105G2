package com.PERMISSION_LIST.model;

import java.util.List;

public interface PermissionListDAO_interface {
	
	public void insert(PermissionListVO permissionList);
    public void update(PermissionListVO permissionList);
    public void delete(String permissionListNo);
    public PermissionListVO findByPrimaryKey(String permissionListNo);
    public List<PermissionListVO> getAll();

}
