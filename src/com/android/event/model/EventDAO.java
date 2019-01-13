package com.android.event.model;

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

import com.utility.Util;


public class EventDAO implements EventDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final String GETEVENTS = "select Ticlimit,eve_sessionname,eve_no from event where evetit_no = ?";
	private final String GET_IMAGE = "select eve_seatmap from event where eve_no = ?";
	
	@Override
	public List<EventVO> getEvents(String evetit_no) {
		List<EventVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETEVENTS);
			pstmt.setString(1, evetit_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EventVO eventVO = new EventVO();
				eventVO.setEve_no(rs.getString("eve_no"));
				eventVO.setEve_sessionname(rs.getString("eve_sessionname"));
				eventVO.setTiclimit(rs.getInt("Ticlimit"));
				list.add(eventVO);
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
	public byte[] getEventImage(String eve_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] picture = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMAGE);
			pstmt.setString(1, eve_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				picture = rs.getBytes("eve_seatmap");
			}
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
		return picture;
	}
}
