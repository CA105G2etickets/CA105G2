package com.android.administrator.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.utility.Util;

public class AdministratorDAO implements AdministratorDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String ISADMIN = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_ACCOUNT = ? AND ADMINISTRATOR_PASSWORD = ?";

	@Override
	public boolean isAdmin(String account, String password) {
		boolean isAdmin = false;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ISADMIN);
			
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if("normal".equals(rs.getString("ADMINISTRATOR_STATUS"))) {
					isAdmin = true;
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
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
		
		return isAdmin;
	}
}