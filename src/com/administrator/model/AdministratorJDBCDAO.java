package com.administrator.model;

import java.sql.*;
import java.util.*;

public class AdministratorJDBCDAO implements AdministratorDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADMINISTRATOR (ADMINISTRATOR_NO,ADMINISTRATOR_NAME,ADMINISTRATOR_ACCOUNT,ADMINISTRATOR_PASSWORD,CREATION_DATE,ADMINISTRATOR_PICTURE,ADMINISTRATOR_STATUS) VALUES ('A'||LPAD(to_char(administrator_no_seq.NEXTVAL), 3, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM ADMINISTRATOR ORDER BY ADMINISTRATOR_NO";
	private static final String DELETE = 
			"DELETE FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";
	private static final String UPDATE = 
			"UPDATE ADMINISTRATOR SET ADMINISTRATOR_NAME = ?, ADMINISTRATOR_ACCOUNT = ?, ADMINISTRATOR_PASSWORD = ?, CREATION_DATE = ?, ADMINISTRATOR_PICTURE = ?, ADMINISTRATOR_STATUS = ? WHERE ADMINISTRATOR_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";

	@Override
	public void insert(AdministratorVO administrator) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, administrator.getAdministratorName());
			pstmt.setString(2, administrator.getAdministratorAccount());
			pstmt.setString(3, administrator.getAdministratorPassword());
			pstmt.setTimestamp(4, administrator.getCreationDate());
			pstmt.setBytes(5, administrator.getAdministratorPicture());
			pstmt.setString(6, administrator.getAdministratorStatus());

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
	public void update(AdministratorVO administrator) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administrator.getAdministratorName());
			pstmt.setString(2, administrator.getAdministratorAccount());
			pstmt.setString(3, administrator.getAdministratorPassword());
			pstmt.setTimestamp(4, administrator.getCreationDate());
			pstmt.setBytes(5, administrator.getAdministratorPicture());
			pstmt.setString(6, administrator.getAdministratorStatus());
			pstmt.setString(7, administrator.getAdministratorNo());

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
	public void delete(String administratorNo) {
		// 管理員不可刪除!
		
	}

	@Override
	public AdministratorVO findByPrimaryKey(String administratorNo) {

		AdministratorVO administrator = null;
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
				administrator = new AdministratorVO();
				administrator.setAdministratorNo(rs.getString("administratorNo"));
				administrator.setAdministratorName(rs.getString("administratorName"));
				administrator.setAdministratorAccount(rs.getString("administratorAccount"));
				administrator.setAdministratorPassword(rs.getString("administratorPassword"));
				administrator.setCreationDate(rs.getTimestamp("creationDate"));
				administrator.setAdministratorPicture(rs.getBytes("administratorPicture"));
				administrator.setAdministratorStatus(rs.getString("administratorStatus"));
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
		return administrator;
		
	}

	@Override
	public List<AdministratorVO> getAll() {

		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO administrator = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				administrator = new AdministratorVO();
				administrator.setAdministratorNo(rs.getString("administratorNo"));
				administrator.setAdministratorName(rs.getString("administratorName"));
				administrator.setAdministratorAccount(rs.getString("administratorAccount"));
				administrator.setAdministratorPassword(rs.getString("administratorPassword"));
				administrator.setCreationDate(rs.getTimestamp("creationDate"));
				administrator.setAdministratorPicture(rs.getBytes("administratorPicture"));
				administrator.setAdministratorStatus(rs.getString("administratorStatus"));
				list.add(administrator);
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
