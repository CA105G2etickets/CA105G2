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
		
	
		
		// 查詢全部
		List<GoodsVO> list = dao.getAll();	
		for(GoodsVO aGoodsVO : list) {
			System.out.println(aGoodsVO.getGoods_no());
			System.out.println(aGoodsVO.getEvetit_no());
			System.out.println(aGoodsVO.getGoods_name());
			System.out.println(aGoodsVO.getGoods_price());

		}
		
		
		
	}

	
	
}
