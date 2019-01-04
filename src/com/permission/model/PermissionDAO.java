package com.permission.model;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


public class PermissionDAO implements PermissionDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO PERMISSION (PERMISSION_LIST_NO,ADMINISTRATOR_NO) VALUES ( ?, ?)";
	private static final String UPDATE = 
			"UPDATE PERMISSION SET PERMISSION_LIST_NO = ? WHERE ADMINISTRATOR_NO = ?";
	private static final String DELETE = 
			"DELETE FROM PERMISSION WHERE PERMISSION_LIST_NO = ?";
	private static final String FIND_BY_PERMISSION_LIST_NO = 
			"SELECT * FROM PERMISSION WHERE PERMISSION_LIST_NO = ?";
	private static final String FIND_BY_ADMINISTRATOR_NO = 
			"SELECT * FROM PERMISSION WHERE ADMINISTRATOR_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM PERMISSION ORDER BY ADMINISTRATOR_NO ,PERMISSION_LIST_NO";

	@Override
	public void insert(PermissionVO permissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, permissionVO.getPermission_list_no());
			pstmt.setString(2, permissionVO.getAdministrator_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(PermissionVO permissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, permissionVO.getPermission_list_no());
			pstmt.setString(2, permissionVO.getAdministrator_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String permission_list_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, permission_list_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public PermissionVO findByPermissionListNo(String permission_list_no) {

		PermissionVO permissionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PERMISSION_LIST_NO);

			pstmt.setString(1, permission_list_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return permissionVO;
	}
	
	@Override
	public PermissionVO findByAdministratorNo(String administrator_no) {

		PermissionVO permissionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_ADMINISTRATOR_NO);

			pstmt.setString(1, administrator_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return permissionVO;
	}

	@Override
	public List<PermissionVO> getAll() {
		
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				list.add(permissionVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}