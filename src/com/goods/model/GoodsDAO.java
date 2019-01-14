package com.goods.model;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.event_title.model.CompositeQuery_EventTitle_Launched;
import com.event_title.model.EventTitleVO;

public class GoodsDAO implements GoodsDAO_interface {
 
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
  
	private static final String INSERT_STMT = "INSERT INTO GOODS VALUES('P'||LPAD(TO_CHAR(GOODS_SEQ.NEXTVAL),7,'0'),? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";

	private static final String GET_ALL_STMT = "SELECT * FROM GOODS";

	private static final String GET_ONE_STMT = "SELECT * FROM GOODS WHERE GOODS_NO = ? ";

	private static final String DELETE = "DELETE FROM GOODS WHERE GOODS_NO = ?";

	private static final String UPDATE = "UPDATE GOODS SET EVETIT_NO=?, GOODS_NAME=?, GOODS_PRICE=?,  GOODS_PICTURE1=?, GOODS_PICTURE2=?, GOODS_PICTURE3=?, GOODS_INTRODUCTION=?, FORSALES_A=?, GOODS_STATUS=?, LAUNCHDATE=?, OFFDATE=? WHERE GOODS_NO=?";

	private static final String GET_ALL_STMT_LAUNCHED= "SELECT * FROM GOODS WHERE (CURRENT_DATE BETWEEN launchdate and offdate)";
	@Override
	public void insert(GoodsVO goodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, goodsVO.getEvetit_no());
			pstmt.setString(2, goodsVO.getGoods_name());
			pstmt.setInt(3, goodsVO.getGoods_price());
			pstmt.setBytes(4, goodsVO.getGoods_picture1());
			pstmt.setBytes(5, goodsVO.getGoods_picture2());
			pstmt.setBytes(6, goodsVO.getGoods_picture3());
			pstmt.setString(7, goodsVO.getGoods_introduction());
			pstmt.setInt(8, goodsVO.getForsales_a());
			pstmt.setInt(9, goodsVO.getFavorite_count());
			pstmt.setString(10, goodsVO.getGoods_status());
			pstmt.setTimestamp(11, goodsVO.getLaunchdate());
			pstmt.setTimestamp(12, goodsVO.getOffdate());
			pstmt.setInt(13, goodsVO.getGoods_group_count());
			pstmt.setInt(14, goodsVO.getGoods_want_count());
			pstmt.setInt(15, goodsVO.getGoods_sales_count());

			pstmt.executeUpdate();

			System.out.println("----------Inserted----------");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, goodsVO.getEvetit_no());
			pstmt.setString(2, goodsVO.getGoods_name());
			pstmt.setInt(3, goodsVO.getGoods_price());
			pstmt.setBytes(4, goodsVO.getGoods_picture1());
			pstmt.setBytes(5, goodsVO.getGoods_picture2());
			pstmt.setBytes(6, goodsVO.getGoods_picture3());
			pstmt.setString(7, goodsVO.getGoods_introduction());
			pstmt.setInt(8, goodsVO.getForsales_a());
			pstmt.setInt(9, goodsVO.getFavorite_count());
			pstmt.setString(9, goodsVO.getGoods_status());
			pstmt.setTimestamp(10, goodsVO.getLaunchdate());
			pstmt.setTimestamp(11, goodsVO.getOffdate());
			pstmt.setInt(13, goodsVO.getGoods_group_count());
			pstmt.setInt(14, goodsVO.getGoods_want_count());
			pstmt.setInt(15, goodsVO.getGoods_sales_count());
			pstmt.setString(12, goodsVO.getGoods_no());
			pstmt.executeUpdate();

			System.out.println("----------Updated----------");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, goods_no);

			pstmt.executeUpdate();

			System.out.println("----------Deleted----------");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, goods_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoods_no(rs.getString("Goods_no"));
				goodsVO.setEvetit_no(rs.getString("Evetit_no"));
				goodsVO.setGoods_name(rs.getString("Goods_name"));
				goodsVO.setGoods_price(rs.getInt("Goods_price"));
				goodsVO.setGoods_picture1(rs.getBytes("Goods_picture1"));
				goodsVO.setGoods_picture2(rs.getBytes("Goods_picture2"));
				goodsVO.setGoods_picture3(rs.getBytes("Goods_picture3"));
				goodsVO.setGoods_introduction(rs.getString("Goods_introduction"));
				goodsVO.setForsales_a(rs.getInt("Forsales_a"));
				goodsVO.setFavorite_count(rs.getInt("Favorite_count"));
				goodsVO.setGoods_status(rs.getString("Goods_status"));
				goodsVO.setLaunchdate(rs.getTimestamp("Launchdate"));
				goodsVO.setOffdate(rs.getTimestamp("Offdate"));
				goodsVO.setGoods_group_count(rs.getInt("Goods_group_count"));
				goodsVO.setGoods_want_count(rs.getInt("Goods_want_count"));
				goodsVO.setGoods_sales_count(rs.getInt("Goods_sales_count"));
			}

			System.out.println("----------findByPrimaryKey finished----------");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<GoodsVO> getAll() {

		List<GoodsVO> list = new ArrayList<GoodsVO>();
		GoodsVO goodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoods_no(rs.getString("goods_no"));
				goodsVO.setEvetit_no(rs.getString("evetit_no"));
				goodsVO.setGoods_name(rs.getString("goods_name"));
				goodsVO.setGoods_price(rs.getInt("goods_price"));
				goodsVO.setGoods_picture1(rs.getBytes("goods_picture1"));
				goodsVO.setGoods_picture2(rs.getBytes("goods_picture2"));
				goodsVO.setGoods_picture3(rs.getBytes("goods_picture3"));
				goodsVO.setGoods_introduction(rs.getString("goods_introduction"));
				goodsVO.setForsales_a(rs.getInt("Forsales_a"));
				goodsVO.setFavorite_count(rs.getInt("favorite_count"));
				goodsVO.setGoods_status(rs.getString("goods_status"));
				goodsVO.setLaunchdate(rs.getTimestamp("launchdate"));
				goodsVO.setOffdate(rs.getTimestamp("offdate"));
				goodsVO.setGoods_group_count(rs.getInt("goods_group_count"));
				goodsVO.setGoods_want_count(rs.getInt("goods_want_count"));
				goodsVO.setGoods_sales_count(rs.getInt("goods_sales_count"));
				list.add(goodsVO);
			}

			System.out.println("----------getAll finished----------");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public List<GoodsVO> getAllLaunched(Map<String, String[]> map) {
		
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		GoodsVO goodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BufferedReader br = null;


		try {

			con = ds.getConnection();
			String finalSQL = "SELECT * FROM GOODS WHERE (goods_status = 'confirmed') AND (CURRENT_DATE BETWEEN launchdate AND offdate) "
			          + CompositeQuery_Goods_Launched.get_WhereCondition(map);
			System.out.println("CompositeQuery_Goods_Launched : ");
			System.out.println(finalSQL);
			
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoods_no(rs.getString("goods_no"));
				goodsVO.setEvetit_no(rs.getString("evetit_no"));
				goodsVO.setGoods_name(rs.getString("goods_name"));
				goodsVO.setGoods_price(rs.getInt("goods_price"));
				goodsVO.setGoods_picture1(rs.getBytes("goods_picture1"));
				goodsVO.setGoods_picture2(rs.getBytes("goods_picture2"));
				goodsVO.setGoods_picture3(rs.getBytes("goods_picture3"));
				goodsVO.setGoods_introduction(rs.getString("goods_introduction"));
				goodsVO.setForsales_a(rs.getInt("Forsales_a"));
				goodsVO.setFavorite_count(rs.getInt("favorite_count"));
				goodsVO.setGoods_status(rs.getString("goods_status"));
				goodsVO.setLaunchdate(rs.getTimestamp("launchdate"));
				goodsVO.setOffdate(rs.getTimestamp("offdate"));
				goodsVO.setGoods_group_count(rs.getInt("goods_group_count"));
				goodsVO.setGoods_want_count(rs.getInt("goods_want_count"));
				goodsVO.setGoods_sales_count(rs.getInt("goods_sales_count"));
				list.add(goodsVO);
			}

			System.out.println("----------getAll finished----------");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					ioe.printStackTrace(System.err);
				}
			}		
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
	public List<GoodsVO> getAllLaunched() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		GoodsVO goodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BufferedReader br = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_LAUNCHED);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				goodsVO = new GoodsVO();
				goodsVO.setGoods_no(rs.getString("goods_no"));
				goodsVO.setEvetit_no(rs.getString("evetit_no"));
				goodsVO.setGoods_name(rs.getString("goods_name"));
				goodsVO.setGoods_price(rs.getInt("goods_price"));
				goodsVO.setGoods_picture1(rs.getBytes("goods_picture1"));
				goodsVO.setGoods_picture2(rs.getBytes("goods_picture2"));
				goodsVO.setGoods_picture3(rs.getBytes("goods_picture3"));
				goodsVO.setGoods_introduction(rs.getString("goods_introduction"));
				goodsVO.setForsales_a(rs.getInt("Forsales_a"));
				goodsVO.setFavorite_count(rs.getInt("favorite_count"));
				goodsVO.setGoods_status(rs.getString("goods_status"));
				goodsVO.setLaunchdate(rs.getTimestamp("launchdate"));
				goodsVO.setOffdate(rs.getTimestamp("offdate"));
				goodsVO.setGoods_group_count(rs.getInt("goods_group_count"));
				goodsVO.setGoods_want_count(rs.getInt("goods_want_count"));
				goodsVO.setGoods_sales_count(rs.getInt("goods_sales_count"));
				list.add(goodsVO);
			}

			System.out.println("----------getAll finished----------");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
}