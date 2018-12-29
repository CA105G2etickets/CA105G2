package com.permission_list.model;

import java.sql.*;
import java.util.*;

public class PermissionListJDBCDAO implements PermissionListDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PERMISSION_LIST (PERMISSION_LIST_NO,PERMISSION) VALUES ('PL'||LPAD(to_char(permission_list_no_seq.NEXTVAL), 2, '0'), ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM PERMISSION_LIST ORDER BY PERMISSION_LIST_NO";
	private static final String DELETE = 
			"DELETE FROM PERMISSION_LIST WHERE PERMISSION_LIST_NO = ?";
	private static final String UPDATE = 
			"UPDATE PERMISSION_LIST SET permissionListNo = ?, permission = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM PERMISSION_LIST WHERE PERMISSION_LIST_NO = ?";
	
	@Override
	public void insert(PermissionListVO permissionList) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, permissionList.getPermission());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. ◢▆▅▄▃崩╰(〒皿〒)╯潰▃▄▅▇◣"
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
	public void update(PermissionListVO permissionList) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, permissionList.getPermissionListNo());
			pstmt.setString(2, permissionList.getPermission());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. ◢▆▅▄▃崩╰(〒皿〒)╯潰▃▄▅▇◣"
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
	public void delete(String permissionListNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, permissionListNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. ◢▆▅▄▃崩╰(〒皿〒)╯潰▃▄▅▇◣"
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
	public PermissionListVO findByPrimaryKey(String permissionListNo) {
		
		PermissionListVO permissionList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, permissionListNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionList = new PermissionListVO();
				permissionList.setPermissionListNo(rs.getString("permissionListNo"));
				permissionList.setPermission(rs.getString("permission"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. HAHAHA guess ClassNotFoundException or SQLException ?"
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
		return permissionList;
	}

	@Override
	public List<PermissionListVO> getAll() {
		
		List<PermissionListVO> list = new ArrayList<PermissionListVO>();
		PermissionListVO permissionList = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				permissionList = new PermissionListVO();
				permissionList.setPermissionListNo(rs.getString("permissionListNo"));
				permissionList.setPermission(rs.getString("permission"));
				list.add(permissionList);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. HAHAHA guess ClassNotFoundException or SQLException ?"
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
