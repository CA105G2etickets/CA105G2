package com.ORDER_HISTORY.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_OrderHistory;

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
	private static final String GET_ALL_MEMBERNO = 
		"SELECT DISTINCT MEMBER_NO FROM ORDER_HISTORY ORDER BY MEMBER_NO";
	private static final String GET_ONE_MEMBERNO = 
		"SELECT * FROM ORDER_HISTORY WHERE MEMBER_NO = ?";
	
	@Override
	public void insert(OrderHistoryVO orderHistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderHistoryVO.getMember_no());
			pstmt.setDouble(2, orderHistoryVO.getOrder_price());
			pstmt.setString(3, orderHistoryVO.getPay_methods());
			pstmt.setString(4, orderHistoryVO.getShipping_methods());
			pstmt.setTimestamp(5, orderHistoryVO.getOrder_date());
			pstmt.setTimestamp(6, orderHistoryVO.getOrder_etd());
			pstmt.setTimestamp(7, orderHistoryVO.getPickup_date());
			pstmt.setString(8, orderHistoryVO.getReceiver_add());
			pstmt.setString(9, orderHistoryVO.getReceiver_name());
			pstmt.setString(10, orderHistoryVO.getReceiver_tel());
			pstmt.setString(11, orderHistoryVO.getOrder_status());

			pstmt.executeUpdate();

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
	public void update(OrderHistoryVO orderHistoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderHistoryVO.getMember_no());
			pstmt.setDouble(2, orderHistoryVO.getOrder_price());
			pstmt.setString(3, orderHistoryVO.getPay_methods());
			pstmt.setString(4, orderHistoryVO.getShipping_methods());
			pstmt.setTimestamp(5, orderHistoryVO.getOrder_date());
			pstmt.setTimestamp(6, orderHistoryVO.getOrder_etd());
			pstmt.setTimestamp(7, orderHistoryVO.getPickup_date());
			pstmt.setString(8, orderHistoryVO.getReceiver_add());
			pstmt.setString(9, orderHistoryVO.getReceiver_name());
			pstmt.setString(10, orderHistoryVO.getReceiver_tel());
			pstmt.setString(11, orderHistoryVO.getOrder_status());
			pstmt.setString(12, orderHistoryVO.getOrder_no());

			pstmt.executeUpdate();

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
	public void delete(String order_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, order_no);
			pstmt.executeUpdate();

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
	public OrderHistoryVO findByPrimaryKey(String order_no) {

		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrder_no(rs.getString("ORDER_NO"));
				orderHistoryVO.setMember_no(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrder_price(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPay_methods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShipping_methods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrder_etd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickup_date(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiver_add(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrder_status(rs.getString("ORDER_STATUS"));
			}


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
				orderHistoryVO.setOrder_no(rs.getString("ORDER_NO"));
				orderHistoryVO.setMember_no(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrder_price(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPay_methods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShipping_methods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrder_etd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickup_date(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiver_add(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrder_status(rs.getString("ORDER_STATUS"));
				list.add(orderHistoryVO); 
			}

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
	
	@Override
	public List<String> getAllMemberNo() {
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMBERNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("MEMBER_NO")); 
			}

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
	
	@Override
	public List<OrderHistoryVO> findByMemberNo(String member_no) {
		
		List<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();
		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMBERNO);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrder_no(rs.getString("ORDER_NO"));
				orderHistoryVO.setMember_no(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrder_price(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPay_methods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShipping_methods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrder_etd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickup_date(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiver_add(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrder_status(rs.getString("ORDER_STATUS"));
				list.add(orderHistoryVO); 
			}

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
	
	@Override
	public List<OrderHistoryVO> getAll(Map<String, String[]> map) {
		List<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();
		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM ORDER_HISTORY "
		          + jdbcUtil_CompositeQuery_OrderHistory.get_WhereCondition(map)
		          + " ORDER BY ORDER_NO";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				orderHistoryVO = new OrderHistoryVO();
				orderHistoryVO.setOrder_no(rs.getString("ORDER_NO"));
				orderHistoryVO.setMember_no(rs.getString("MEMBER_NO"));
				orderHistoryVO.setOrder_price(rs.getDouble("ORDER_PRICE"));
				orderHistoryVO.setPay_methods(rs.getString("PAY_METHODS"));
				orderHistoryVO.setShipping_methods(rs.getString("SHIPPING_METHODS"));
				orderHistoryVO.setOrder_date(rs.getTimestamp("ORDER_DATE"));
				orderHistoryVO.setOrder_etd(rs.getTimestamp("ORDER_ETD"));
				orderHistoryVO.setPickup_date(rs.getTimestamp("PICKUP_DATE"));
				orderHistoryVO.setReceiver_add(rs.getString("RECEIVER_ADD"));
				orderHistoryVO.setReceiver_name(rs.getString("RECEIVER_NAME"));
				orderHistoryVO.setReceiver_tel(rs.getString("RECEIVER_TEL"));
				orderHistoryVO.setOrder_status(rs.getString("ORDER_STATUS"));
				list.add(orderHistoryVO); 
			}

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