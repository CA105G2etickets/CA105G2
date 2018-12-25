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
	private static final String GET_ALL_STMT = 
			"SELECT * FROM PERMISSION ORDER BY ADMINISTRATOR_NO";
	private static final String DELETE = 
			"DELETE FROM PERMISSION WHERE ADMINISTRATOR_NO = ?";
	private static final String UPDATE = 
			"UPDATE PERMISSION SET PERMISSION_LIST_NO = ? WHERE ADMINISTRATOR_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM PERMISSION WHERE ADMINISTRATOR_NO = ?";
	
	@Override
	public void insert(PermissionVO permission) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, permission.getPermissionListNo());
			pstmt.setString(2, permission.getAdministratorNo());

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
	public void update(PermissionVO permission) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, permission.getPermissionListNo());
			pstmt.setString(2, permission.getAdministratorNo());

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
	public void delete(String administratorNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, administratorNo);

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
	public PermissionVO findByPrimaryKey(String administratorNo) {
		
		PermissionVO permission = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, administratorNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permission = new PermissionVO();
				permission.setPermissionListNo(rs.getString("permissionListNo"));
				permission.setAdministratorNo(rs.getString("administratorNo"));
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
		return permission;
	}

	@Override
	public List<PermissionVO> getAll() {
		
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permission = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				permission = new PermissionVO();
				permission.setPermissionListNo(rs.getString("permissionListNo"));
				permission.setAdministratorNo(rs.getString("administratorNo"));
				list.add(permission);
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
