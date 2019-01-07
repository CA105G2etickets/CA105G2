package com.android.administrator.model;


public class AdministratorService {

	private AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}
	
	public boolean isAdmin(String account, String password) {
		return dao.isAdmin(account, password);
	}
}
