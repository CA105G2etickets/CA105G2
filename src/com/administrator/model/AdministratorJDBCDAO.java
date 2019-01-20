package com.administrator.model;

import java.sql.*;
import java.util.*;

public class AdministratorJDBCDAO implements AdministratorDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADMINISTRATOR (ADMINISTRATOR_NO,ADMINISTRATOR_NAME,ADMINISTRATOR_ACCOUNT,ADMINISTRATOR_PASSWORD,CREATION_DATE,ADMINISTRATOR_PICTURE,ADMINISTRATOR_STATUS) VALUES ('A'||LPAD(to_char(administrator_no_seq.NEXTVAL), 3, '0'), ?, ?, ?,CURRENT_TIMESTAMP, ?, ?)";
	private static final String UPDATE = 
			"UPDATE ADMINISTRATOR SET ADMINISTRATOR_NAME = ?, ADMINISTRATOR_ACCOUNT = ?, ADMINISTRATOR_PASSWORD = ?, CREATION_DATE = ?, ADMINISTRATOR_PICTURE = ?, ADMINISTRATOR_STATUS = ? WHERE ADMINISTRATOR_NO = ?";
	private static final String DELETE = 
			"DELETE FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM ADMINISTRATOR ORDER BY ADMINISTRATOR_NO";
	private static final String GET_ONE_ADMINISTRATOR_BY_ACCOUNT = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_ACCOUNT=?";

	@Override
	public void insert(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, administratorVO.getAdministrator_name());
			pstmt.setString(2, administratorVO.getAdministrator_account());
			pstmt.setString(3, administratorVO.getAdministrator_password());
//			pstmt.setTimestamp(5, administratorVO.getCreation_date());
			pstmt.setBytes(4, administratorVO.getAdministrator_picture());
			pstmt.setString(5, administratorVO.getAdministrator_status());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. (´;ω;`)"
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
	public void update(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdministrator_name());
			pstmt.setString(2, administratorVO.getAdministrator_account());
			pstmt.setString(3, administratorVO.getAdministrator_password());
			pstmt.setTimestamp(4, administratorVO.getCreation_date());
			pstmt.setBytes(5, administratorVO.getAdministrator_picture());
			pstmt.setString(6, administratorVO.getAdministrator_status());
			pstmt.setString(7, administratorVO.getAdministrator_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. (´;ω;`)"
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
	public void delete(String administrator_no) {
		// 管理員不可刪除!
		
	}

	@Override
	public AdministratorVO findByPrimaryKey(String administrator_no) {

		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, administrator_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
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
		return administratorVO;
		
	}

	@Override
	public List<AdministratorVO> getAll() {

		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO administratorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
				list.add(administratorVO);
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
	
public AdministratorVO findByAccount(String administrator_account) {
		
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_ADMINISTRATOR_BY_ACCOUNT);
			rs = pstmt.executeQuery();
			
			pstmt.setString(1, administrator_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
			}
			
		} catch (ClassNotFoundException | SQLException se) {
			throw new RuntimeException("BuBu!"
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
		return administratorVO;
	}

}
