package com.ORDER_DETAIL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_DETAIL (ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC) "
		+ "VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC FROM ORDER_DETAIL ORDER BY ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	private static final String UPDATE =
		"UPDATE ORDER_DETAIL SET GOODS_NO = ?, GOODS_BONUS = ?, GOODS_PC = ? WHERE ORDER_NO = ?";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderDetailVO.getOrderNo());
			pstmt.setString(2, orderDetailVO.getGoodsNo());
			pstmt.setDouble(3, orderDetailVO.getGoodsBonus());
			pstmt.setDouble(4, orderDetailVO.getGoodsPc());

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
	public void update(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderDetailVO.getGoodsNo());
			pstmt.setDouble(2, orderDetailVO.getGoodsBonus());
			pstmt.setDouble(3, orderDetailVO.getGoodsPc());
			pstmt.setString(4, orderDetailVO.getOrderNo());

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
	public OrderDetailVO findByPrimaryKey(String orderNo) {

		OrderDetailVO orderDetailVO = null;
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

				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getString("ORDER_NO"));
				orderDetailVO.setGoodsNo(rs.getString("GOODS_NO"));
				orderDetailVO.setGoodsBonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoodsPc(rs.getDouble("GOODS_PC"));

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
		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNo(rs.getString("ORDER_NO"));
				orderDetailVO.setGoodsNo(rs.getString("GOODS_NO"));
				orderDetailVO.setGoodsBonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoodsPc(rs.getDouble("GOODS_PC"));
				list.add(orderDetailVO); // Store the row in the list
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

		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();

		// 新增
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setOrderNo("O2018121610003");
//		orderDetailVO1.setGoodsNo("P0000007");
//		orderDetailVO1.setGoodsBonus(new Double(44444));
//		orderDetailVO1.setGoodsPc(new Double(1));
//		dao.insert(orderDetailVO1);

		// 修改
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setOrderNo("O2018121610002");
//		orderDetailVO2.setGoodsNo("P0000002");
//		orderDetailVO2.setGoodsBonus(new Double(765474));
//		orderDetailVO2.setGoodsPc(new Double(8));
//		dao.update(orderDetailVO2);

		// 刪除
//		dao.delete("O2018121110004");

		// 查詢
//		OrderDetailVO orderDetailVO3 = dao.findByPrimaryKey("O2018121610002");
//		System.out.println("訂單編號：　　" + orderDetailVO3.getOrderNo());
//		System.out.println("商品編號：　　" + orderDetailVO3.getGoodsNo());
//		System.out.println("實際交易單價：" + orderDetailVO3.getGoodsBonus());
//		System.out.println("商品數量：　　" + orderDetailVO3.getGoodsPc());
//		System.out.println("------------------------------------");

		// 查詢列表
//		List<OrderDetailVO> list = dao.getAll();
//		for (OrderDetailVO aOrder : list) {
//			System.out.println("訂單編號：　　" + aOrder.getOrderNo());
//			System.out.println("商品編號：　　" + aOrder.getGoodsNo());
//			System.out.println("實際交易單價：" + aOrder.getGoodsBonus());
//			System.out.println("商品數量：　　" + aOrder.getGoodsPc());
//			System.out.println("------------------------------------");
//		}
		
	}
}
