package com.android.ticket_type.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ticket_type.model.TicketTypeVO;
import com.utility.Util;

public class TicketTypeDAO implements TicketTypeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ONE_STMT = "SELECT tictype_price FROM TICKET_TYPE WHERE tictype_no=?";
	
	@Override
	public String getPrice(String tictype_no) {
		String price = "0";
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, tictype_no); 
					
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				price = String.valueOf(rs.getInt("tictype_price"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
		return price;
	}
}
