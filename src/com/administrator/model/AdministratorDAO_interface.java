package com.administrator.model;

import java.util.*;

import com.permission.model.PermissionVO;

public interface AdministratorDAO_interface {
	
	public void insert(AdministratorVO administratorVO);
    public void update(AdministratorVO administratorVO);
    public void delete(String administrator_no);
    public AdministratorVO findByPrimaryKey(String administrator_no);
    public List<AdministratorVO> getAll();
    public AdministratorVO findByAccount(String administrator_account);
    
    public Set<PermissionVO> getPermissionsByAdministratorno(String administrator_no);
    
    public void insertWithPermission(AdministratorVO administratorVO , List<PermissionVO> list);
    
}
