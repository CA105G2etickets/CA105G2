package com.FAVORITE_GOODS.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteGoodsJDBCDAO implements FavoriteGoodsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO FAVORITE_GOODS (MEMBER_NO, GOODS_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEMBER_NO, GOODS_NO FROM FAVORITE_GOODS ORDER BY MEMBER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEMBER_NO, GOODS_NO FROM FAVORITE_GOODS WHERE MEMBER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FAVORITE_GOODS WHERE MEMBER_NO = ?";
	private static final String UPDATE =
		"UPDATE FAVORITE_GOODS SET GOODS_NO = ? WHERE MEMBER_NO = ?";
	
	@Override
	public void insert(FavoriteGoodsVO favoriteGoodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favoriteGoodsVO.getMember_no());
			pstmt.setString(2, favoriteGoodsVO.getGoods_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
	public void update(FavoriteGoodsVO favoriteGoodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, favoriteGoodsVO.getGoods_no());
			pstmt.setString(2, favoriteGoodsVO.getMember_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
	public void delete(String member_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_no);
			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
	public FavoriteGoodsVO findByPrimaryKey(String member_no) {

		FavoriteGoodsVO favoriteGoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
		return favoriteGoodsVO;
	}

	@Override
	public List<FavoriteGoodsVO> getAll() {
		List<FavoriteGoodsVO> list = new ArrayList<FavoriteGoodsVO>();
		FavoriteGoodsVO favoriteGoodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));
				list.add(favoriteGoodsVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());

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

		FavoriteGoodsJDBCDAO dao = new FavoriteGoodsJDBCDAO();

		// 新增
//		FavoriteGoodsVO favoriteGoodsVO1 = new FavoriteGoodsVO();
//		favoriteGoodsVO1.setMember_no("M000002");
//		favoriteGoodsVO1.setGoods_no("P0000005");
//		dao.insert(favoriteGoodsVO1);

		// 修改
//		FavoriteGoodsVO favoriteGoodsVO2 = new FavoriteGoodsVO();
//		favoriteGoodsVO2.setMember_no("M000003");
//		favoriteGoodsVO2.setGoods_no("P0000002");
//		dao.update(favoriteGoodsVO2);

		// 刪除
//		dao.delete("M000002");

		// 查詢
//		FavoriteGoodsVO favoriteGoodsVO3 = dao.findByPrimaryKey("M000002");
//		System.out.println("會員編號：　" + favoriteGoodsVO3.getMember_no());
//		System.out.println("商品編號：　" + favoriteGoodsVO3.getGoods_no());
//		System.out.println("------------------------------------");

		// 查詢列表
//		List<FavoriteGoodsVO> list = dao.getAll();
//		for (FavoriteGoodsVO aFavoriteGoods : list) {
//			System.out.println("會員編號：　" + aFavoriteGoods.getMember_no());
//			System.out.println("商品編號：　" + aFavoriteGoods.getGoods_no());
//			System.out.println("------------------------------------");
//		}
	}
}
