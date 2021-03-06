package com.news_classification.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class NewsClassificationDAO implements NewsClassificationDAO_interface {

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
			"INSERT INTO NEWS_CLASSIFICATION (NEWS_CLASSIFICATION_NO,NEWS_CLASSIFICATION) VALUES ( ?, ?)";
	private static final String UPDATE = 
			"UPDATE NEWS_CLASSIFICATION SET NEWS_CLASSIFICATION_NO = ?, NEWS_CLASSIFICATION = ? WHERE NEWS_CLASSIFICATION_NO = ?";
//	private static final String DELETE = 
//			"DELETE FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM NEWS_CLASSIFICATION";

	@Override
	public void insert(NewsClassificationVO newsClassification) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, newsClassification.getNewsClassificationNo());
			pstmt.setString(2, newsClassification.getNewsClassification());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("該公告分類代碼已使用"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(NewsClassificationVO newsClassification) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsClassification.getNewsClassificationNo());
			pstmt.setString(2, newsClassification.getNewsClassification());
			pstmt.setString(3, newsClassification.getNewsClassificationNo());

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
	public void delete(String newsClassificationNo) {
		//公告分類部分不可刪除
	}

	@Override
	public NewsClassificationVO findByPrimaryKey(String newsClassificationNo) {
		NewsClassificationVO newsClass = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, newsClassificationNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsClass = new NewsClassificationVO();
				newsClass.setNewsClassificationNo(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsClass.setNewsClassification(rs.getString("NEWS_CLASSIFICATION"));
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
		return newsClass;
	}

	@Override
	public List<NewsClassificationVO> getAll() {
		
		List<NewsClassificationVO> list = new ArrayList<NewsClassificationVO>();
		NewsClassificationVO newsClassification = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsClassification = new NewsClassificationVO();
				newsClassification.setNewsClassificationNo(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsClassification.setNewsClassification(rs.getString("NEWS_CLASSIFICATION"));
				list.add(newsClassification);
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