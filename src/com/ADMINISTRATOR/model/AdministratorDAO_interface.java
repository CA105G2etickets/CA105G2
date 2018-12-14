package com.ADMINISTRATOR.model;

import java.util.List;

public interface AdministratorDAO_interface {
	
	public void insert(AdministratorVO administrator);
    public void update(AdministratorVO administrator);
    public void delete(String administratorNo);
    public AdministratorVO findByPrimaryKey(String administratorNo);
    public List<AdministratorVO> getAll();

}
