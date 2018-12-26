package com.goods_qa.model;

import java.sql.*;
import java.util.*;

import com.ADVERTISEMENT.model.AdvertisementVO;
import com.EVENT_CLASSIFICATION.model.EventClassificationJDBCDAO;

public class GoodsQaJDBCDAO implements GoodsQaDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO GOODS_QA VALUES ('GF'||LPAD(TO_CHAR(GOODS_QA_SEQ.NEXTVAL),7,'0'),? ,? ,? ,? ,? ,? ,?)";
	private static final String UPDATE_STMT = "UPDATE GOODS_QA SET goods_no=?, member_no=?, administrator_no=?, questions_content=?, answer_content=?,Questions_date=?,answer_date=?, gfaq_no=?";
	private static final String DELETE_STMT = "DELETE FROM GOODS_QA WHERE gfaq_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM GOODS_QA WHERE gfaq_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM GOODS_QA ORDER BY gfaq_no";

	@Override
	public void insert(GoodsQaVO goodsQaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, goodsQaVO.getGoods_no());
			pstmt.setString(2, goodsQaVO.getMember_no());
			pstmt.setString(3, goodsQaVO.getAdministrator_no());
			pstmt.setString(4, goodsQaVO.getQuestions_content());
			pstmt.setString(5, goodsQaVO.getAnswer_content());
			pstmt.setTimestamp(6, goodsQaVO.getQuestions_date());
			pstmt.setTimestamp(7, goodsQaVO.getAnswer_date());

			pstmt.executeUpdate();

			System.out.println("----------Inserted----------");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(GoodsQaVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, faqVO.getGoods_no());
			pstmt.setString(2, faqVO.getMember_no());
			pstmt.setString(3, faqVO.getAdministrator_no());
			pstmt.setString(4, faqVO.getQuestions_content());
			pstmt.setString(5, faqVO.getAnswer_content());
			pstmt.setTimestamp(6, faqVO.getQuestions_date());
			pstmt.setTimestamp(7, faqVO.getAnswer_date());
			pstmt.setString(8, faqVO.getGfaq_no());

			pstmt.executeUpdate();
			System.out.println("----------Updated----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, gfaq_no);

			pstmt.executeUpdate();

			System.out.println("----------Deleted----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

		GoodsQaVO goodsfaqVO1 = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, gfaq_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsfaqVO1 = new GoodsQaVO();
				goodsfaqVO1.setGfaq_no(rs.getString("gfaq_no"));
				goodsfaqVO1.setGoods_no(rs.getString("goods_no"));
				goodsfaqVO1.setMember_no(rs.getString("member_no"));
				goodsfaqVO1.setAdministrator_no(rs.getString("administrator_no"));
				goodsfaqVO1.setQuestions_content(rs.getString("questions_content"));
				goodsfaqVO1.setAnswer_content(rs.getString("answer_content"));
				goodsfaqVO1.setQuestions_date(rs.getTimestamp("Questions_date"));
				goodsfaqVO1.setAnswer_date(rs.getTimestamp("answer_date"));

			}

			System.out.println("----------findByPrimaryKey finished----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return goodsfaqVO1;
	}

	@Override
	public List<GoodsQaVO> getAll() {

		List<GoodsQaVO> list = new ArrayList<GoodsQaVO>();
		GoodsQaVO goodsfaqVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				goodsfaqVO = new GoodsQaVO();
				goodsfaqVO.setGfaq_no(rs.getString("gfaq_no"));
				goodsfaqVO.setGoods_no(rs.getString("goods_no"));
				goodsfaqVO.setMember_no(rs.getString("member_no"));
				goodsfaqVO.setAdministrator_no(rs.getString("administrator_no"));
				goodsfaqVO.setQuestions_content(rs.getString("questions_content"));
				goodsfaqVO.setAnswer_content(rs.getString("answer_content"));
				goodsfaqVO.setQuestions_date(rs.getTimestamp("Questions_date"));
				goodsfaqVO.setAnswer_date(rs.getTimestamp("answer_date"));
				list.add(goodsfaqVO);
			}

			System.out.println("----------getAll finished----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		GoodsQaJDBCDAO dao = new GoodsQaJDBCDAO();

		// 新增
//	GoodsQaVO aGoodsQa = new GoodsQaVO();	
//	aGoodsQa.setGoods_no("P0000001");
//	aGoodsQa.setMember_no("M000002");
//	aGoodsQa.setAdministrator_no("A001");
//	aGoodsQa.setQuestions_content("questions_content");
//	aGoodsQa.setAnswer_content("answer_content");
//	aGoodsQa.setQuestions_date(Timestamp.valueOf("2018-08-20 12:00:00"));
//	aGoodsQa.setAnswer_date(Timestamp.valueOf("2018-08-20 12:00:00"));
//	dao.insert(aGoodsQa);
//	
		// 修改
//	EventClassificationVO EventClassificationVO2 = new EventClassificationVO();
//	EventClassificationVO2.setEveclass_no("B");
//	EventClassificationVO2.setEveclass_name("比賽");
//	dao.update(EventClassificationVO2);

		// 刪除
//dao.delete("GF0000002");
//System.out.println("------------------------------");
		// 查詢一個
//	GoodsQaVO aGoodsQa3 = dao.findByPrimaryKey("GF0000001");
//	System.out.println(aGoodsQa3.getGfaq_no());
//	System.out.println(aGoodsQa3.getGoods_no());
//	System.out.println(aGoodsQa3.getMember_no());
//	System.out.println(aGoodsQa3.getAdministrator_no());
//	System.out.println(aGoodsQa3.getQuestions_content());
//	System.out.println(aGoodsQa3.getAnswer_content());
//	System.out.println(aGoodsQa3.getQuestions_date());
//	System.out.println(aGoodsQa3.getAnswer_date());
//
		// 查詢全部
		List<GoodsQaVO> list = dao.getAll();
		for (GoodsQaVO aGoodsQaVO : list) {
			System.out.println(aGoodsQaVO.getGfaq_no());
			System.out.println(aGoodsQaVO.getGoods_no());
			System.out.println(aGoodsQaVO.getMember_no());
			System.out.println(aGoodsQaVO.getAdministrator_no());
			System.out.println(aGoodsQaVO.getQuestions_content());
			System.out.println(aGoodsQaVO.getAnswer_content());
			System.out.println(aGoodsQaVO.getQuestions_date());
			System.out.println(aGoodsQaVO.getAnswer_date());
		}
	}
}
