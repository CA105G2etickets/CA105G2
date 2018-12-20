package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO MEMBER (MEMBER_NO,MEMBER_FULLNAME,EMAIL,PHONE,IDCARD,MEMBER_ACCOUNT,MEMBER_PASSWORD,EWALLET_BALANCE,CREATION_DATE,PROFILE_PICTURE,MEMBER_STATUS) VALUES ('M'||LPAD(to_char(member_no_seq.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MEMBER ORDER BY MEMBER_NO";
	private static final String DELETE = 
			"DELETE FROM MEMBER WHERE MEMBER_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER SET MEMBER_FULLNAME = ?, EMAIL = ?, PHONE = ?, IDCARD = ?, MEMBER_ACCOUNT = ?, MEMBER_PASSWORD = ?, EWALLET_BALANCE = ?, CREATION_DATE = ?, PROFILE_PICTURE = ?, MEMBER_STATUS = ? WHERE MEMBER_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM MEMBER WHERE MEMBER_NO = ?";

	@Override
	public void insert(MemberVO member) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
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
	public void update(MemberVO member) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
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
	public void delete(String memberNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, memberNo);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
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
	public MemberVO findByPrimaryKey(String memberNo) {

		MemberVO member = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

		} catch (SQLException se) {
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				list.add(member);
			}

		} catch (SQLException se) {
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
		return list;
	}
}