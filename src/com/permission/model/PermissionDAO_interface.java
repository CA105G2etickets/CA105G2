package com.permission.model;

import java.util.List;

public interface PermissionDAO_interface {
	
	public void insert(PermissionVO permissionVO);
    public void update(PermissionVO permissionVO);
    public void delete(String permission_list_no);
    public PermissionVO findByPermissionListNo(String permission_list_no);
    public PermissionVO findByAdministratorNo(String administrator_no);
    public List<PermissionVO> getAll();

    public List<String> findPermissionByAdministratorNo(String administrator_no);
    
    public void insert2 (PermissionVO permissionVO , java.sql.Connection con);
    
}
