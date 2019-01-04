package com.permission.model;

import java.sql.*;
import java.util.*;

public class PermissionJDBCDAO implements PermissionDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
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
			"SELECT * FROM PERMISSION ORDER BY ADMINISTRATOR_NO ,PERMISSION_LIST_NO;";
	
	@Override
	public void insert(PermissionVO permissionVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, permissionVO.getPermission_list_no());
			pstmt.setString(2, permissionVO.getAdministrator_no());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, permissionVO.getPermission_list_no());
			pstmt.setString(2, permissionVO.getAdministrator_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, permission_list_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PERMISSION_LIST_NO);

			pstmt.setString(1, permission_list_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ADMINISTRATOR_NO);

			pstmt.setString(1, administrator_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
				permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				list.add(permissionVO);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
