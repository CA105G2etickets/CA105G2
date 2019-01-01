package com.order_detail.model;

import java.sql.*;
import java.util.*;

import com.order_history.model.OrderHistoryVO;

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
			"DELETE FROM ORDER_DETAIL WHERE ORDER_NO = ? AND GOODS_NO = ?";
	private static final String UPDATE =
		"UPDATE ORDER_DETAIL SET GOODS_NO = ?, GOODS_BONUS = ?, GOODS_PC = ? WHERE ORDER_NO = ?";
	private static final String GET_ALL_ORDERNO = 
		"SELECT DISTINCT MEMBER_NO FROM ORDER_DETAIL ORDER BY ORDER_NO";
	private static final String GET_ONE_ORDERNO = 
		"SELECT * FROM ORDER_DETAIL WHERE ORDER_NO = ?";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, orderDetailVO.getOrder_no());
			pstmt.setString(2, orderDetailVO.getGoods_no());
			pstmt.setDouble(3, orderDetailVO.getGoods_bonus());
			pstmt.setDouble(4, orderDetailVO.getGoods_pc());

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

			pstmt.setString(1, orderDetailVO.getGoods_no());
			pstmt.setDouble(2, orderDetailVO.getGoods_bonus());
			pstmt.setDouble(3, orderDetailVO.getGoods_pc());
			pstmt.setString(4, orderDetailVO.getOrder_no());

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
	public void delete(String order_no, String goods_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, order_no);
			pstmt.setString(2, goods_no);
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
	public OrderDetailVO findByPrimaryKey(String order_no) {

		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(rs.getString("ORDER_NO"));
				orderDetailVO.setGoods_no(rs.getString("GOODS_NO"));
				orderDetailVO.setGoods_bonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoods_pc(rs.getDouble("GOODS_PC"));

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
				orderDetailVO.setOrder_no(rs.getString("ORDER_NO"));
				orderDetailVO.setGoods_no(rs.getString("GOODS_NO"));
				orderDetailVO.setGoods_bonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoods_pc(rs.getDouble("GOODS_PC"));
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

	@Override
	public void insertOrderHistory (OrderDetailVO orderDetailVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderDetailVO.getOrder_no());
			pstmt.setString(2, orderDetailVO.getGoods_no());
			pstmt.setDouble(3, orderDetailVO.getGoods_bonus());
			pstmt.setDouble(4, orderDetailVO.getGoods_pc());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<String> getAllOrderNo() {
		List<String> list = new ArrayList<String>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ORDERNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ORDER_NO")); 
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
	
	@Override
	public List<OrderDetailVO> findByOrderNo(String order_no) {
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ORDERNO);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(rs.getString("ORDER_NO"));
				orderDetailVO.setGoods_no(rs.getString("GOODS_NO"));
				orderDetailVO.setGoods_bonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoods_pc(rs.getDouble("GOODS_PC"));
				list.add(orderDetailVO); 
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
//		orderDetailVO1.setOrder_no("O2018121610003");
//		orderDetailVO1.setGoods_no("P0000007");
//		orderDetailVO1.setGoods_bonus(new Double(44444));
//		orderDetailVO1.setGoods_pc(new Double(1));
//		dao.insert(orderDetailVO1);

		// 修改
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setOrder_no("O2018121610002");
//		orderDetailVO2.setGoods_no("P0000002");
//		orderDetailVO2.setGoods_bonus(new Double(765474));
//		orderDetailVO2.setGoods_pc(new Double(8));
//		dao.update(orderDetailVO2);

		// 刪除
//		dao.delete("O2018121110004");

		// 查詢
//		OrderDetailVO orderDetailVO3 = dao.findByPrimaryKey("O2018121610002");
//		System.out.println("訂單編號：　　" + orderDetailVO3.getOrder_no());
//		System.out.println("商品編號：　　" + orderDetailVO3.getGoods_no());
//		System.out.println("實際交易單價：" + orderDetailVO3.getGoods_bonus());
//		System.out.println("商品數量：　　" + orderDetailVO3.getGoods_pc());
//		System.out.println("------------------------------------");

		// 查詢列表
//		List<OrderDetailVO> list = dao.getAll();
//		for (OrderDetailVO aOrder : list) {
//			System.out.println("訂單編號：　　" + aOrder.getOrder_no());
//			System.out.println("商品編號：　　" + aOrder.getGoods_no());
//			System.out.println("實際交易單價：" + aOrder.getGoods_bonus());
//			System.out.println("商品數量：　　" + aOrder.getGoods_pc());
//			System.out.println("------------------------------------");
//		}
		
	}
}
