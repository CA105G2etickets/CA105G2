package com.android.event_title.model;

//import java.security.interfaces.RSAKey;
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
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL = "select evetit_no , evetit_name , eveclass_name " + 
										"from event_title left join event_classification " + 
										"on event_title.eveclass_no = event_classification.eveclass_no " +
										"where evetit_name like \'%?%\' " +
										"order by evetit_no";
	private static final String GET_IMAGE = "select EVETIT_POSTER from event_title where evetit_no = ?";
	@Override
	public List<Event_titleVO> getAll(String str , String className) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Event_titleVO> eventList = new ArrayList<Event_titleVO>();
		
		String getAll = "select evetit_no , evetit_name , eveclass_name ,launchdate " + 
				"from event_title left join event_classification " + 
				"on event_title.eveclass_no = event_classification.eveclass_no " +
				"where upper(evetit_name) like upper('%"+str+"%') and eveclass_name like '%"+ className +"%' "+
				"order by launchdate";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
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
	
	public List<String> getclass() {
		List<String> classList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		classList.add("全部種類");
		
		String getAll = "select * from event_classification";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String className = null ; 
				className = rs.getString("EVECLASS_NAME");
				classList.add(className);
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
		return classList;
	}

	@Override
	public List<Event_titleVO> getFavr(String memberNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Event_titleVO> eventList = new ArrayList<Event_titleVO>();
		
		String getAll = "select a.evetit_no,a.evetit_name,b.eveclass_name " + 
				"from(select a.evetit_no,a.evetit_name,a.eveclass_no " + 
				"from favorite_event b left join  (event_title) a " + 
				"on b.evetit_no = a.evetit_no " + 
				"where b.member_no = '"+memberNo+"') a left join event_classification b " + 
				"on a.eveclass_no = b.eveclass_no";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
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
	public List<Event_titleVO> getAllByClass(String str) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Event_titleVO> eventList = new ArrayList<Event_titleVO>();
		
		String getAll = "select evetit_no , evetit_name , eveclass_name ,launchdate " + 
				"from event_title left join event_classification " + 
				"on event_title.eveclass_no = event_classification.eveclass_no " +
				"where upper(eveclass_name) like upper('%"+str+"%') " +
				"order by launchdate";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
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
	public List<Event_titleVO> getNow() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Event_titleVO> eventList = new ArrayList<Event_titleVO>();
		
		String getNow = "select * " + 
				"from event_title a right join " + 
				"(SELECT * " + 
				"FROM EVENT " + 
				"where CURRENT_DATE >= trunc(eve_startdate) and CURRENT_DATE < trunc(eve_enddate) ) b " + 
				"on a.evetit_no = b.evetit_no";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getNow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Event_titleVO evetitVO = new Event_titleVO();
				evetitVO.setEvetitNo(rs.getString("EVETIT_NO"));
				evetitVO.setEvetitName(rs.getString("EVETIT_NAME"));
				evetitVO.setEventNo(rs.getString("EVE_NO"));
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
	public String getTitle(String evetit_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String title = "";
		
		String getTitle = "select evetit_name from event_title where evetit_no = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getTitle);
			pstmt.setString(1, evetit_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				title = rs.getString("evetit_name");
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
		return title;
	}
}
