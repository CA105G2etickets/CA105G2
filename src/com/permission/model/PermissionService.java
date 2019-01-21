package com.permission.model;

import java.util.List;

public class PermissionService {

	private PermissionDAO_interface dao;

	public PermissionService() {
		dao = new PermissionDAO();
	}

	public PermissionVO addPermission(String permission_list_no, String administrator_no) {

		PermissionVO permissionVO = new PermissionVO();

		permissionVO.setPermission_list_no(permission_list_no);
		permissionVO.setAdministrator_no(administrator_no);
		dao.insert(permissionVO);

		return permissionVO;
	}

	public PermissionVO updatePermission(String permission_list_no, String administrator_no) {

		PermissionVO permissionVO = new PermissionVO();

		
		permissionVO.setPermission_list_no(permission_list_no);
		permissionVO.setAdministrator_no(administrator_no);
		dao.update(permissionVO);

		return permissionVO;
	}

	public void deleteNews(String permission_list_no) {
		dao.delete(permission_list_no);
	}

	public PermissionVO getOneByPermissionListNo(String permission_list_no) {
		return dao.findByPermissionListNo(permission_list_no);
	}
	
	public PermissionVO getOneByAdministratorNo(String administrator_no) {
		return dao.findByAdministratorNo(administrator_no);
	}

	public List<PermissionVO> getAll() {
		return dao.getAll();
	}

	public List<String> findPermissionByAdministratorNo(String administrator_no) {
		return dao.findPermissionByAdministratorNo(administrator_no);
	}
	
}
