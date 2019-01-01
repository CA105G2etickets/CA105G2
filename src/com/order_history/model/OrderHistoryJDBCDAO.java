package com.order_history.model;

import java.sql.*;
import java.util.*;

import com.order_detail.model.OrderDetailJDBCDAO;
import com.order_detail.model.OrderDetailVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_OrderHistory;

public class OrderHistoryJDBCDAO implements OrderHistoryDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";

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
	private static final String GET_ORDERDETAIL_BY_ORDERNO_STMT = 
		"SELECT ORDER_NO, GOODS_NO, GOODS_BONUS, GOODS_PC FROM ORDER_DETAIL WHERE ORDER_NO = ? ORDER BY ORDER_NO";
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String order_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, order_no);
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
	public OrderHistoryVO findByPrimaryKey(String order_no) {

		OrderHistoryVO orderHistoryVO = null;
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
	public List<String> getAllMemberNo() {
		List<String> list = new ArrayList<String>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_MEMBERNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("MEMBER_NO")); 
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
	public List<OrderHistoryVO> findByMemberNo(String member_no) {
		
		List<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();
		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<OrderHistoryVO> getAll(Map<String, String[]> map) {
		List<OrderHistoryVO> list = new ArrayList<OrderHistoryVO>();
		OrderHistoryVO orderHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public Set<OrderDetailVO> getOrdersDetailByOrderHistory(String order_no) {
		Set<OrderDetailVO> set = new HashSet<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ORDERDETAIL_BY_ORDERNO_STMT);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrder_no(rs.getString("ORDER_NO"));
				orderDetailVO.setGoods_no(rs.getString("GOODS_NO"));
				orderDetailVO.setGoods_bonus(rs.getDouble("GOODS_BONUS"));
				orderDetailVO.setGoods_pc(rs.getDouble("GOODS_PC"));
				set.add(orderDetailVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return set;
	}

	@Override
	public void insertWithDetail (OrderHistoryVO orderHistoryVO, List<OrderDetailVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增訂單紀錄
			String cols[] = {"ORDER_NO"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
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
			//掘取對應的自增主鍵值
			String next_order_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_order_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_order_no +"(剛新增成功的訂單編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增明細
			OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
			System.out.println("list.size()-A=" + list.size());
			for (OrderDetailVO aOrderDetail : list) {
				aOrderDetail.setOrder_no(new String(next_order_no)) ;
				dao.insertOrderHistory(aOrderDetail, con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + next_order_no + "時,共有" + list.size()
					+ "筆訂單明細同時被新增");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-OrderHistory");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	

	public static void main(String[] args) {
	
		OrderHistoryJDBCDAO dao = new OrderHistoryJDBCDAO();

		OrderHistoryVO orderHistoryVO = new OrderHistoryVO();
		orderHistoryVO.setMember_no("M000013");
		orderHistoryVO.setOrder_price(new Double(1280));
		orderHistoryVO.setPay_methods("CREDITCARD");
		orderHistoryVO.setShipping_methods("HOMEDELIVERY");
		orderHistoryVO.setOrder_date(java.sql.Timestamp.valueOf("2018-12-11 09:48:34.524"));
		orderHistoryVO.setOrder_etd(java.sql.Timestamp.valueOf("2018-12-16 09:48:34.524"));
		orderHistoryVO.setPickup_date(java.sql.Timestamp.valueOf("2018-12-21 12:40:51.435"));
		orderHistoryVO.setReceiver_add("330桃園市桃園區民生路124號");
		orderHistoryVO.setReceiver_name("佳樂精緻蛋糕專賣店");
		orderHistoryVO.setReceiver_tel("033335339");
		orderHistoryVO.setOrder_status("COMPLETE4");
		
		List<OrderDetailVO> testList = new ArrayList<OrderDetailVO>(); // 準備置入明細
		OrderDetailVO Detail01 = new OrderDetailVO(); //明細01
		Detail01.setGoods_no("P0000013");
		Detail01.setGoods_bonus(new Double(180));
		Detail01.setGoods_pc(new Double(1));

		OrderDetailVO Detail02 = new OrderDetailVO(); //明細02
		Detail02.setGoods_no("P0000018");
		Detail02.setGoods_bonus(new Double(1100));
		Detail02.setGoods_pc(new Double(1));

		testList.add(Detail01);
		testList.add(Detail02);
		
		dao.insertWithDetail(orderHistoryVO , testList);
		
		
		// 新增
//		OrderHistoryVO orderHistoryVO1 = new OrderHistoryVO();
//		orderHistoryVO1.setMember_no("M000004");
//		orderHistoryVO1.setOrder_price(new Double(80000));
//		orderHistoryVO1.setPay_methods("CREDITCARD");
//		orderHistoryVO1.setShipping_methods("HOMEDELIVERY");
//		orderHistoryVO1.setOrder_date(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		orderHistoryVO1.setOrder_etd(new Timestamp(System.currentTimeMillis()));
//		orderHistoryVO1.setPickup_date(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		orderHistoryVO1.setReceiver_add("320桃園市中壢區中正路389號");
//		orderHistoryVO1.setReceiver_name("NOVA資訊廣場");
//		orderHistoryVO1.setReceiver_tel("034028686");
//		orderHistoryVO1.setOrder_status("COMPLETE4");
//		dao.insert(orderHistoryVO1);

		// 修改
//		OrderHistoryVO orderHistoryVO2 = new OrderHistoryVO();
//		orderHistoryVO2.setOrder_no("O2018121210004");
//		orderHistoryVO2.setMember_no("M000005");
//		orderHistoryVO2.setOrder_price(new Double(44444));
//		orderHistoryVO2.setPay_methods("EWALLET");
//		orderHistoryVO2.setShipping_methods("STOREPICKUP");
//		orderHistoryVO2.setOrder_date(java.sql.Timestamp.valueOf("2018-11-25 09:48:34.524"));
//		orderHistoryVO2.setOrder_etd(new Timestamp(System.currentTimeMillis()));
//		orderHistoryVO2.setPickup_date(java.sql.Timestamp.valueOf("2018-11-30 12:40:51.435"));
//		orderHistoryVO2.setReceiver_add("320桃園市中壢區志廣路62號");
//		orderHistoryVO2.setReceiver_name("嘉義大鍋湯雞肉飯魯肉飯");
//		orderHistoryVO2.setReceiver_tel("0923253177");
//		orderHistoryVO2.setOrder_status("SHIPMENT3");
//		dao.update(orderHistoryVO2);


		// 刪除
//			dao.delete("O2018121210004");

		// 查詢
//		OrderHistoryVO orderHistoryVO3 = dao.findByPrimaryKey("O2018121610002");
//		System.out.println("訂單編號：　" + orderHistoryVO3.getOrder_no());
//		System.out.println("會員編號：　" + orderHistoryVO3.getMember_no());
//		System.out.println("訂單總金額：" + orderHistoryVO3.getOrder_price());
//		System.out.println("付款方式：　" + orderHistoryVO3.getPay_methods());
//		System.out.println("出貨方式：　" + orderHistoryVO3.getShipping_methods());
//		System.out.println("訂購日期：　" + orderHistoryVO3.getOrder_date());
//		System.out.println("出貨日期：　" + orderHistoryVO3.getOrder_etd());
//		System.out.println("取貨日期：　" + orderHistoryVO3.getPickup_date());
//		System.out.println("送貨地址：　" + orderHistoryVO3.getReceiver_add());
//		System.out.println("收件人名稱：" + orderHistoryVO3.getReceiver_name());
//		System.out.println("收件人電話：" + orderHistoryVO3.getReceiver_tel());
//		System.out.println("訂單狀態：　" + orderHistoryVO3.getOrder_status());
//		System.out.println("-------------------------------------------");

		// 查詢列表
//		List<OrderHistoryVO> list = dao.getAll();
//		for (OrderHistoryVO aOrder : list) {
//			System.out.println("訂單編號：　" + aOrder.getOrder_no());
//			System.out.println("會員編號：　" + aOrder.getMember_no());
//			System.out.println("訂單總金額：" + aOrder.getOrder_price());
//			System.out.println("付款方式：　" + aOrder.getPay_methods());
//			System.out.println("出貨方式：　" + aOrder.getShipping_methods());
//			System.out.println("訂購日期：　" + aOrder.getOrder_date());
//			System.out.println("出貨日期：　" + aOrder.getOrder_etd());
//			System.out.println("取貨日期：　 " + aOrder.getPickup_date());
//			System.out.println("送貨地址：　" + aOrder.getReceiver_add());
//			System.out.println("收件人名稱：" + aOrder.getReceiver_name());
//			System.out.println("收件人電話：" + aOrder.getReceiver_tel());
//			System.out.println("訂單狀態：　" + aOrder.getOrder_status());
//			System.out.println("-------------------------------------------");			
//		}
		
		// 查詢會員列表(重覆不顯示)
//		List<String> list = dao.getAllMemberNo();
//		for (String aMemberNo : list) {
//			System.out.println(aMemberNo);
//		}

		// 查詢單一會員的訂單
//		List<OrderHistoryVO> list = dao.findByMemberNo("M000001");
//		for (OrderHistoryVO aOrder : list) {
//			System.out.println("訂單編號：　" + aOrder.getOrder_no());
//			System.out.println("會員編號：　" + aOrder.getMember_no());
//			System.out.println("訂單總金額：" + aOrder.getOrder_price());
//			System.out.println("付款方式：　" + aOrder.getPay_methods());
//			System.out.println("出貨方式：　" + aOrder.getShipping_methods());
//			System.out.println("訂購日期：　" + aOrder.getOrder_date());
//			System.out.println("出貨日期：　" + aOrder.getOrder_etd());
//			System.out.println("取貨日期：　" + aOrder.getPickup_date());
//			System.out.println("送貨地址：　" + aOrder.getReceiver_add());
//			System.out.println("收件人名稱：" + aOrder.getReceiver_name());
//			System.out.println("收件人電話：" + aOrder.getReceiver_tel());
//			System.out.println("訂單狀態：　" + aOrder.getOrder_status());
//			System.out.println("-------------------------------------------");
//		}	
	
	}
}