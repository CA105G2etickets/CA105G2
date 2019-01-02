package com.faq.model;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


public class FaqDAO implements FaqDAO_interface {

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
			"INSERT INTO FAQ (FAQ_NO,QUESTION,ANSWER,FAQ_CLASSIFICATION) VALUES ('FAQ'||LPAD(to_char(faq_no_seq.NEXTVAL), 3, '0'), ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE FAQ SET QUESTION = ?, ANSWER = ?, FAQ_CLASSIFICATION = ? WHERE FAQ_NO = ?";
	private static final String DELETE = 
			"DELETE FROM FAQ WHERE FAQ_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM FAQ WHERE FAQ_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM FAQ ORDER BY FAQ_NO";

	@Override
	public void insert(FaqVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, faqVO.getQuestion());
			pstmt.setString(2, faqVO.getAnswer());
			pstmt.setString(3, faqVO.getFaq_classification());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
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
	public void update(FaqVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faqVO.getQuestion());
			pstmt.setString(2, faqVO.getAnswer());
			pstmt.setString(3, faqVO.getFaq_classification());
			pstmt.setString(4, faqVO.getFaq_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
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
	public void delete(String faq_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, faq_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
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
	public FaqVO findByPrimaryKey(String faq_no) {

		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, faq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getString("FAQ_NO"));
				faqVO.setQuestion(rs.getString("QUESTION"));
				faqVO.setAnswer(rs.getString("ANSWER"));
				faqVO.setFaq_classification(rs.getString("FAQ_CLASSIFICATION"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
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
		return faqVO;
	}

	@Override
	public List<FaqVO> getAll() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaq_no(rs.getString("FAQ_NO"));
				faqVO.setQuestion(rs.getString("QUESTION"));
				faqVO.setAnswer(rs.getString("ANSWER"));
				faqVO.setFaq_classification(rs.getString("FAQ_CLASSIFICATION"));
				list.add(faqVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("錯誤!"
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