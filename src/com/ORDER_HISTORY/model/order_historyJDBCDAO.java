package com.ORDER_HISTORY.model;

import java.sql.*;
import java.util.*;

public class order_historyJDBCDAO implements order_historyDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_HISTORY (ORDER_NO, MEMBER_NO, ORDER_PRICE, PAY_METHODS, SHIPPING_METHODS,"
		+ " ORDER_DATE, ORDER_ETD, PICKUP_DATE, RECEIVER_ADD, RECEIVER_NAME, RECEIVER_TEL, ORDER_STATUS) "
		+ "VALUES 'O20181212'||LPAD(to_char(ORDER_HISTORY_seq.NEXTVAL),5,'0'),?,?,?,?,?,?,?,?,?,?,?)";
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
	public void insert(order_historyVO order_historyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, order_historyVO.getMemberNo());
			pstmt.setDouble(2, order_historyVO.getOrderPrice());
			pstmt.setString(3, order_historyVO.getPayMethods());
			pstmt.setString(4, order_historyVO.getShippingMethods());
			pstmt.setTimestamp(5, order_historyVO.getOrderDate());
			pstmt.setTimestamp(6, order_historyVO.getOrderEtd());
			pstmt.setTimestamp(7, order_historyVO.getPickupDate());
			pstmt.setString(8, order_historyVO.getReceiverAdd());
			pstmt.setString(9, order_historyVO.getReceiverName());
			pstmt.setString(10, order_historyVO.getReceiverTel());
			pstmt.setString(11, order_historyVO.getOrderStatus());

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
	public void update(order_historyVO order_historyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, order_historyVO.getMemberNo());
			pstmt.setDouble(2, order_historyVO.getOrderPrice());
			pstmt.setString(3, order_historyVO.getPayMethods());
			pstmt.setString(4, order_historyVO.getShippingMethods());
			pstmt.setTimestamp(5, order_historyVO.getOrderDate());
			pstmt.setTimestamp(6, order_historyVO.getOrderEtd());
			pstmt.setTimestamp(7, order_historyVO.getPickupDate());
			pstmt.setString(8, order_historyVO.getReceiverAdd());
			pstmt.setString(9, order_historyVO.getReceiverName());
			pstmt.setString(10, order_historyVO.getReceiverTel());
			pstmt.setString(11, order_historyVO.getOrderStatus());
			pstmt.setString(12, order_historyVO.getOrderNo());

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
	public order_historyVO findByPrimaryKey(String orderNo) {

		order_historyVO order_historyVO = null;
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

				order_historyVO = new order_historyVO();
				order_historyVO.setOrderNo(rs.getString("ORDER_NO"));
				order_historyVO.setMemberNo(rs.getString("MEMBER_NO"));
				order_historyVO.setOrderPrice(rs.getDouble("ORDER_PRICE"));
				order_historyVO.setPayMethods(rs.getString("PAY_METHODS"));
				order_historyVO.setShippingMethods(rs.getString("SHIPPING_METHODS"));
				order_historyVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				order_historyVO.setOrderEtd(rs.getTimestamp("ORDER_ETD"));
				order_historyVO.setPickupDate(rs.getTimestamp("PICKUP_DATE"));
				order_historyVO.setReceiverAdd(rs.getString("RECEIVER_ADD"));
				order_historyVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				order_historyVO.setReceiverTel(rs.getString("RECEIVER_TEL"));
				order_historyVO.setOrderStatus(rs.getString("ORDER_STATUS"));
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
		return order_historyVO;
	}

	@Override
	public List<order_historyVO> getAll() {
		List<order_historyVO> list = new ArrayList<order_historyVO>();
		order_historyVO order_historyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_historyVO = new order_historyVO();
				order_historyVO.setOrderNo(rs.getString("ORDER_NO"));
				order_historyVO.setMemberNo(rs.getString("MEMBER_NO"));
				order_historyVO.setOrderPrice(rs.getDouble("ORDER_PRICE"));
				order_historyVO.setPayMethods(rs.getString("PAY_METHODS"));
				order_historyVO.setShippingMethods(rs.getString("SHIPPING_METHODS"));
				order_historyVO.setOrderDate(rs.getTimestamp("ORDER_DATE"));
				order_historyVO.setOrderEtd(rs.getTimestamp("ORDER_ETD"));
				order_historyVO.setPickupDate(rs.getTimestamp("PICKUP_DATE"));
				order_historyVO.setReceiverAdd(rs.getString("RECEIVER_ADD"));
				order_historyVO.setReceiverName(rs.getString("RECEIVER_NAME"));
				order_historyVO.setReceiverTel(rs.getString("RECEIVER_TEL"));
				order_historyVO.setOrderStatus(rs.getString("ORDER_STATUS"));
				list.add(order_historyVO); 
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
	
		order_historyJDBCDAO dao = new order_historyJDBCDAO();

		// 新增
//		order_historyVO order_historyVO1 = new order_historyVO();
//		order_historyVO1.setMemberNo("M000005");
//		order_historyVO1.setOrderPrice(new Double(80000));
//		order_historyVO1.setPayMethods("CREDITCARD");
//		order_historyVO1.setShippingMethods("HOMEDELIVERY");
//		order_historyVO1.setOrderDate(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		order_historyVO1.setOrderEtd(new Timestamp(System.currentTimeMillis()));
//		order_historyVO1.setPickupDate(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		order_historyVO1.setReceiverAdd("320桃園市中壢區中正路389號");
//		order_historyVO1.setReceiverName("NOVA資訊廣場");
//		order_historyVO1.setReceiverTel("034028686");
//		order_historyVO1.setOrderStatus("COMPLETE4");
//		dao.insert(order_historyVO1);

		// 修改
//		order_historyVO order_historyVO2 = new order_historyVO();
//		order_historyVO2.setOrderNo("O2018121210005");
//		order_historyVO2.setMemberNo("M000005");
//		order_historyVO2.setOrderPrice(new Double(44444));
//		order_historyVO2.setPayMethods("EWALLET");
//		order_historyVO2.setShippingMethods("STOREPICKUP");
//		order_historyVO2.setOrderDate(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		order_historyVO2.setOrderEtd(new Timestamp(System.currentTimeMillis()));
//		order_historyVO2.setPickupDate(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		order_historyVO2.setReceiverAdd("320桃園市中壢區志廣路62號");
//		order_historyVO2.setReceiverName("嘉義大鍋湯雞肉飯魯肉飯");
//		order_historyVO2.setReceiverTel("0923253177");
//		order_historyVO2.setOrderStatus("SHIPMENT3");
//		dao.update(order_historyVO2);


		// 刪除
//			dao.delete("O2018121110004");

		// 查詢
//		order_historyVO order_historyVO3 = dao.findByPrimaryKey("O2018121110002");
//		System.out.println("訂單編號：\t\t" + order_historyVO3.getOrderNo());
//		System.out.println("會員編號：\t\t" + order_historyVO3.getMemberNo());
//		System.out.println("訂單總金額：\t" + order_historyVO3.getOrderPrice());
//		System.out.println("付款方式：\t\t" + order_historyVO3.getPayMethods());
//		System.out.println("出貨方式：\t\t" + order_historyVO3.getShippingMethods());
//		System.out.println("訂購日期：\t\t" + order_historyVO3.getOrderDate());
//		System.out.println("出貨日期：\t\t" + order_historyVO3.getOrderEtd());
//		System.out.println("取貨日期\t\t" + order_historyVO3.getPickupDate());
//		System.out.println("送貨地址：\t\t" + order_historyVO3.getReceiverAdd());
//		System.out.println("收件人名稱:\t\t" + order_historyVO3.getReceiverName());
//		System.out.println("收件人電話：\t" + order_historyVO3.getReceiverTel());
//		System.out.println("訂單狀態：\t\t" + order_historyVO3.getOrderStatus());
//		System.out.println("-------------------------------------------");

		// 查詢列表
		List<order_historyVO> list = dao.getAll();
		for (order_historyVO aOrder : list) {
			System.out.println("訂單編號：\t\t" + aOrder.getOrderNo());
			System.out.println("會員編號：\t\t" + aOrder.getMemberNo());
			System.out.println("訂單總金額：\t" + aOrder.getOrderPrice());
			System.out.println("付款方式：\t\t" + aOrder.getPayMethods());
			System.out.println("出貨方式：\t\t" + aOrder.getShippingMethods());
			System.out.println("訂購日期：\t\t" + aOrder.getOrderDate());
			System.out.println("出貨日期：\t\t" + aOrder.getOrderEtd());
			System.out.println("取貨日期\t\t" + aOrder.getPickupDate());
			System.out.println("送貨地址：\t\t" + aOrder.getReceiverAdd());
			System.out.println("收件人名稱:\t\t" + aOrder.getReceiverName());
			System.out.println("收件人電話：\t" + aOrder.getReceiverTel());
			System.out.println("訂單狀態：\t\t" + aOrder.getOrderStatus());
		System.out.println("-------------------------------------------");			
		}
		
	}
}