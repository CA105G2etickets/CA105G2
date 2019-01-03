package com.administrator.model;

import java.util.List;

public interface AdministratorDAO_interface {
	
	public void insert(AdministratorVO administratorVO);
    public void update(AdministratorVO administratorVO);
    public void delete(String administrator_no);
    public AdministratorVO findByPrimaryKey(String administrator_no);
    public List<AdministratorVO> getAll();
    
}
