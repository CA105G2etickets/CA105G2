package com.NEWS.model;

import java.sql.*;
import java.util.*;

public class NewsJDBCDAO implements NewsDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO NEWS (NEWS_NO,NEWS_CLASSIFICATION_NO,NEWS_TITLE,NEWS_CONTENT,ANNOUNCE_DATE,ADMINISTRATOR_NO) VALUES ('N'||LPAD(to_char(news_N_seq.NEXTVAL), 2, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM NEWS ORDER BY NEWS_NO";
	private static final String DELETE = 
			"DELETE FROM NEWS WHERE NEWS_NO = ?";
	private static final String UPDATE = 
			"UPDATE NEWS SET NEWS_CLASSIFICATION_NO = ?, NEWS_TITLE = ?, NEWS_CONTENT = ?, ANNOUNCE_DATE = ?, ADMINISTRATOR_NO = ? WHERE NEWS_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM NEWS WHERE NEWS_NO = ?";

	@Override
	public void insert(NewsVO news) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, news.getNewsNo());
			pstmt.setString(2, news.getNewsClassificationNo());
			pstmt.setString(3, news.getNewsTitle());
			pstmt.setString(4, news.getNewsContent());
			pstmt.setDate(5, news.getAnnounceDate());
			pstmt.setString(6, news.getAdministratorNo());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
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
	public void update(NewsVO news) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, news.getNewsClassificationNo());
			pstmt.setString(2, news.getNewsTitle());
			pstmt.setString(3, news.getNewsContent());
			pstmt.setDate(4, news.getAnnounceDate());
			pstmt.setString(5, news.getAdministratorNo());
			pstmt.setString(6, news.getNewsNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
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
	public void delete(String newsNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, newsNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
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
	public NewsVO findByPrimaryKey(String newsNo) {

		NewsVO news = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, newsNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				news = new NewsVO();
				news.setNewsNo(rs.getString("newsNo"));
				news.setNewsClassificationNo(rs.getString("newsClassificationNo"));
				news.setNewsTitle(rs.getString("newsTitle"));
				news.setNewsContent(rs.getString("newsContent"));
				news.setAnnounceDate(rs.getDate("announceDate"));
				news.setAdministratorNo(rs.getString("administratorNo"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
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
		return news;
		
	}

	@Override
	public List<NewsVO> getAll() {

		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO news = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				news = new NewsVO();
				news.setNewsNo(rs.getString("newsNo"));
				news.setNewsClassificationNo(rs.getString("newsClassificationNo"));
				news.setNewsTitle(rs.getString("newsTitle"));
				news.setNewsContent(rs.getString("newsContent"));
				news.setAnnounceDate(rs.getDate("announceDate"));
				news.setAdministratorNo(rs.getString("administratorNo"));
				list.add(news);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. Nooooooo~ guess ClassNotFoundException or SQLException ?"
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
