package com.FAQ.model;

import java.sql.*;
import java.util.*;

public class FaqJDBCDAO implements FaqDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO FAQ (FAQ_NO,QUESTION,ANSWER,FAQ_CLASSIFICATION) VALUES ('FAQ'||LPAD(to_char(faq_no_seq.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM FAQ ORDER BY FAQ_NO";
	private static final String DELETE = 
			"DELETE FROM FAQ WHERE FAQ_NO = ?";
	private static final String UPDATE = 
			"UPDATE FAQ SET QUESTION = ?, ANSWER = ?, FAQ_CLASSIFICATION = ? WHERE FAQ_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM FAQ WHERE FAQ_NO = ?";
	
	@Override
	public void insert(FaqVO faq) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, faq.getQuestion());
			pstmt.setString(2, faq.getAnswer());
			pstmt.setString(3, faq.getFaqClassification());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. HAHAHA guess ClassNotFoundException or SQLException ?"
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
	public void update(FaqVO faq) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faq.getQuestion());
			pstmt.setString(2, faq.getAnswer());
			pstmt.setString(3, faq.getFaqClassification());
			pstmt.setString(4, faq.getFaqNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. HAHAHA guess ClassNotFoundException or SQLException ?"
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
	public void delete(String faqNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, faqNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured. HAHAHA guess ClassNotFoundException or SQLException ?"
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
	public FaqVO findByPrimaryKey(String faqNo) {
		
		FaqVO faq = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, faqNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				faq = new FaqVO();
				faq.setFaqNo(rs.getString("faqNo"));
				faq.setQuestion(rs.getString("question"));
				faq.setAnswer(rs.getString("answer"));
				faq.setFaqClassification(rs.getString("faqClassification"));
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
		return faq;
	}

	@Override
	public List<FaqVO> getAll() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faq = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				faq = new FaqVO();
				faq.setFaqNo(rs.getString("faqNo"));
				faq.setQuestion(rs.getString("question"));
				faq.setAnswer(rs.getString("answer"));
				faq.setFaqClassification(rs.getString("faqClassification"));
				list.add(faq);
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
