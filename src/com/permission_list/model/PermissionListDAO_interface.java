package com.permission_list.model;

import java.util.List;

public interface PermissionListDAO_interface {
	
	public void insert(PermissionListVO permissionListVO);
    public void update(PermissionListVO permissionListVO);
    public void delete(String permission_list_no);
    public PermissionListVO findByPrimaryKey(String permission_list_no);
    public List<PermissionListVO> getAll();
    
}
