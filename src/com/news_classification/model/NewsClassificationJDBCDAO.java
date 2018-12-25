package com.news_classification.model;

import java.sql.*;
import java.util.*;

public class NewsClassificationJDBCDAO implements NewsClassificationDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO NEWS_CLASSIFICATION (NEWS_CLASSIFICATION_NO,NEWS_CLASSIFICATION) VALUES ( ?, ?)";
	private static final String UPDATE = 
			"UPDATE NEWS_CLASSIFICATION SET NEWS_CLASSIFICATION_NO = ?, NEWS_CLASSIFICATION = ?";
//	private static final String DELETE = 
//			"DELETE FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
//	private static final String FIND_BY_PK_SQL = 
//			"SELECT * FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM NEWS_CLASSIFICATION";
	
	@Override
	public void insert(NewsClassificationVO newsClassification) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, newsClassification.getNewsClassificationNo());
			pstmt.setString(2, newsClassification.getNewsClassification());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. QAQ guess ClassNotFoundException or SQLException ?"
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
	public void update(NewsClassificationVO newsClassification) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsClassification.getNewsClassificationNo());
			pstmt.setString(2, newsClassification.getNewsClassification());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. QAQ guess ClassNotFoundException or SQLException ?"
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
	public void delete(String newsClassificationNo) {
		//公告分類部分不可刪除
	}

	@Override
	public NewsClassificationVO findByPrimaryKey(String newsClassificationNo) {
		//公告分類部分暫時不給單一查詢(分類過多時再新增此功能)
		return null;
	}

	@Override
	public List<NewsClassificationVO> getAll() {

		List<NewsClassificationVO> list = new ArrayList<NewsClassificationVO>();
		NewsClassificationVO newsClassification = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsClassification = new NewsClassificationVO();
				newsClassification.setNewsClassificationNo(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsClassification.setNewsClassification(rs.getString("NEWS_CLASSIFICATION"));
				list.add(newsClassification);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. QAQ guess ClassNotFoundException or SQLException ?"
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
