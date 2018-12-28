package android.com.EVENT_TITLE.model;

import java.security.interfaces.RSAKey;
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

public class Event_titleDAO implements Event_titleDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL = "select evetit_no , evetit_name , eveclass_name " + 
										"from event_title left join event_classification " + 
										"on event_title.eveclass_no = event_classification.eveclass_no " + 
										"order by evetit_no";
	private static final String GET_IMAGE = "select EVETIT_POSTER from event_title where evetit_no = ?";
	@Override
	public List<Event_titleVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Event_titleVO> eventList = new ArrayList<Event_titleVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Event_titleVO evetitVO = new Event_titleVO();
				evetitVO.setEvetitNo(rs.getString("EVETIT_NO"));
				evetitVO.setEvetitName(rs.getString("EVETIT_NAME"));
				evetitVO.setEveClass(rs.getString("EVECLASS_NAME"));
				eventList.add(evetitVO);
			}
			
		} catch (Exception e) {
			System.out.println(e);
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
		
		return eventList;
	}

	@Override
	public byte[] getImage(String evetit_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] picture = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_IMAGE);
			pstmt.setString(1, evetit_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				picture = rs.getBytes("EVETIT_POSTER");
			}
		} catch (Exception e) {
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
