package com.ticket.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class TicketDAO implements TicketDAO_interface2{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT=
			"INSERT INTO ticket (ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale) "
			+ "VALUES ('T_'||(TO_CHAR(SYSDATE,'YYYYMMDD'))||'_'||LPAD(to_char(TICKET_SEQ.NEXTVAL), 6, '0'),?,?,?,?,?,?,?,?)";
//	private static final String GET_ALL_STMT=
//			"SELECT ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale FROM ticket order by ticket_no";
//	private static final String GET_ONE_STMT=
//			"SELECT ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale FROM ticket where ticket_no=?";
//	private static final String DELETE = 
//			"DELETE FROM ticket where ticket_no = ?";
//	private static final String UPDATE = 
//			"UPDATE ticket set ticarea_no=?, ticket_order_no=?, member_no=?, ticket_status=?, ticket_create_time=?, ticket_resale_status=?,ticket_resale_price=?,is_from_resale=? where ticket_no=?";

	@Override
	public void insertTickets(TicketVO2 ticketVO2, Connection con) {
		
		System.out.println(ticketVO2.getTicket_order_no());
		System.out.println(ticketVO2.getTicarea_no());
		System.out.println(ticketVO2.getMember_no());
		System.out.println(ticketVO2.getTicket_status());
		System.out.println(ticketVO2.getTicket_create_time());
		System.out.println(ticketVO2.getTicket_resale_status());
		System.out.println(ticketVO2.getTicket_resale_price());
		System.out.println(ticketVO2.getIs_from_resale());
		
		PreparedStatement pstmt = null;
		try {
     		pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ticketVO2.getTicarea_no());
			pstmt.setString(2, ticketVO2.getTicket_order_no());
			pstmt.setString(3, ticketVO2.getMember_no());
			pstmt.setString(4, ticketVO2.getTicket_status());
			pstmt.setTimestamp(5, ticketVO2.getTicket_create_time());
			pstmt.setString(6, ticketVO2.getTicket_resale_status());
			pstmt.setInt(7, ticketVO2.getTicket_resale_price());
			pstmt.setString(8, ticketVO2.getIs_from_resale());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-TicketDAO.java");
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
}
