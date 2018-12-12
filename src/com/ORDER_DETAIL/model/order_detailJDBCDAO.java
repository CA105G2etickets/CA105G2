package com.ORDER_DETAIL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class order_detailJDBCDAO implements order_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC) VALUES ('O'||to_char(sysdate,'yyyymmdd')||LPAD(to_char(ORDER_DETAIL_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC FROM ORDER_DETAIL ORDER BY ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	private static final String UPDATE =
		"UPDATE ORDER_DETAIL SET GOODS_NO = ?, GOODS_BONUS = ?, GOODS_PC = ? WHERE ORDER_NO = ?";
	
	@Override
	public void insert(order_detailVO order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_detailVO.getGoodsNo());
			pstmt.setDouble(2, order_detailVO.getGoodsBonus());
			pstmt.setDouble(3, order_detailVO.getGoodsPc());


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
	public void update(order_detailVO order_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, order_detailVO.getGoodsNo());
			pstmt.setDouble(2, order_detailVO.getGoodsBonus());
			pstmt.setDouble(3, order_detailVO.getGoodsPc());
			pstmt.setString(4, order_detailVO.getOrderNo());

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
	public void delete(String orderNo) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, orderNo);
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
	public order_detailVO findByPrimaryKey(String orderNo) {

		order_detailVO order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				order_detailVO = new order_detailVO();
				order_detailVO.setOrderNo(rs.getString("ORDER_NO"));
				order_detailVO.setGoodsNo(rs.getString("GOODS_NO"));
				order_detailVO.setGoodsBonus(rs.getDouble("GOODS_BONUS"));
				order_detailVO.setGoodsPc(rs.getDouble("GOODS_PC"));

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
		return order_detailVO;
	}

	@Override
	public List<order_detailVO> getAll() {
		List<order_detailVO> list = new ArrayList<order_detailVO>();
		order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailVO = new order_detailVO();
				order_detailVO.setOrderNo(rs.getString("ORDER_NO"));
				order_detailVO.setGoodsNo(rs.getString("GOODS_NO"));
				order_detailVO.setGoodsBonus(rs.getDouble("GOODS_BONUS"));
				order_detailVO.setGoodsPc(rs.getDouble("GOODS_PC"));
				list.add(order_detailVO); // Store the row in the list
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

		order_detailJDBCDAO dao = new order_detailJDBCDAO();

		// 新增
//		order_detailVO order_detailVO1 = new order_detailVO();
//		order_detailVO1.setGoodsNo("P10777");
//		order_detailVO1.setGoodsBonus(new Double(7878778));
//		order_detailVO1.setGoodsPc(new Double(87));
//
//		dao.insert(order_detailVO1);

		// 修改
//		order_detailVO order_detailVO2 = new order_detailVO();
//		order_detailVO2.setOrderNo("O2018121110004");
//		order_detailVO2.setGoodsNo("P10777");
//		order_detailVO2.setGoodsBonus(new Double(765474));
//		order_detailVO2.setGoodsPc(new Double(8));
//
//		dao.update(order_detailVO2);

		// 刪除
//		dao.delete("O2018121110004");

		// 查詢
//		order_detailVO order_detailVO3 = dao.findByPrimaryKey("O2018121110002");
//		System.out.println("查詢訂單編號結果↓");
//		System.out.println("訂單編號：\t\t" + order_detailVO3.getOrderNo());
//		System.out.println("商品編號：\t\t" + order_detailVO3.getGoodsNo());
//		System.out.println("實際交易單價：\t" + order_detailVO3.getGoodsBonus());
//		System.out.println("商品數量：\t\t" + order_detailVO3.getGoodsPc());
//		System.out.println("------------------------------------");

		// 查詢列表
		List<order_detailVO> list = dao.getAll();
		System.out.println("查詢訂單明細列表結果↓");
		for (order_detailVO aOrder : list) {
			System.out.println("訂單編號：\t\t" + aOrder.getOrderNo());
			System.out.println("商品編號：\t\t" + aOrder.getGoodsNo());
			System.out.println("實際交易單價：\t" + aOrder.getGoodsBonus());
			System.out.println("商品數量：\t\t" + aOrder.getGoodsPc());
			System.out.println("------------------------------------");
		}
		
	}
}
