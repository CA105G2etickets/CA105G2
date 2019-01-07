package com.android.seating_area.model;

import java.sql.Connection;
import java.sql.DriverManager;
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

import oracle.jdbc.proxy.annotation.Pre;
import oracle.net.aso.s;

public class SeatingAreaDAO implements SeatingAreaDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<SeatingAreaVO> findByPrimaryKey(String eventNo) {
		List<SeatingAreaVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String getAll = "select * from seating_area where eve_no = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
			pstmt.setString(1, eventNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				SeatingAreaVO seatingAreaVO = new SeatingAreaVO();
				seatingAreaVO.setTicarea_no(rs.getString("TICAREA_NO"));
				seatingAreaVO.setEve_no(rs.getString("EVE_NO"));
				seatingAreaVO.setTicarea_no(rs.getString("TICTYPE_NO"));
				seatingAreaVO.setTicarea_color(rs.getString("TICAREA_COLOR"));
				seatingAreaVO.setTicarea_name(rs.getString("TICAREA_NAME"));
				seatingAreaVO.setTictotalnumber(rs.getInt("TICTOTALNUMBER"));
				seatingAreaVO.setTicbookednumber(rs.getInt("TICBOOKEDNUMBER"));
				list.add(seatingAreaVO);
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
		
		return list;
	}
	@Override
	public String getEvent(String eventNo) {
		String event = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String getAll = "select * from event where eve_no = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
			pstmt.setString(1, eventNo);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				event = rs.getString("eve_sessionname");
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
		
		return event;
	}
	
}
