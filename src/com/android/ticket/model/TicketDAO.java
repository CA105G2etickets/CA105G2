package com.android.ticket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class TicketDAO implements TicketDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String ISTICKET = "select * from ticket left join seating_area on ticket.ticarea_no = seating_area.ticarea_no where ticket_no = ? and eve_no = ?";
	private static final String UPDATE = "update ticket set ticket_status= ? where ticket_no= ?";
	private static final String FINDID = "select * from ticket where ticket_no= ?";
	@Override
	public List<TicketVO> getAll(String memberNo,String status,String className) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<TicketVO> list = new ArrayList<>();
		String getAll = 
				"select e.venue_name,t.* " + 
				"from venue e right join " +
				"(select e.evetit_name,e.eveclass_name,t.* " + 
				"from " + 
				"(select evetit_no , evetit_name , eveclass_name " + 
				"from event_title left join event_classification " + 
				"on event_title.eveclass_no = event_classification.eveclass_no " + 
				"order by evetit_no) e right join " + 
				"(select e.evetit_no,e.venue_no,e.eve_startdate,e.ticlimit,t.* " + 
				"from event e right join  " + 
				"(select t.member_no,t.ticket_status,s.eve_no,s.ticarea_name,(tictotalnumber-ticbookednumber) remaining ,t.ticket_no " + 
				"from ticket t left join seating_area s " + 
				"on t.ticarea_no = s.ticarea_no) t " + 
				"on e.eve_no = t.eve_no) t " + 
				"on e.evetit_no = t.evetit_no " + 
				"where member_no = '"+memberNo+"' and ticket_status like '%"+status+"%'and eveclass_name like '%"+className+"%') t " +
				"on e.venue_no = t.venue_no";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				TicketVO ticketVO = new TicketVO();
				ticketVO.setVenueName(rs.getString(1));
				ticketVO.setEvetitName(rs.getString(2));
				ticketVO.setEveClassName(rs.getString(3));
				ticketVO.setEvetitNo(rs.getString(4));
				ticketVO.setEveStartDate(rs.getTimestamp(6));
				ticketVO.setTiclimit(rs.getInt(7));
				ticketVO.setMemberNo(rs.getString(8));
				ticketVO.setTicketStatus(rs.getString(9));
				ticketVO.setEveNo(rs.getString(10));
				ticketVO.setTicareaName(rs.getString(11));
				ticketVO.setRemaining(rs.getInt(12));
				ticketVO.setTicketNo(rs.getString(13));
				list.add(ticketVO);
			}
			
		}catch (SQLException e) {
				e.getStackTrace();
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
		return list;
	}

	@Override
	public boolean isTicket(String ticketNo,String eventNo) {
		boolean isTicket = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ISTICKET);
			pstmt.setString(1, ticketNo);
			pstmt.setString(2, eventNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String status = rs.getString("ticket_status");
				if("ACTIVE1".equals(status)) {
					isTicket = true;
					upDate(ticketNo);
				}
			}
			
		}catch (SQLException e) {
				e.getStackTrace();
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
		return isTicket;
	}
	
	@Override
	public String findIDbyTicket(String ticketNo) {
		String memberNo = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDID);
			pstmt.setString(1, ticketNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberNo = rs.getString("member_no");
			}
			
		}catch (SQLException e) {
				e.getStackTrace();
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
		return memberNo;
	}
	
	private void upDate(String ticketNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, "USED2");
			pstmt.setString(2, ticketNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
}
