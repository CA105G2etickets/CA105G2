package com.goods_qa.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GoodsQaDAO implements GoodsQaDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
		"INSERT INTO GOODS_QA VALUES ('GF'||LPAD(TO_CHAR(GOODS_QA_SEQ.NEXTVAL),7,'0'),? ,? ,? ,? ,? ,? ,?)";
	private static final String UPDATE_STMT =
		"UPDATE GOODS_QA SET GOODS_NO=?, MEMBER_NO=?, ADMINISTRATOR_NO=?, QUESTIONS_CONTENT=?, ANSWER_CONTENT=?, QUESTIONS_DATE=?, ANSWER_DATE=? WHERE GFAQ_NO=?";
	private static final String DELETE_STMT = 
		"DELETE FROM GOODS_QA WHERE GFAQ_NO=?";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM GOODS_QA WHERE GFAQ_NO=?";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM GOODS_QA ORDER BY GFAQ_NO";

	@Override
	public void insert(GoodsQaVO goodsQaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, goodsQaVO.getGoods_no());
			pstmt.setString(2, goodsQaVO.getMember_no());
			pstmt.setString(3, goodsQaVO.getAdministrator_no());
			pstmt.setString(4, goodsQaVO.getQuestions_content());
			pstmt.setString(5, goodsQaVO.getAnswer_content());
			pstmt.setTimestamp(6, goodsQaVO.getQuestions_date());
			pstmt.setTimestamp(7, goodsQaVO.getAnswer_date());
			pstmt.executeUpdate();

			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(GoodsQaVO goodsQaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, goodsQaVO.getGoods_no());
			pstmt.setString(2, goodsQaVO.getMember_no());
			pstmt.setString(3, goodsQaVO.getAdministrator_no());
			pstmt.setString(4, goodsQaVO.getQuestions_content());
			pstmt.setString(5, goodsQaVO.getAnswer_content());
			pstmt.setTimestamp(6, goodsQaVO.getQuestions_date());
			pstmt.setTimestamp(7, goodsQaVO.getAnswer_date());
			pstmt.setString(8, goodsQaVO.getGfaq_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String gfaq_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, gfaq_no);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public GoodsQaVO findByPrimaryKey(String gfaq_no) {

		GoodsQaVO goodsQaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gfaq_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsQaVO = new GoodsQaVO();
				goodsQaVO.setGfaq_no(rs.getString("gfaq_no"));
				goodsQaVO.setGoods_no(rs.getString("goods_no"));
				goodsQaVO.setMember_no(rs.getString("member_no"));
				goodsQaVO.setAdministrator_no(rs.getString("administrator_no"));
				goodsQaVO.setQuestions_content(rs.getString("questions_content"));
				goodsQaVO.setAnswer_content(rs.getString("answer_content"));
				goodsQaVO.setQuestions_date(rs.getTimestamp("Questions_date"));
				goodsQaVO.setAnswer_date(rs.getTimestamp("answer_date"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return goodsQaVO;
	}

	@Override
	public List<GoodsQaVO> getAll() {

		List<GoodsQaVO> list = new ArrayList<GoodsQaVO>();
		GoodsQaVO goodsQaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsQaVO = new GoodsQaVO();
				goodsQaVO.setGfaq_no(rs.getString("gfaq_no"));
				goodsQaVO.setGoods_no(rs.getString("goods_no"));
				goodsQaVO.setMember_no(rs.getString("member_no"));
				goodsQaVO.setAdministrator_no(rs.getString("administrator_no"));
				goodsQaVO.setQuestions_content(rs.getString("questions_content"));
				goodsQaVO.setAnswer_content(rs.getString("answer_content"));
				goodsQaVO.setQuestions_date(rs.getTimestamp("Questions_date"));
				goodsQaVO.setAnswer_date(rs.getTimestamp("answer_date"));
				list.add(goodsQaVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
