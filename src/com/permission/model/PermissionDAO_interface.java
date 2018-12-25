package com.permission.model;

import java.util.List;

public interface PermissionDAO_interface {
	
	public void insert(PermissionVO permission);
    public void update(PermissionVO permission);
    public void delete(String permissionListNo);
    public PermissionVO findByPrimaryKey(String permissionListNo);
    public List<PermissionVO> getAll();
    
}
