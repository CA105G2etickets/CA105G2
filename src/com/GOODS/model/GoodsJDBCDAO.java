package com.GOODS.model;


import java.io.*;
import java.sql.*;
import java.util.*;

public class GoodsJDBCDAO implements GoodsDAO_interface{
	

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO GOODS (GOODS_NO, EVETIT_NO, GOODS_NAME, GOODS_PRICE, "
			+ "GOODS_PICTURE1, GOODS_PICTURE2, GOODS_PICTURE3, GOODS_INTRODUCTION, FORSALES_A, FAVORITE_COUNT, GOODS_STATUS, "
			+ "LAUNCHDATE, OFFDATE, GOODS_GROUP_COUNT, GOODS_WANT_COUNT, GOODS_SALES_COUNT) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ?, ?, ?)";
	
	private static final String GET_ALL_STMT = "SELECT GOODS_NO, EVETIT_NO, GOODS_NAME, GOODS_PRICE," 
	       + "GOODS_PICTURE1, GOODS_PICTURE2, GOODS_PICTURE3, GOODS_INTRODUCTION, FORSALES_A, FAVORITE_COUNT, GOODS_STATUS,"  
		   + "LAUNCHDATE, OFFDATE, GOODS_GROUP_COUNT, GOODS_WANT_COUNT, GOODS_SALES_COUNT"
	       + "FROM GOODS ORDER BY GOODS_NO";
			
	private static final String GET_ONE_STMT = "SELECT GOODS_NO, EVETIT_NO, GOODS_NAME, GOODS_PRICE,"
			+ "GOODS_PICTURE1, GOODS_PICTURE2, GOODS_PICTURE3, GOODS_INTRODUCTION, FORSALES_A, FAVORITE_COUNT, GOODS_STATUS,"
			+ "LAUNCHDATE, OFFDATE, GOODS_GROUP_COUNT, GOODS_WANT_COUNT, GOODS_SALES_COUNT"
			+ "FROM GOODS WHERE GOODS_NO = ?";
	
	private static final String DELETE_STMT = 
			"DELETE FROM GOODS WHERE GOODS_NO = ?";
	
	private static final String UPDATE_STMT =
			"UPDATE GOODS SET GOODS_NO=?, GOODS_NAME=?, GOODS_PRICE=?,  GOODS_PICTURE1=?,"
			+ "GOODS_PICTURE2=?, GOODS_PICTURE3=?, GOODS_INTRODUCTION=?, FORSALES_A=?, FAVORITE_COUNT=?,"
			+ "GOODS_STATUS=?, LAUNCHDATE=?, OFFDATE=?, GOODS_GROUP_COUNT=?, GOODS_WANT_COUNT=?, GOODS_SALES_COUNT=?";
	@Override
	public void insert(GoodsVO goodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, goodsVO.getGoods_no());
			pstmt.setString(2, goodsVO.getEvetit_no());
			pstmt.setString(3, goodsVO.getGoods_name());
			pstmt.setInt(4, goodsVO.getGoods_price());
			pstmt.setBytes(5, goodsVO.getGoods_picture1());
			pstmt.setBytes(6, goodsVO.getGoods_picture2());
			pstmt.setBytes(7, goodsVO.getGoods_picture3());
			pstmt.setString(8, goodsVO.getGoods_introduction());
			pstmt.setInt(9,goodsVO.getForsale_a());
			pstmt.setInt(10, goodsVO.getFavorite_count());
			pstmt.setString(11,goodsVO.getGoods_status());
			pstmt.setTimestamp(12,goodsVO.getLaunchdate());
			pstmt.setTimestamp(13,goodsVO.getOffdate());
			pstmt.setInt(14,goodsVO.getGoods_group_count());
			pstmt.setInt(15,goodsVO.getGoods_want_count());
			pstmt.setInt(16,goodsVO.getGoods_sales_count());
			

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
public void update(GoodsVO goodsVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, goodsVO.getGoods_no());
			pstmt.setString(2, goodsVO.getGoods_name());
			pstmt.setInt(3, goodsVO.getGoods_price());
			pstmt.setBytes(4, goodsVO.getGoods_picture1());
			pstmt.setBytes(5, goodsVO.getGoods_picture2());
			pstmt.setBytes(6, goodsVO.getGoods_picture3());
			pstmt.setString(7, goodsVO.getGoods_introduction());
			pstmt.setInt(8,goodsVO.getForsale_a());
			pstmt.setInt(9, goodsVO.getFavorite_count());
			pstmt.setString(10,goodsVO.getGoods_status());
			pstmt.setTimestamp(11,goodsVO.getLaunchdate());
			pstmt.setTimestamp(12,goodsVO.getOffdate());
			pstmt.setInt(13,goodsVO.getGoods_group_count());
			pstmt.setInt(14,goodsVO.getGoods_want_count());
			pstmt.setInt(15,goodsVO.getGoods_sales_count());
			
		
			pstmt.executeUpdate();
			
			System.out.println("----------Updated----------");

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
	public void delete(String goods_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, goods_no);

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
public GoodsVO findByPrimarykey(String goods_no) {
		
		GoodsVO goodsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, goods_no); 
					
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 goodsVO = new GoodsVO();				
				 goodsVO.setGoods_no(rs.getString("Goods_no"));
				 goodsVO.setEvetit_no(rs.getString("Evetit_no"));
				 goodsVO.setGoods_name(rs.getString("Goods_name"));
				 goodsVO.setGoods_price(rs.getInt("Goods_price"));
				 goodsVO.setGoods_picture1(rs.getBytes("Goods_picture1"));
				 goodsVO.setGoods_picture2(rs.getBytes("Goods_picture2"));
				 goodsVO.setGoods_picture3(rs.getBytes("Goods_picture3"));
				 goodsVO.setGoods_introduction(rs.getString("Goods_introduction"));
				 goodsVO.setForsale_a(rs.getInt("Forsale_a"));
				 goodsVO.setFavorite_count(rs.getInt("Favorite_coun"));
				 goodsVO.setGoods_status(rs.getString("Goods_status"));
				 goodsVO.setLaunchdate(rs.getTimestamp("Launchdate"));
				 goodsVO.setOffdate(rs.getTimestamp("Offdate"));
				 goodsVO.setGoods_group_count(rs.getInt("Goods_group_count"));
				 goodsVO.setGoods_want_count(rs.getInt("Goods_want_count"));
				 goodsVO.setGoods_sales_count(rs.getInt("Goods_sales_count"));
			}
			
			System.out.println("----------findByPrimaryKey finished----------");

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
		return goodsVO;
	}

	@Override
	public  List<GoodsVO> getAll() {
		
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		GoodsVO goodsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				 goodsVO = new GoodsVO();				
				 goodsVO.setGoods_no(rs.getString("Goods_no"));
				 goodsVO.setEvetit_no(rs.getString("Evetit_no"));
				 goodsVO.setGoods_name(rs.getString("Goods_name"));
				 goodsVO.setGoods_price(rs.getInt("Goods_price"));
				 goodsVO.setGoods_picture1(rs.getBytes("Goods_picture1"));
				 goodsVO.setGoods_picture2(rs.getBytes("Goods_picture2"));
				 goodsVO.setGoods_picture3(rs.getBytes("Goods_picture3"));
				 goodsVO.setGoods_introduction(rs.getString("Goods_introduction"));
				 goodsVO.setForsale_a(rs.getInt("Forsale_a"));
				 goodsVO.setFavorite_count(rs.getInt("Favorite_coun"));
				 goodsVO.setGoods_status(rs.getString("Goods_status"));
				 goodsVO.setLaunchdate(rs.getTimestamp("Launchdate"));
				 goodsVO.setOffdate(rs.getTimestamp("Offdate"));
				 goodsVO.setGoods_group_count(rs.getInt("Goods_group_count"));
				 goodsVO.setGoods_want_count(rs.getInt("Goods_want_count"));
				 goodsVO.setGoods_sales_count(rs.getInt("Goods_sales_count"));
				 list.add(goodsVO);
			}
			
			System.out.println("----------getAll finished----------");

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
	
public static void main(String[] args) {
		
		
		
		GoodsJDBCDAO dao = new GoodsJDBCDAO();
		
		
		
//		// 新增		
//		FileInputStream fis1 = null;
//		ByteArrayOutputStream baos1 = null;	
//		try {
//			GoodsVO GoodsVO1 = new GoodsVO();			
//			
//			goodsVO1.setEvetit_no("P0000001"); 
//			goodsVO1.setVenue_no("V001");
//			goodsVO1.setEve_session("03");
//			goodsVO1.setEve_no(goodsVO1.getEvetit_no() + goodsVO1.getEve_session()); 			
//			goodsVO1.setEve_sessionname("第三場");					
//			
//			fis1 = new FileInputStream("writeImgJDBC/tomcat.jpg");			
//			baos1 = new ByteArrayOutputStream();			
//			int i;
//			while ((i = fis1.read()) != -1)
//				baos1.write(i);			
//			eventVO1.setEve_seatmap(baos1.toByteArray());	
//			
//			eventVO1.setEve_startdate(java.sql.Timestamp.valueOf("2018-08-20 12:00:00"));							
//			eventVO1.setEve_enddate(java.sql.Timestamp.valueOf("2019-03-10 14:30:00"));		
//			eventVO1.setEve_onsaledate(java.sql.Timestamp.valueOf("2018-09-01 10:00:00")); 				
//			eventVO1.setEve_offsaledate(java.sql.Timestamp.valueOf("2018-03-09 24:00:00"));				
//			eventVO1.setTiclimit(new Integer(4));			
//			eventVO1.setFullrefundenddate(java.sql.Timestamp.valueOf("2018-09-10 10:00:00"));
//			eventVO1.setEve_status("normal");
//			
//			dao.insert(eventVO1);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (baos1 != null) {
//				try {
//					baos1.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (fis1 != null) {
//				try {
//					fis1.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		
//		
//		// 修改		
//		FileInputStream fis2 = null;
//		ByteArrayOutputStream baos2 = null;	
//		try {
//			EventVO eventVO2 = new EventVO();
//			
//			eventVO2.setEve_no("E000101"); 			
//			eventVO2.setVenue_no("V001");
//			eventVO2.setEve_sessionname("第一場");					
//			
//			fis2 = new FileInputStream("writeImgJDBC/java.jpg");			
//			baos2 = new ByteArrayOutputStream();			
//			int i;
//			while ((i = fis2.read()) != -1)
//				baos2.write(i);			
//			eventVO2.setEve_seatmap(baos2.toByteArray());	
//			
//			eventVO2.setEve_startdate(java.sql.Timestamp.valueOf("2017-08-22 12:00:00"));							
//			eventVO2.setEve_enddate(java.sql.Timestamp.valueOf("2018-03-12 14:30:00"));		
//			eventVO2.setEve_onsaledate(java.sql.Timestamp.valueOf("2017-09-01 10:00:00")); 				
//			eventVO2.setEve_offsaledate(java.sql.Timestamp.valueOf("2017-03-31 24:00:00"));				
//			eventVO2.setTiclimit(new Integer(4));			
//			eventVO2.setFullrefundenddate(null);
//			eventVO2.setEve_status("cancel");
//			
//			dao.update(eventVO2);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (baos1 != null) {
//				try {
//					baos1.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (fis1 != null) {
//				try {
//					fis1.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		
//		
//		// 刪除
//		dao.delete("E000103");
//		System.out.println("------------------------------");
//		
//		
//		
//		// 查詢一個
//		EventVO EventVO03 = dao.findByPrimaryKey("E000101");
//		System.out.println(EventVO03.getEve_no());
//		System.out.println(EventVO03.getEvetit_no());
//		System.out.println(EventVO03.getVenue_no());
//		System.out.println(EventVO03.getEve_session());
//		System.out.println(EventVO03.getEve_sessionname());
//		
//		try (PrintStream ps = new PrintStream(new FileOutputStream("readImgJDBC/eventTest.jpg"), true)){
//			ps.write(EventVO03.getEve_seatmap());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(EventVO03.getEve_startdate());
//		System.out.println(EventVO03.getEve_enddate());
//		System.out.println(EventVO03.getEve_onsaledate());
//		System.out.println(EventVO03.getEve_offsaledate());
//		System.out.println(EventVO03.getTiclimit());
//		
//		System.out.println(EventVO03.getFullrefundenddate());
//		System.out.println(EventVO03.getEve_status());	
//		System.out.println("------------------------------");

		
		
		// 查詢全部
		List<GoodsVO> list = dao.getAll();	
		for(GoodsVO aGoodsVO : list) {
			System.out.println(aGoodsVO.getGoods_no());
			System.out.println(aGoodsVO.getEvetit_no());
			System.out.println(aGoodsVO.getGoods_name());
			System.out.println(aGoodsVO.getGoods_price());
//			try (PrintStream ps = new PrintStream(new FileOutputStream("readImgJDBC/" + aGoodsVO.getEvetit_no()() + ".jpg"), true)){
//				ps.write(aGoodsVO.getGoods_picture1()());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println(aGoodsVO.getGoods_picture1());
//			System.out.println(aGoodsVO.getEve_startdate());
//			System.out.println(aGoodsVO.getEve_enddate());
//			System.out.println(aGoodsVO.getEve_onsaledate());
//			System.out.println(aGoodsVO.getEve_offsaledate());
//			System.out.println(aGoodsVO.getTiclimit());
//			System.out.println(aGoodsVO.getFullrefundenddate());
//			System.out.println(aGoodsVO.getEve_status());	
//			System.out.println("------------------------------");
		}
		
		
		
	}

	
	
}
