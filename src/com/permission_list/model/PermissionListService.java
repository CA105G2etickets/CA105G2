package com.permission_list.model;

import java.util.List;

public class PermissionListService {

	private PermissionListDAO_interface dao;

	public PermissionListService() {
		dao = new PermissionListDAO();
	}
			
	public PermissionListVO addPermissionList(String permission) {

		PermissionListVO permissionListVO = new PermissionListVO();

		permissionListVO.setPermission(permission);
		dao.insert(permissionListVO);

		return permissionListVO;
	}

	public PermissionListVO updatePermissionList(String permission_list_no, String permission) {

		PermissionListVO permissionListVO = new PermissionListVO();

		
		permissionListVO.setPermission_list_no(permission_list_no);
		permissionListVO.setPermission(permission);
		dao.update(permissionListVO);

		return permissionListVO;
	}

	public void deletePermissionList(String permission_list_no) {
		dao.delete(permission_list_no);
	}

	public PermissionListVO getOnePermissionList(String permission_list_no) {
		return dao.findByPrimaryKey(permission_list_no);
	}

	public List<PermissionListVO> getAll() {
		return dao.getAll();
	}
	
}
