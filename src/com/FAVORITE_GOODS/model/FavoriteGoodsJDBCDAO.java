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
	public void insert(FavoriteGoodsVO favorite_goodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, favorite_goodsVO.getMemberNo());
			pstmt.setString(2, favorite_goodsVO.getGoodsNo());

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
	public void update(FavoriteGoodsVO favorite_goodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, favorite_goodsVO.getGoodsNo());
			pstmt.setString(2, favorite_goodsVO.getMemberNo());

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
	public void delete(String memberNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, memberNo);
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
	public FavoriteGoodsVO findByPrimaryKey(String memberNo) {

		FavoriteGoodsVO favorite_goodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				favorite_goodsVO = new FavoriteGoodsVO();
				favorite_goodsVO.setMemberNo(rs.getString("MEMBER_NO"));
				favorite_goodsVO.setGoodsNo(rs.getString("GOODS_NO"));

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
		return favorite_goodsVO;
	}

	@Override
	public List<FavoriteGoodsVO> getAll() {
		List<FavoriteGoodsVO> list = new ArrayList<FavoriteGoodsVO>();
		FavoriteGoodsVO favorite_goodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favorite_goodsVO = new FavoriteGoodsVO();
				favorite_goodsVO.setMemberNo(rs.getString("MEMBER_NO"));
				favorite_goodsVO.setGoodsNo(rs.getString("GOODS_NO"));
				list.add(favorite_goodsVO); // Store the row in the list
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
//		favorite_goodsVO favorite_goodsVO1 = new favorite_goodsVO();
//		favorite_goodsVO1.setMemberNo("M000004");
//		favorite_goodsVO1.setGoodsNo("P10004");
//		dao.insert(favorite_goodsVO1);

		// 修改
//		favorite_goodsVO favorite_goodsVO2 = new favorite_goodsVO();
//		favorite_goodsVO2.setMemberNo("M000004");
//		favorite_goodsVO2.setGoodsNo("P10777");
//		dao.update(favorite_goodsVO2);

		// 刪除
//		dao.delete("M000004");

		// 查詢
//		favorite_goodsVO favorite_goodsVO3 = dao.findByPrimaryKey("M000002");
//		System.out.println("查詢訂單編號結果↓");
//		System.out.println("會員編號：\t\t" + favorite_goodsVO3.getMemberNo());
//		System.out.println("商品編號：\t\t" + favorite_goodsVO3.getGoodsNo());
//		System.out.println("------------------------------------");

		// 查詢列表
		List<FavoriteGoodsVO> list = dao.getAll();
		System.out.println("查詢訂單明細列表結果↓");
		for (FavoriteGoodsVO aOrder : list) {
			System.out.println("會員編號：\t\t" + aOrder.getMemberNo());
			System.out.println("商品編號：\t\t" + aOrder.getGoodsNo());
			System.out.println("------------------------------------");
		}
	}
}
