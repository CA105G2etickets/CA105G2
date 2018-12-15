package com.ORDER_HISTORY.model;

import java.sql.*;
import java.util.*;

public class OrderHistoryJDBCDAO implements OrderHistoryDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_HISTORY (ORDER_NO, MEMBER_NO, ORDER_PRICE, PAY_METHODS, SHIPPING_METHODS,"
		+ " ORDER_DATE, ORDER_ETD, PICKUP_DATE, RECEIVER_ADD, RECEIVER_NAME, RECEIVER_TEL, ORDER_STATUS) "
		+ "VALUES ('O20181212'||LPAD(to_char(ORDER_HISTORY_seq.NEXTVAL),5,'0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT ORDER_NO, MEMBER_NO, ORDER_PRICE, PAY_METHODS, SHIPPING_METHODS, ORDER_DATE, ORDER_ETD,"
		+ " PICKUP_DATE, RECEIVER_ADD, RECEIVER_NAME, RECEIVER_TEL, ORDER_STATUS "
		+ "FROM ORDER_HISTORY ORDER BY ORDER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ORDER_NO, MEMBER_NO, ORDER_PRICE, PAY_METHODS, SHIPPING_METHODS, ORDER_DATE, ORDER_ETD,"
		+ " PICKUP_DATE, RECEIVER_ADD, RECEIVER_NAME, RECEIVER_TEL, ORDER_STATUS "
		+ "FROM ORDER_HISTORY WHERE ORDER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ORDER_HISTORY WHERE ORDER_NO = ?";
	private static final String UPDATE =
		"UPDATE ORDER_HISTORY SET MEMBER_NO=?, ORDER_PRICE=?, PAY_METHODS=?, SHIPPING_METHODS=?,"
		+ " ORDER_DATE=?, ORDER_ETD=?, PICKUP_DATE=?, RECEIVER_ADD=?, RECEIVER_NAME=?, "
		+ "RECEIVER_TEL=?, ORDER_STATUS=? WHERE ORDER_NO = ?";
	
	@Override
	public void insert(OrderHistoryVO orderHistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderHistoryVO.getMemberNo());
			pstmt.setDouble(2, orderHistoryVO.getOrderPrice());
			pstmt.setString(3, orderHistoryVO.getPayMethods());
			pstmt.setString(4, orderHistoryVO.getShippingMethods());
			pstmt.setTimestamp(5, orderHistoryVO.getOrderDate());
			pstmt.setTimestamp(6, orderHistoryVO.getOrderEtd());
			pstmt.setTimestamp(7, orderHistoryVO.getPickupDate());
			pstmt.setString(8, orderHistoryVO.getReceiverAdd());
			pstmt.setString(9, orderHistoryVO.getReceiverName());
			pstmt.setString(10, orderHistoryVO.getReceiverTel());
			pstmt.setString(11, orderHistoryVO.getOrderStatus());

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
	public void update(OrderHistoryVO orderHistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderHistoryVO.getMemberNo());
			pstmt.setDouble(2, orderHistoryVO.getOrderPrice());
			pstmt.setString(3, orderHistoryVO.getPayMethods());
			pstmt.setString(4, orderHistoryVO.getShippingMethods());
			pstmt.setTimestamp(5, orderHistoryVO.getOrderDate());
			pstmt.setTimestamp(6, orderHistoryVO.getOrderEtd());
			pstmt.setTimestamp(7, orderHistoryVO.getPickupDate());
			pstmt.setString(8, orderHistoryVO.getReceiverAdd());
			pstmt.setString(9, orderHistoryVO.getReceiverName());
			pstmt.setString(10, orderHistoryVO.getReceiverTel());
			pstmt.setString(11, orderHistoryVO.getOrderStatus());
			pstmt.setString(12, orderHistoryVO.getOrderNo());

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
	public OrderHistoryVO findByPrimaryKey(String orderNo) {

		OrderHistoryVO orderHistoryVO = null;
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

				orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrderNo(rs.getString("ORDER_NO"));
				orderHistoryVO.setMemberNo(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrderPrice(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPayMethods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShippingMethods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrderEtd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickupDate(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiverAdd(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiverTel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrderStatus(rs.getString("ORDER_STATUS"));
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
		return orderHistoryVO;
	}

	@Override
	public List<OrderHistoryVO> getAll() {
		List<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();
		OrderHistoryVO orderHistoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrderNo(rs.getString("ORDER_NO"));
				orderHistoryVO.setMemberNo(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrderPrice(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPayMethods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShippingMethods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrderEtd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickupDate(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiverAdd(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiverTel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				list.add(orderHistoryVO); 
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
	
		OrderHistoryJDBCDAO dao = new OrderHistoryJDBCDAO();

		// 新增
//		OrderHistoryVO orderHistoryVO1 = new OrderHistoryVO();
//		orderHistoryVO1.setMemberNo("M000005");
//		orderHistoryVO1.setOrderPrice(new Double(80000));
//		orderHistoryVO1.setPayMethods("CREDITCARD");
//		orderHistoryVO1.setShippingMethods("HOMEDELIVERY");
//		orderHistoryVO1.setOrderDate(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		orderHistoryVO1.setOrderEtd(new Timestamp(System.currentTimeMillis()));
//		orderHistoryVO1.setPickupDate(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		orderHistoryVO1.setReceiverAdd("320桃園市中壢區中正路389號");
//		orderHistoryVO1.setReceiverName("NOVA資訊廣場");
//		orderHistoryVO1.setReceiverTel("034028686");
//		orderHistoryVO1.setOrderStatus("COMPLETE4");
//		dao.insert(orderHistoryVO1);

		// 修改
//		OrderHistoryVO orderHistoryVO2 = new OrderHistoryVO();
//		orderHistoryVO2.setOrderNo("O2018121210005");
//		orderHistoryVO2.setMemberNo("M000005");
//		orderHistoryVO2.setOrderPrice(new Double(44444));
//		orderHistoryVO2.setPayMethods("EWALLET");
//		orderHistoryVO2.setShippingMethods("STOREPICKUP");
//		orderHistoryVO2.setOrderDate(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		orderHistoryVO2.setOrderEtd(new Timestamp(System.currentTimeMillis()));
//		orderHistoryVO2.setPickupDate(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		orderHistoryVO2.setReceiverAdd("320桃園市中壢區志廣路62號");
//		orderHistoryVO2.setReceiverName("嘉義大鍋湯雞肉飯魯肉飯");
//		orderHistoryVO2.setReceiverTel("0923253177");
//		orderHistoryVO2.setOrderStatus("SHIPMENT3");
//		dao.update(orderHistoryVO2);


		// 刪除
//			dao.delete("O2018121210004");

		// 查詢
//		OrderHistoryVO orderHistoryVO3 = dao.findByPrimaryKey("O2018121510002");
//		System.out.println("訂單編號：　" + orderHistoryVO3.getOrderNo());
//		System.out.println("會員編號：　" + orderHistoryVO3.getMemberNo());
//		System.out.println("訂單總金額：" + orderHistoryVO3.getOrderPrice());
//		System.out.println("付款方式：　" + orderHistoryVO3.getPayMethods());
//		System.out.println("出貨方式：　" + orderHistoryVO3.getShippingMethods());
//		System.out.println("訂購日期：　" + orderHistoryVO3.getOrderDate());
//		System.out.println("出貨日期：　" + orderHistoryVO3.getOrderEtd());
//		System.out.println("取貨日期：　" + orderHistoryVO3.getPickupDate());
//		System.out.println("送貨地址：　" + orderHistoryVO3.getReceiverAdd());
//		System.out.println("收件人名稱：" + orderHistoryVO3.getReceiverName());
//		System.out.println("收件人電話：" + orderHistoryVO3.getReceiverTel());
//		System.out.println("訂單狀態：　" + orderHistoryVO3.getOrderStatus());
//		System.out.println("-------------------------------------------");

		// 查詢列表
		List<OrderHistoryVO> list = dao.getAll();
		for (OrderHistoryVO aOrder : list) {
			System.out.println("訂單編號：　" + aOrder.getOrderNo());
			System.out.println("會員編號：　" + aOrder.getMemberNo());
			System.out.println("訂單總金額：" + aOrder.getOrderPrice());
			System.out.println("付款方式：　" + aOrder.getPayMethods());
			System.out.println("出貨方式：　" + aOrder.getShippingMethods());
			System.out.println("訂購日期：　" + aOrder.getOrderDate());
			System.out.println("出貨日期：　" + aOrder.getOrderEtd());
			System.out.println("取貨日期：　 " + aOrder.getPickupDate());
			System.out.println("送貨地址：　" + aOrder.getReceiverAdd());
			System.out.println("收件人名稱：" + aOrder.getReceiverName());
			System.out.println("收件人電話：" + aOrder.getReceiverTel());
			System.out.println("訂單狀態：　" + aOrder.getOrderStatus());
		System.out.println("-------------------------------------------");			
		}
		
	}
}