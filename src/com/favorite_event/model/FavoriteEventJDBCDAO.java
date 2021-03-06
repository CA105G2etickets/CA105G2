package com.favorite_event.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
     

public class FavoriteEventJDBCDAO implements FavoriteEventDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO FAVORITE_EVENT (MEMBER_NO,EVETIT_NO) VALUES (?, ?)";
	
	private static final String DELETE_STMT = 
			"DELETE FROM FAVORITE_EVENT WHERE MEMBER_NO=? and EVETIT_NO=?";

	private static final String GET_ALL_STMT = 
			"SELECT MEMBER_NO,EVETIT_NO FROM FAVORITE_EVENT WHERE MEMBER_NO=?";
	
	private static final String GET_ONE_STMT = 
			"SELECT MEMBER_NO,EVETIT_NO FROM FAVORITE_EVENT WHERE MEMBER_NO=? AND EVETIT_NO=?";
	
	
	

	@Override
	public void insert(FavoriteEventVO favoriteEventVO) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,favoriteEventVO.getMember_no());
			pstmt.setString(2,favoriteEventVO.getEvetit_no());
			
			pstmt.executeUpdate();
			
			System.out.println("----------Inserted----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String member_no, String evetit_no) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, member_no);
			pstmt.setString(2, evetit_no);

			pstmt.executeUpdate();
			
			System.out.println("----------Deleted----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<FavoriteEventVO> findByMember(String member_no) {
		List<FavoriteEventVO> list = new ArrayList<>();
		FavoriteEventVO favoriteEventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1, member_no); 
					
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				favoriteEventVO = new FavoriteEventVO();
				favoriteEventVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteEventVO.setEvetit_no(rs.getString("EVETIT_NO"));
				list.add(favoriteEventVO);
			}
			
			System.out.println("----------findByMember finished----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void insertbyNo(String member_no, String evetit_no) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,member_no);
			pstmt.setString(2,evetit_no);
			
			pstmt.executeUpdate();
			
			System.out.println("----------insertbyNo finished----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public boolean getOneFavoriteEvent(String member_no, String evetit_no) {

		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		boolean result;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, member_no); 
			pstmt.setString(2, evetit_no); 
					
			rs = pstmt.executeQuery();
			
			result = rs.next();

			System.out.println("----------getOnefavoriteEvent finished----------");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return result;
	}
	
	public static void main(String[] args) {
		
		FavoriteEventJDBCDAO dao = new FavoriteEventJDBCDAO();
		
		// 新增
//		FavoriteEventVO FavoriteEventVO1 = new FavoriteEventVO();
//		FavoriteEventVO1.setMember_no("M000002"); 
//		FavoriteEventVO1.setEvetit_no("E0001");
		
		// 刪除
//		dao.delete("M000001", "E0002");
	
		// 查詢某會員全部
//		List<FavoriteEventVO> list = dao.findByMember("M000001");
//		for (FavoriteEventVO aFavoriteEventVO : list) {
//			System.out.println(aFavoriteEventVO.getMember_no());
//			System.out.println(aFavoriteEventVO.getEvetit_no());
//			System.out.println("------------------------------");		
//		}
		
		// 新增:由會員編號與活動主題編號
//		dao.insertbyNo("M000002", "E0003");
		
		// 查一個
		System.out.println(dao.getOneFavoriteEvent("M000002", "E0003"));
		System.out.println(dao.getOneFavoriteEvent("M000001", "E0001"));
	}
}
