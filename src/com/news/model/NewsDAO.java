//package com.news.model;
//
//import java.util.*;
//import java.sql.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class NewsDAO implements NewsDAO_interface {
//
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO NEWS (NEWS_NO,NEWS_CLASSIFICATION_NO,NEWS_TITLE,NEWS_CONTENT,ANNOUNCE_DATE,ADMINISTRATOR_NO) VALUES ('?'||LPAD(to_char(news_S_seq.NEXTVAL), 2, '0'), ?, ?, ?,CURRENT_DATE, ?)";
//	private static final String UPDATE = 
//			"UPDATE NEWS SET NEWS_CLASSIFICATION_NO = ?, NEWS_TITLE = ?, NEWS_CONTENT = ?, ADMINISTRATOR_NO = ? WHERE NEWS_NO = ?";
//	private static final String DELETE = 
//			"DELETE FROM NEWS WHERE NEWS_NO = ?";
//	private static final String GET_ONE_STMT = 
//			"SELECT * FROM NEWS WHERE NEWS_NO = ?";
//	private static final String GET_ALL_STMT = 
//			"SELECT * FROM NEWS ORDER BY NEWS_NO";
//
//	@Override
//	public void insert(NewsVO newsVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1, newsVO.getNews_classification_no());
//			pstmt.setString(2, newsVO.getNews_classification_no());
//			pstmt.setString(3, newsVO.getNews_title());
//			pstmt.setString(4, newsVO.getNews_content());
//			pstmt.setString(5, newsVO.getAdministrator_no());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("錯誤!"
//					+ se.getMessage());
//		} finally {
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
//
//	}
//
//	@Override
//	public void update(NewsVO newsVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, newsVO.getNews_classification_no());
//			pstmt.setString(2, newsVO.getNews_title());
//			pstmt.setString(3, newsVO.getNews_content());
//			pstmt.setString(4, newsVO.getAdministrator_no());
//			pstmt.setString(5, newsVO.getNews_no());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("錯誤!"
//					+ se.getMessage());
//		} finally {
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
//
//	}
//
//	@Override
//	public void delete(String news_no) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(news_no);
//
//			pstmt.setString(1, news_no);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("錯誤!"
//					+ se.getMessage());
//		} finally {
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
//
//	}
//
//	@Override
//	public NewsVO findByPrimaryKey(String news_no) {
//
//		NewsVO member = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, memberNo);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				member = new MemberVO();
//				member.setMemberNo(rs.getString("MEMBER_NO"));
//				member.setMemberFullname(rs.getString("MEMBER_FULLNAME"));
//				member.setEmail(rs.getString("EMAIL"));
//				member.setPhone(rs.getString("PHONE"));
//				member.setIdcard(rs.getString("IDCARD"));
//				member.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
//				member.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
//				member.setEwalletBalance(rs.getInt("EWALLET_BALANCE"));
//				member.setCreationDate(rs.getTimestamp("CREATION_DATE"));
//				member.setProfilePicture(rs.getBytes("PROFILE_PICTURE"));
//				member.setMemberStatus(rs.getString("MEMBER_STATUS"));
//				member.setThirduid(rs.getString("THIRDUID"));
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("錯誤!"
//					+ se.getMessage());
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
//		return member;
//	}
//
//	@Override
//	public List<MemberVO> getAll() {
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		MemberVO member = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				member = new MemberVO();
//				member.setMemberNo(rs.getString("MEMBER_NO"));
//				member.setMemberFullname(rs.getString("MEMBER_FULLNAME"));
//				member.setEmail(rs.getString("EMAIL"));
//				member.setPhone(rs.getString("PHONE"));
//				member.setIdcard(rs.getString("IDCARD"));
//				member.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
//				member.setMemberPassword(rs.getString("MEMBER_PASSWORD"));
//				member.setEwalletBalance(rs.getInt("EWALLET_BALANCE"));
//				member.setCreationDate(rs.getTimestamp("CREATION_DATE"));
//				member.setProfilePicture(rs.getBytes("PROFILE_PICTURE"));
//				member.setMemberStatus(rs.getString("MEMBER_STATUS"));
//				member.setThirduid(rs.getString("THIRDUID"));
//				list.add(member);
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("錯誤!"
//					+ se.getMessage());
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
//		return list;
//	}
//}