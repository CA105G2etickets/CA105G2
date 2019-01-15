package com.ticketorder.model;
import java.util.*;
import java.util.Date;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.seating_area.model.SeatingAreaDAO;
import com.seating_area.model.SeatingAreaService;
import com.seating_area.model.SeatingAreaVO;
import com.ticket.model.TicketDAO;
import com.ticket.model.TicketVO2;
import com.ticketorder.model.*;

public class TicketOrderDAO implements TicketOrderDAO_interface2{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	private static final String INSERT_STMT=
//			"INSERT INTO ticket_order (ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status) VALUES ('TO_'||(TO_CHAR(SYSDATE,'YYYYMMDD'))||'_'||LPAD(to_char(TICKET_ORDER_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?)";
//	private static final String GET_ALL_STMT=
//			"SELECT ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status FROM ticket_order order by ticket_order_no";
//	private static final String GET_ONE_STMT=
//			"SELECT ticket_order_no,member_no,ticarea_no,total_price,total_amount,ticket_order_time,payment_method,ticket_order_status FROM ticket_order where ticket_order_no=?";
//	private static final String DELETE = 
//			"DELETE FROM ticket_order where ticket_order_no = ?";
	private static final String UPDATE = 
			"UPDATE ticket_order set member_no=?, ticarea_no=?, total_price=?, total_amount=?, ticket_order_time=?, payment_method=?,ticket_order_status=? where ticket_order_no=?";
	
	@Override
	public void updateTicketOrderAndInsertTickets(TicketOrderVO2 ticketorderVO2, List<TicketVO2> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先update ticket_order
			pstmt = con.prepareStatement(UPDATE);			
			pstmt.setString(1, ticketorderVO2.getMember_no());
			pstmt.setString(2, ticketorderVO2.getTicarea_no());
			pstmt.setInt(3, ticketorderVO2.getTotal_price());
			pstmt.setInt(4, ticketorderVO2.getTotal_amount());
			pstmt.setTimestamp(5, ticketorderVO2.getTicket_order_time());
			pstmt.setString(6, ticketorderVO2.getPayment_method());
			pstmt.setString(7, ticketorderVO2.getTicket_order_status());
			pstmt.setString(8, ticketorderVO2.getTicket_order_no());
			pstmt.executeUpdate();
			
			// 再同時新增票券
			TicketDAO dao = new TicketDAO();
			System.out.println("list.size()-A="+list.size());
			for (TicketVO2 aTicketVO2 : list) {
				aTicketVO2.setTicket_order_no(ticketorderVO2.getTicket_order_no());
				dao.insertTickets(aTicketVO2, con); 
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("UPDATE訂票訂單時,共有幾張票券:" + list.size());
			
			// Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-TicketOrderDAO updateTicketOrderAndInsertTickets");
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

//	@Override
//	public String cancelTicketOrderByServlet(String ticket_order_no) {
//		TicketOrderVO ticketorderVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			// 1●設定於 pstm.executeUpdate()之前
//    		con.setAutoCommit(false);
//			
//    		// 先get TicketOrderVO
//    		pstmt = con.prepareStatement(GET_ONE_STMT);
//    		pstmt.setString(1, ticket_order_no);
//    		
//    		rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo �]�٬� Domain objects
//				ticketorderVO = new TicketOrderVO();
//				ticketorderVO.setTicket_order_no(rs.getString("ticket_order_no"));
//				ticketorderVO.setMember_no(rs.getString("member_no"));
//				ticketorderVO.setTicarea_no(rs.getString("ticarea_no"));
//				ticketorderVO.setTotal_price(rs.getInt("total_price"));
//				ticketorderVO.setTotal_amount(rs.getInt("total_amount"));
//				ticketorderVO.setTicket_order_time(rs.getTimestamp("ticket_order_time"));
//				ticketorderVO.setPayment_method(rs.getString("payment_method"));
//				ticketorderVO.setTicket_order_status(rs.getString("ticket_order_status"));
//			}
//			//check out the status , only cancel the 'WAITTOPAY'
//			String toStatus = ticketorderVO.getTicket_order_status();
//			if(!toStatus.contains("WAIT")) {
//				throw new SQLException("status don't contain WAIT,cant cancel");
//			}
//			
//    		this.cancelOneTicketOrderByServlet(ticketorderVO, con);
//    		
//    		//after update TicketOrderStatus to outdate4, then update the seatingarea's bookednumber
//    		String ticarea_no = ticketorderVO.getTicarea_no();
//    		Integer total_amount_thisTicketOrder = ticketorderVO.getTotal_amount();
//    		
//			//同時delete tic_area's bookednumber
//			SeatingAreaDAO seatingareaDAO = new SeatingAreaDAO();
//			SeatingAreaVO sVO = seatingareaDAO.findByPrimaryKeyWithCon(ticarea_no, con);
//			Integer currentBookedNum = sVO.getTicbookednumber();
//			sVO.setTicbookednumber(currentBookedNum-total_amount_thisTicketOrder);
//			seatingareaDAO.updateSeatingAreaVOBecauseTicketOrderCancelledByServlet(sVO, con);
//			// 2●設定於 pstm.executeUpdate()之後
//			con.commit();
//			con.setAutoCommit(true);
//			
//			// Handle any driver errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-TicketOrderDAO cancelTicketOrderByServlet");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return ticket_order_no;
//	}
//	public void cancelOneTicketOrderByServlet(TicketOrderVO ticketorderVO, Connection con) {
//		PreparedStatement pstmt = null;
//		try {
//			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setString(1, ticketorderVO.getMember_no());
//			pstmt.setString(2, ticketorderVO.getTicarea_no());
//			pstmt.setInt(3, ticketorderVO.getTotal_price());
//			pstmt.setInt(4, ticketorderVO.getTotal_amount());
//			pstmt.setTimestamp(5, ticketorderVO.getTicket_order_time());
//			pstmt.setString(6, ticketorderVO.getPayment_method());
//			pstmt.setString(7, "OUTDATE4");
//			pstmt.setString(8, ticketorderVO.getTicket_order_no());
//
//			pstmt.executeUpdate();
//			
//			System.out.println("----------Updated----------");
//
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3●設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-cancelOneTicketOrderByServlet at TicketOrderDAO.java");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. "
//							+ excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//		
//	}
	
	
}
