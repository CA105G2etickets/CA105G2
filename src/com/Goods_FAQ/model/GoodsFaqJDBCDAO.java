package com.Goods_FAQ.model;

import java.sql.*;
import java.util.*;

public class GoodsFaqJDBCDAO {

	public class GooodsFaqJDBCDAO implements GoodsFaq_interface {
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String USER = "CA105G2";
		private static final String PASSWORD = "123456";

		private static final String INSERT_STMT = "INSERT INTO GOODS_FAQ(gfaq_no, goods_no, member_no, administrator, questions_content, answer_content,qustions_date,answer_date)";
		private static final String GET_ALL_STMT = "SELECT * FROM GOODS_FAQ ORDER BY GFAQ_NO";
		private static final String DELETE_STMT = "DELETE FROM GOODS_FAQ WHERE GFAQ_NO = ?";
		private static final String UPDATE_STMT = "UPDATE GOODS_FAQ SET gfaq_no=?, goods_no=?, member_no=?, administrator=?, questions_content=?, answer_content=?,qustions_date=?,answer_date=?";
		private static final String GET_ONE_STMT = "SELECT * FROM GOODS_FAQ WHERE GFAQ_NO =  ?";

		@Override
		public void insert(GoodsFaqVO faqVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, faqVO.getGfaq_no());
				pstmt.setString(2, faqVO.getGoods_no());
				pstmt.setString(3, faqVO.getMember_no());
				pstmt.setString(4, faqVO.getAdministrator());
				pstmt.setString(5, faqVO.getQuestions_content());
				pstmt.setString(6, faqVO.getAnswer_content());
				pstmt.setTimestamp(7, faqVO.getQustions_date());
				pstmt.setTimestamp(8, faqVO.getAnswer_date());

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
		public void update(GoodsFaqVO faqVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STMT);

				pstmt.setString(1, faqVO.getGfaq_no());
				pstmt.setString(2, faqVO.getGoods_no());
				pstmt.setString(3, faqVO.getMember_no());
				pstmt.setString(4, faqVO.getAdministrator());
				pstmt.setString(5, faqVO.getQuestions_content());
				pstmt.setString(6, faqVO.getAnswer_content());
				pstmt.setTimestamp(7, faqVO.getQustions_date());
				pstmt.setTimestamp(8, faqVO.getAnswer_date());

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
		public GoodsFaqVO findByPrimaryKey(String gfaq_no) {

			GoodsFaqVO goodsfaqVO1 = null;

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
					goodsfaqVO1 = new GoodsFaqVO();
					goodsfaqVO1.setGfaq_no(rs.getString("gfaq_no"));
					goodsfaqVO1.setGoods_no(rs.getString("goods_no"));
					goodsfaqVO1.setMember_no(rs.getString("member_no"));
					goodsfaqVO1.setAdministrator(rs.getString("administrator"));
					goodsfaqVO1.setQuestions_content(rs.getString("questions_content"));
					goodsfaqVO1.setAnswer_content(rs.getString("answer_content"));
					goodsfaqVO1.setQustions_date(rs.getTimestamp("qustions_date"));
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
		public List<GoodsFaqVO> getAll() {

			List<GoodsFaqVO> list = new ArrayList<GoodsFaqVO>();
			GoodsFaqVO goodsfaqVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {

					goodsfaqVO = new GoodsFaqVO();
					goodsfaqVO.setGfaq_no(rs.getString("gfaq_no"));
					goodsfaqVO.setGoods_no(rs.getString("goods_no"));
					goodsfaqVO.setMember_no(rs.getString("member_no"));
					goodsfaqVO.setAdministrator(rs.getString("administrator"));
					goodsfaqVO.setQuestions_content(rs.getString("questions_content"));
					goodsfaqVO.setAnswer_content(rs.getString("answer_content"));
					goodsfaqVO.setQustions_date(rs.getTimestamp("qustions_date"));
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
	}
}
