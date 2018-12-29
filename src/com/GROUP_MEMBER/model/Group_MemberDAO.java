package com.GROUP_MEMBER.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



public class Group_MemberDAO implements Group_MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Wilson";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO GROUP_MEMBER (MEMBER_NO ,GROUP_NO ,JOIN_TIME ,PRODUCT_QUANTITY ,PAY_STATUS ,GROUP_MEMBER_STATUS ,LOG_OUT_REASON ,ORDER_PHONE ,PAY_METHODS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select * from group_member";
	private static final String GET_ONE_STMT = "SELECT * FROM GROUP_MEMBER where MEMBER_NO = ?";
	private static final String DELETE = "DELETE FROM GROUP_MEMBER where MEMBER_NO = ?";
	private static final String UPDATE = "UPDATE GROUP_MEMBER set GROUP_NO = ?, JOIN_TIME=?, PRODUCT_QUANTITY=?, PAY_STATUS=?, GROUP_MEMBER_STATUS=?, LOG_OUT_REASON=?,ORDER_PHONE=?,PAY_METHODS=? where  MEMBER_NO = ? ";

	@Override
	public void insert(Group_MemberVO Group_MemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Group_MemberVO.getMEMBER_NO());
			pstmt.setString(2, Group_MemberVO.getGROUP_NO());
			pstmt.setTimestamp(3, Group_MemberVO.getJOIN_TIME());
			pstmt.setInt(4, Group_MemberVO.getPRODUCT_QUANTITY());
			pstmt.setString(5, Group_MemberVO.getPAY_STATUS());
			pstmt.setString(6, Group_MemberVO.getGROUP_MEMBER_STATUS());
			pstmt.setString(7, Group_MemberVO.getLOG_OUT_REASON());
			pstmt.setInt(8, Group_MemberVO.getORDER_PHONE());
			pstmt.setString(9, Group_MemberVO.getPAY_STATUS());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(Group_MemberVO Group_MemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(9, Group_MemberVO.getMEMBER_NO());
			pstmt.setString(1, Group_MemberVO.getGROUP_NO());
			pstmt.setTimestamp(2, Group_MemberVO.getJOIN_TIME());
			pstmt.setInt(3, Group_MemberVO.getPRODUCT_QUANTITY());
			pstmt.setString(4, Group_MemberVO.getPAY_STATUS());
			pstmt.setString(5, Group_MemberVO.getGROUP_MEMBER_STATUS());
			pstmt.setString(6, Group_MemberVO.getLOG_OUT_REASON());
			pstmt.setInt(7, Group_MemberVO.getORDER_PHONE());
			pstmt.setString(8, Group_MemberVO.getPAY_STATUS());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String member_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public Group_MemberVO findByPrimaryKey(String member_no) {

		Group_MemberVO grmvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				grmvo = new Group_MemberVO();
				grmvo.setMEMBER_NO(rs.getString("MEMBER_NO"));
				grmvo.setGROUP_NO(rs.getString("GROUP_NO"));
				grmvo.setJOIN_TIME(rs.getTimestamp("JOIN_TIME"));
				grmvo.setPRODUCT_QUANTITY(rs.getInt("PRODUCT_QUANTITY"));
				grmvo.setPAY_STATUS(rs.getString("PAY_STATUS"));
				grmvo.setGROUP_MEMBER_STATUS(rs.getString("GROUP_MEMBER_STATUS"));
				grmvo.setLOG_OUT_REASON(rs.getString("LOG_OUT_REASON"));
				grmvo.setORDER_PHONE(rs.getInt("ORDER_PHONE"));
				grmvo.setPAY_METHODS(rs.getString("PAY_METHODS"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return grmvo;
	}

	@Override
	public List<Group_MemberVO> getAll() {
		List<Group_MemberVO> list = new ArrayList<Group_MemberVO>();
		Group_MemberVO group_MemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				group_MemberVO = new Group_MemberVO();
				group_MemberVO.setMEMBER_NO(rs.getString("MEMBER_NO"));
				group_MemberVO.setGROUP_NO(rs.getString("GROUP_NO"));
				group_MemberVO.setJOIN_TIME(rs.getTimestamp("JOIN_TIME"));
				group_MemberVO.setPRODUCT_QUANTITY(rs.getInt("PRODUCT_QUANTITY"));
				group_MemberVO.setPAY_STATUS(rs.getString("PAY_STATUS"));
				group_MemberVO.setGROUP_MEMBER_STATUS(rs.getString("GROUP_MEMBER_STATUS"));
				group_MemberVO.setLOG_OUT_REASON(rs.getString("LOG_OUT_REASON"));
				group_MemberVO.setORDER_PHONE(rs.getInt("ORDER_PHONE"));
				group_MemberVO.setPAY_METHODS(rs.getString("PAY_METHODS"));
				list.add(group_MemberVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
	public static void main(String[] args) {
		Group_MemberDAO dao = new Group_MemberDAO();
		
		//新增
//		Group_MemberVO Group_MemberVO1 = new Group_MemberVO();
//		
//		Group_MemberVO1.setMEMBER_NO("M000009");
//		Group_MemberVO1.setGROUP_NO("G0003");
//		Group_MemberVO1.setJOIN_TIME(Timestamp.valueOf("2018-12-11 18:00:00"));
//		Group_MemberVO1.setPRODUCT_QUANTITY(5);
//		Group_MemberVO1.setPAY_STATUS("COMPLETE2");
//		Group_MemberVO1.setGROUP_MEMBER_STATUS("withgroup");
//		Group_MemberVO1.setLOG_OUT_REASON(" ");
//		Group_MemberVO1.setORDER_PHONE(937485997);
//		Group_MemberVO1.setPAY_METHODS("EWALLET");
//		
//		dao.insert(Group_MemberVO1);
//		System.out.println("新增完成");
		
	    //修改 
//		Group_MemberVO Group_MemberVO2 = new Group_MemberVO();
//		Group_MemberVO2.setMEMBER_NO("M000002");
//		Group_MemberVO2.setGROUP_NO("G0001");
//		Group_MemberVO2.setJOIN_TIME(Timestamp.valueOf("2018-12-11 18:00:00"));
//		Group_MemberVO2.setPRODUCT_QUANTITY(100000);
//		Group_MemberVO2.setPAY_STATUS("COMPLETE2");
//		Group_MemberVO2.setGROUP_MEMBER_STATUS("withgroup");
//		Group_MemberVO2.setLOG_OUT_REASON(" ");
//		Group_MemberVO2.setORDER_PHONE(937485997);
//		Group_MemberVO2.setPAY_METHODS("CREDITCARD");
//		
//		dao.update(Group_MemberVO2);
//		System.out.println("修改完成");
		
		//刪除
//		dao.delete("M000009");
//		System.out.println("刪除完成");
		
		
		//查詢
//		Group_MemberVO grm2 = dao.findByPrimaryKey("M000001");
//		System.out.println(grm2.getMEMBER_NO()+",");
//		System.out.println(grm2.getGROUP_NO()+",");
//		System.out.println(grm2.getJOIN_TIME()+",");
//		System.out.println(grm2.getPRODUCT_QUANTITY()+",");
//		System.out.println(grm2.getPAY_STATUS()+",");
//		System.out.println(grm2.getGROUP_MEMBER_STATUS()+",");
//		System.out.println(grm2.getLOG_OUT_REASON()+",");
//		System.out.println(grm2.getORDER_PHONE()+",");
//		System.out.println(grm2.getPAY_METHODS()+",");
		
		//查詢
//		List<Group_MemberVO> list = dao.getAll();
//		for(Group_MemberVO grm : list) {
//			System.out.println(grm.getMEMBER_NO()+",");
//			System.out.println(grm.getGROUP_NO()+",");
//			System.out.println(grm.getJOIN_TIME()+",");
//			System.out.println(grm.getPRODUCT_QUANTITY()+",");
//			System.out.println(grm.getPAY_STATUS()+",");
//			System.out.println(grm.getGROUP_MEMBER_STATUS()+",");
//			System.out.println(grm.getLOG_OUT_REASON()+",");
//			System.out.println(grm.getORDER_PHONE()+",");
//			System.out.println(grm.getPAY_METHODS()+",");	
//			System.out.println();
			
//		}
	
		
		
		
	}
	
	

}
