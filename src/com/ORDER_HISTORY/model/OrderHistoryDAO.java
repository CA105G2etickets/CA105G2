package com.ORDER_HISTORY.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderHistoryDAO implements OrderHistoryDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO ORDER_HISTORY (ORDER_NO, MEMBER_NO, ORDER_PRICE, PAY_METHODS, SHIPPING_METHODS,"
		+ " ORDER_DATE, ORDER_ETD, PICKUP_DATE, RECEIVER_ADD, RECEIVER_NAME, RECEIVER_TEL, ORDER_STATUS) "
		+ "VALUES ('O'||to_char(sysdate,'yyyymmdd')||LPAD(to_char(ORDER_HISTORY_seq.NEXTVAL), 5, '0'),?,?,?,?,?,?,?,?,?,?,?)";
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

			con = ds.getConnection();
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
	public void update(OrderHistoryVO orderHistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, orderNo);
			pstmt.executeUpdate();

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
	public OrderHistoryVO findByPrimaryKey(String orderNo) {

		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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