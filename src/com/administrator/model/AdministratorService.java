package com.administrator.model;

import java.sql.Timestamp;
import java.util.List;

public class AdministratorService {

	private AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}

	public AdministratorVO addAdministrator(String administrator_name, String administrator_account,
			String administrator_password, byte[] administrator_picture,
			String administrator_status) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdministrator_name(administrator_name);
		administratorVO.setAdministrator_account(administrator_account);
		administratorVO.setAdministrator_password(administrator_password);
//		administratorVO.setCreation_date(creation_date);
		administratorVO.setAdministrator_picture(administrator_picture);
		administratorVO.setAdministrator_status(administrator_status);
		dao.insert(administratorVO);

		return administratorVO;
	}

	public AdministratorVO updateAdministrator(String administrator_no, String administrator_name, String administrator_account,
			String administrator_password, byte[] administrator_picture,
			String administrator_status) {

		AdministratorVO administratorVO = new AdministratorVO();

		administratorVO.setAdministrator_no(administrator_no);
		administratorVO.setAdministrator_name(administrator_name);
		administratorVO.setAdministrator_account(administrator_account);
		administratorVO.setAdministrator_password(administrator_password);
//		administratorVO.setCreation_date(creation_date);
		administratorVO.setAdministrator_picture(administrator_picture);
		administratorVO.setAdministrator_status(administrator_status);
		dao.update(administratorVO);

		return administratorVO;
	}

	public void deleteAdministrator(String administrator_no) {
		dao.delete(administrator_no);
	}

	public AdministratorVO getOneAdministrator(String administrator_no) {
		return dao.findByPrimaryKey(administrator_no);
	}

	public List<AdministratorVO> getAll() {
		return dao.getAll();
	}
	
	public AdministratorVO findByAccount(String administrator_account) {
		return dao.findByAccount(administrator_account);
	}
	
}
