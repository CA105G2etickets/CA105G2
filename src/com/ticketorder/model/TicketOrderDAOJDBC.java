package com.ticketorder.model;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TicketOrderDAOJDBC implements TicketOrderDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";
	private static final String INSERT_STMT=
			"INSERT INTO ticket_order (ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status) VALUES ('TO_'||(TO_CHAR(SYSDATE,'YYYYMMDD'))||'_'||LPAD(to_char(TICKET_ORDER_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status FROM ticket_order order by ticket_order_no";
	private static final String GET_ONE_STMT=
			"SELECT ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status FROM ticket_order where ticket_order_no=?";
	private static final String DELETE = 
			"DELETE FROM ticket_order where ticket_order_no = ?";
	private static final String UPDATE = 
			"UPDATE ticket_order set member_no=?, ticarea_no=?, total_price=?, total_amount=?, ticket_order_time=?, payment_method=?,ticket_order_status=? where ticket_order_no=?";
	
	@Override
	public void insert(TicketOrderVO ticketorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ticketorderVO.getMember_no());
			pstmt.setString(2, ticketorderVO.getTicarea_no());
			pstmt.setInt(3, ticketorderVO.getTotal_price());
			pstmt.setInt(4, ticketorderVO.getTotal_amount());
			pstmt.setTimestamp(5, ticketorderVO.getTicket_order_time());
			pstmt.setString(6, ticketorderVO.getPayment_method());
			pstmt.setString(7, ticketorderVO.getTicket_order_status());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void update(TicketOrderVO ticketorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ticketorderVO.getMember_no());
			pstmt.setString(2, ticketorderVO.getTicarea_no());
			pstmt.setInt(3, ticketorderVO.getTotal_price());
			pstmt.setInt(4, ticketorderVO.getTotal_amount());
			pstmt.setTimestamp(5, ticketorderVO.getTicket_order_time());
			pstmt.setString(6, ticketorderVO.getPayment_method());
			pstmt.setString(7, ticketorderVO.getTicket_order_status());
			pstmt.setString(8, ticketorderVO.getTicket_order_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public void delete(String ticket_order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ticket_order_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
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

	@Override
	public TicketOrderVO findByPrimaryKey(String ticket_order_no) {
		TicketOrderVO ticketorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ticket_order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticketorderVO = new TicketOrderVO();
				ticketorderVO.setTicket_order_no(rs.getString("ticket_order_no"));
				ticketorderVO.setMember_no(rs.getString("member_no"));
				ticketorderVO.setTicarea_no(rs.getString("ticarea_no"));
				ticketorderVO.setTotal_price(rs.getInt("total_price"));
				ticketorderVO.setTotal_amount(rs.getInt("total_amount"));
				ticketorderVO.setTicket_order_time(rs.getTimestamp("ticket_order_time"));
				ticketorderVO.setPayment_method(rs.getString("payment_method"));
				ticketorderVO.setTicket_order_status(rs.getString("ticket_order_status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		return ticketorderVO;
	}

	@Override
	public List<TicketOrderVO> getAll() {
		List<TicketOrderVO> list = new ArrayList<TicketOrderVO>();
		TicketOrderVO ticketorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ticketorderVO = new TicketOrderVO();
				ticketorderVO.setTicket_order_no(rs.getString("ticket_order_no"));
				ticketorderVO.setMember_no(rs.getString("member_no"));
				ticketorderVO.setTicarea_no(rs.getString("ticarea_no"));
				ticketorderVO.setTotal_price(rs.getInt("total_price"));
				ticketorderVO.setTotal_amount(rs.getInt("total_amount"));
				ticketorderVO.setTicket_order_time(rs.getTimestamp("ticket_order_time"));
				ticketorderVO.setPayment_method(rs.getString("payment_method"));
				ticketorderVO.setTicket_order_status(rs.getString("ticket_order_status"));
				list.add(ticketorderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
//	public static void main (String[] args) {
//		TicketOrderDAOJDBC dao = new TicketOrderDAOJDBC();
//		//ADD
//		TicketOrderVO ticketorderVO = new TicketOrderVO();
//		ticketorderVO.setMember_no("M000001");
//		ticketorderVO.setTicarea_no("E000101A01");
//		ticketorderVO.setTotal_price(9999);
//		ticketorderVO.setTotal_amount(2);
//		long Ltime = new Date().getTime();
//		Timestamp times = new Timestamp(Ltime);
//		ticketorderVO.setTicket_order_time(times);
//		ticketorderVO.setPayment_method("CREDITCARD");
//		ticketorderVO.setTicket_order_status("WAITPAY1");
//		dao.insert(ticketorderVO);
//		
//		//SELECT WITH LIST
//		System.out.println("------selectAll after add start--------");
//		List<TicketOrderVO> list = dao.getAll();
//		for(TicketOrderVO aVO :list) {
//			System.out.print(aVO.getTicket_order_no() + ",");
//			System.out.print(aVO.getMember_no() + ",");
//			System.out.print(aVO.getTicarea_no() + ",");
//			System.out.print(aVO.getTotal_price() + ",");
//			System.out.print(aVO.getTotal_amount() + ",");
//			System.out.print(aVO.getTicket_order_time() + ",");
//			System.out.print(aVO.getPayment_method() + ",");
//			System.out.print(aVO.getTicket_order_status() + ",");
//			System.out.println();
//		}
//		System.out.println("---------------------");
//		
//		//DELETE
//		dao.delete("TO_20181214_000004"); 
//		
//		//SELECT WITH PK
//		TicketOrderVO VO2 = dao.findByPrimaryKey("TO_20181225_000001");
//		System.out.println("------select1 start--------");
//		System.out.print(VO2.getTicket_order_no() + ",");
//		System.out.print(VO2.getMember_no() + ",");
//		System.out.print(VO2.getTicarea_no() + ",");
//		System.out.print(VO2.getTotal_price() + ",");
//		System.out.print(VO2.getTotal_amount() + ",");
//		System.out.print(VO2.getTicket_order_time() + ",");
//		System.out.print(VO2.getPayment_method() + ",");
//		System.out.print(VO2.getTicket_order_status() + ",");
//		System.out.println("---------------------");
//		
//		//UPDATE
//		VO2.setTotal_price(9870); //update VO2.price to 9000;
////		dao.update(VO2);
//		
//		//SELECT WITH LIST
//		System.out.println("------Final selectAll start--------");
//		List<TicketOrderVO> list2 = dao.getAll();
//		for(TicketOrderVO aVO :list2) {
//			System.out.print(aVO.getTicket_order_no() + ",");
//			System.out.print(aVO.getMember_no() + ",");
//			System.out.print(aVO.getTicarea_no() + ",");
//			System.out.print(aVO.getTotal_price() + ",");
//			System.out.print(aVO.getTotal_amount() + ",");
//			System.out.print(aVO.getTicket_order_time() + ",");
//			System.out.print(aVO.getPayment_method() + ",");
//			System.out.print(aVO.getTicket_order_status() + ",");
//			System.out.println();
//		}
//		System.out.println("---------------------");
//	}
}
