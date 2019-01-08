package com.android.member.model;

import java.sql.*;
import java.util.*;

import com.google.gson.JsonObject;


public class MemberJDBCDAO implements MemberDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MEMBER (MEMBER_NO,MEMBER_FULLNAME,EMAIL,PHONE,IDCARD,MEMBER_ACCOUNT,MEMBER_PASSWORD,EWALLET_BALANCE,CREATION_DATE,PROFILE_PICTURE,MEMBER_STATUS) VALUES ('M'||LPAD(to_char(member_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MEMBER ORDER BY MEMBER_NO";
	private static final String DELETE = 
			"DELETE FROM MEMBER WHERE MEMBER_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER SET MEMBER_FULLNAME = ?, EMAIL = ?, PHONE = ?, IDCARD = ?, MEMBER_ACCOUNT = ?, MEMBER_PASSWORD = ?, EWALLET_BALANCE = ?, CREATION_DATE = ?, PROFILE_PICTURE = ?, MEMBER_STATUS = ? WHERE MEMBER_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM MEMBER WHERE MEMBER_NO = ?";
	private static final String ISMEMBER = 
			"SELECT MEMBER_NO, MEMBER_ACCOUNT, MEMBER_PASSWORD FROM MEMBER WHERE MEMBER_ACCOUNT=? AND MEMBER_PASSWORD = ?";
	private static final String GETIMAGE = 
			"SELECT PROFILE_PICTURE FROM MEMBER WHERE MEMBER_NO=?";
	@Override
	public void insert(MemberVO member) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, member.getMemberFullname());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getIdcard());
			pstmt.setString(5, member.getMemberAccount());
			pstmt.setString(6, member.getMemberPassword());
			pstmt.setInt(7, member.getEwalletBalance());
			pstmt.setTimestamp(8, member.getCreationDate());
			pstmt.setBytes(9, member.getProfilePicture());
			pstmt.setString(10, member.getMemberStatus());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("BuBu!"
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
	public void update(MemberVO member) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, member.getMemberFullname());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getIdcard());
			pstmt.setString(5, member.getMemberAccount());
			pstmt.setString(6, member.getMemberPassword());
			pstmt.setInt(7, member.getEwalletBalance());
			pstmt.setTimestamp(8, member.getCreationDate());
			pstmt.setBytes(9, member.getProfilePicture());
			pstmt.setString(10, member.getMemberStatus());
			pstmt.setString(11, member.getMemberNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("BuBu!"
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
	public void delete(String memberNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("BuBu!"
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
	public MemberVO findByPrimaryKey(String memberNo) {
		
		MemberVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, memberNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberVO();
				member.setMemberNo(rs.getString("MEMBER_NO"));
				member.setMemberFullname(rs.getString("MEMBER_FULLNAME"));
				member.setEmail(rs.getString("EMAIL"));
				member.setPhone(rs.getString("PHONE"));
				member.setIdcard(rs.getString("IDCARD"));
				member.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				member.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
				member.setEwalletBalance(rs.getInt("EWALLET_BALANCE"));
				member.setCreationDate(rs.getTimestamp("CREATION_DATE"));
				member.setProfilePicture(rs.getBytes("PROFILE_PICTURE"));
				member.setMemberStatus(rs.getString("MEMBER_STATUS"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
//			throw new RuntimeException("BuBu!"
//					+ e.getMessage());
			
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
		return member;
	}

	@Override
	public List<MemberVO> getAll() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO member = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberVO();
				member.setMemberNo(rs.getString("memberNo"));
				member.setMemberFullname(rs.getString("memberFullname"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setIdcard(rs.getString("idcard"));
				member.setMemberAccount(rs.getString("memberAccount"));
				member.setMemberPassword(rs.getString("memberPassword"));
				member.setEwalletBalance(rs.getInt("ewalletBalance"));
				member.setCreationDate(rs.getTimestamp("creationDate"));
				member.setProfilePicture(rs.getBytes("profilePicture"));
				member.setMemberStatus(rs.getString("memberStatus"));
				list.add(member);
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

//	@Override
//	public boolean isMember(String userName, String userPassword) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean ismember = false;
//		try {
//
//			Class.forName(DRIVER);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			pstmt = con.prepareStatement(ISMEMBER);
//
//			pstmt.setString(1, userName);
//			pstmt.setString(2, userPassword);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				ismember = true;
//				return ismember;
//			}
//
//		} catch (ClassNotFoundException | SQLException e) {
//			throw new RuntimeException("BuBu!"
//					+ e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return ismember;
//	}
	
	public String isMember(String userName, String userPassword,String thirdUID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String memberNo = "";
		boolean ismember = false;
		JsonObject jsonObject = new JsonObject();
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(ISMEMBER);

			pstmt.setString(1, userName);
			pstmt.setString(2, userPassword);
			pstmt.setString(3, thirdUID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberNo = rs.getString("MEMBER_NO");
				ismember = true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("BuBu!"
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
		jsonObject.addProperty("memberNo", memberNo);
		jsonObject.addProperty("isMember", ismember);
		return jsonObject.toString();
	}
	@Override
	public byte[] getImage(String memberNo) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GETIMAGE);
			
			pstmt.setString(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				picture = rs.getBytes("PROFILE_PICTURE");
			}
		} catch (Exception e) {

		}finally {
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
		return picture;
	}
}
