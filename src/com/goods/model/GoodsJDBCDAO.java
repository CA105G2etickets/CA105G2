package com.goods.model;

import java.io.*;
import java.sql.*;
import java.util.*;

public class GoodsJDBCDAO implements GoodsDAO_interface {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
   
	private static final String INSERT_STMT = "INSERT INTO GOODS VALUES('P'||LPAD(TO_CHAR(GOODS_SEQ.NEXTVAL),7,'0'),? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )";

	private static final String GET_ALL_STMT = "SELECT * FROM GOODS ORDER BY GOODS_NO";
  
	private static final String GET_ONE_STMT = "SELECT * FROM GOODS WHERE GOODS_NO = ? ";

	private static final String DELETE_STMT = "DELETE FROM GOODS WHERE GOODS_NO = ?";

	private static final String UPDATE_STMT = "UPDATE GOODS SET EVETIT_NO=?, GOODS_NAME=?, GOODS_PRICE=?,  GOODS_PICTURE1=?, GOODS_PICTURE2=?, GOODS_PICTURE3=?, GOODS_INTRODUCTION=?, FORSALES_A=?, GOODS_STATUS=?, LAUNCHDATE=?, OFFDATE=? WHERE GOODS_NO=?";

	@Override
	public void insert(GoodsVO goodsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	public void update(GoodsVO goodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, goodsVO.getEvetit_no());
			pstmt.setString(2, goodsVO.getGoods_name());
			pstmt.setInt(3, goodsVO.getGoods_price());
			pstmt.setBytes(4, goodsVO.getGoods_picture1());
			pstmt.setBytes(5, goodsVO.getGoods_picture2());
			pstmt.setBytes(6, goodsVO.getGoods_picture3());
			pstmt.setString(7, goodsVO.getGoods_introduction());
			pstmt.setInt(8, goodsVO.getForsales_a());
//			pstmt.setInt(9, goodsVO.getFavorite_count());
			pstmt.setString(9, goodsVO.getGoods_status());
    		pstmt.setTimestamp(10, goodsVO.getLaunchdate());
			pstmt.setTimestamp(11, goodsVO.getOffdate());
//			pstmt.setInt(13, goodsVO.getGoods_group_count());
//			pstmt.setInt(14, goodsVO.getGoods_want_count());
//			pstmt.setInt(15, goodsVO.getGoods_sales_count());
			pstmt.setString(12, goodsVO.getGoods_no());
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
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

		// 新增
//		FileInputStream fis1 = null;
//		FileInputStream fis2 = null;
//		FileInputStream fis3 = null;
//		ByteArrayOutputStream baos1 = null;
//		ByteArrayOutputStream baos2 = null;
//		ByteArrayOutputStream baos3 = null;
//		try {
//			GoodsVO goodsVO1 = new GoodsVO();
//			goodsVO1.setEvetit_no("E0003");
//			goodsVO1.setGoods_name("Goods_name");
//			goodsVO1.setGoods_price(500);
//			
//			fis1 = new FileInputStream("writeImgJDBC/java.jpg"); // B
//			baos1 = new ByteArrayOutputStream();
//			int y;
//			while ((y = fis1.read()) != -1)
//				baos1.write(y);
//			goodsVO1.setGoods_picture1(baos1.toByteArray());
//			
//			fis2= new FileInputStream("writeImgJDBC/tomcat.jpg"); // B
//			baos2 = new ByteArrayOutputStream();
//			int i;
//			while ((i = fis2.read()) != -1)
//				baos2.write(i);
//			goodsVO1.setGoods_picture2(baos2.toByteArray());
//		
//			fis3 = new FileInputStream("writeImgJDBC/tomcat.jpg"); // B
//			baos3 = new ByteArrayOutputStream();
//			int j;
//			while ((j = fis3.read()) != -1)
//				baos3.write(j);
//			goodsVO1.setGoods_picture3(baos3.toByteArray());
//			
//			goodsVO1.setGoods_introduction("Goods_introduction");
//			goodsVO1.setForsales_a(400);
//			goodsVO1.setFavorite_count(2);
//			goodsVO1.setGoods_status("已上架");
//			goodsVO1.setLaunchdate(Timestamp.valueOf("2018-08-20 12:00:00"));
//			goodsVO1.setOffdate(Timestamp.valueOf("2018-09-01 10:00:00"));
//			goodsVO1.setGoods_group_count(1);
//			goodsVO1.setGoods_want_count(2);
//			goodsVO1.setGoods_sales_count(1);
//
//			dao.insert(goodsVO1);
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
		
		// 修改
		FileInputStream fis1 = null;
		ByteArrayOutputStream baos1 = null;	
		FileInputStream fis2 = null;
		ByteArrayOutputStream baos2 = null;	
		FileInputStream fis3 = null;
		ByteArrayOutputStream baos3 = null;	
		try {
			GoodsVO AgoodsVO = new GoodsVO();
			AgoodsVO.setGoods_no("P0000001");
			AgoodsVO.setEvetit_no("E0003");
			AgoodsVO.setGoods_name("UpdateTest");
			AgoodsVO.setGoods_price(100);
			fis1 = new FileInputStream("writeImgJDBC/java.jpg");			
			baos1 = new ByteArrayOutputStream();			
			int i;
			while ((i = fis1.read()) != -1)
				baos1.write(i);		
			AgoodsVO.setGoods_picture1(baos1.toByteArray());
			fis2 = new FileInputStream("writeImgJDBC/java.jpg");			
			baos2 = new ByteArrayOutputStream();			
			int j;
			while ((j = fis2.read()) != -1)
				baos2.write(j);		
			AgoodsVO.setGoods_picture2(baos2.toByteArray());

			fis3 = new FileInputStream("writeImgJDBC/java.jpg");			
			baos3 = new ByteArrayOutputStream();			
			int x;
			while ((x = fis3.read()) != -1)
				baos3.write(x);		
			
			AgoodsVO.setGoods_picture3(baos3.toByteArray());
			AgoodsVO.setGoods_introduction("UpdateTest");	
			AgoodsVO.setForsales_a(50);
			AgoodsVO.setFavorite_count(10);
			AgoodsVO.setGoods_status("Test");
			AgoodsVO.setLaunchdate(java.sql.Timestamp.valueOf("2018-10-22 12:00:00"));
			AgoodsVO.setOffdate(java.sql.Timestamp.valueOf("2018-12-22 12:00:00"));
			AgoodsVO.setGoods_group_count(10);
			AgoodsVO.setGoods_want_count(10);
			AgoodsVO.setGoods_sales_count(10);
			
		
			dao.update(AgoodsVO);
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			if (baos2 != null) {
				try {
					baos2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis2 != null) {
				try {
					fis2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

//		// 刪除
//		dao.delete("P0000005");
//		System.out.println("------------------------------");
//		

//		
//
//			 查詢一個
//		GoodsVO aGoodsVO = dao.findByPrimarykey("P0000018");
//		System.out.println(aGoodsVO.getGoods_no());
//		System.out.println(aGoodsVO.getEvetit_no());
//		System.out.println(aGoodsVO.getGoods_name());
//		System.out.println(aGoodsVO.getGoods_price());
//
//		try (PrintStream ps = new PrintStream(new FileOutputStream("readImgJDBC/eventTitleTest.jpg"), true)) {
//			if (aGoodsVO.getGoods_picture1() == null) {
//
//			} else {
//				ps.write(aGoodsVO.getGoods_picture1());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		try (PrintStream ps = new PrintStream(new FileOutputStream("readImgJDBC/eventTitleTest.jpg"), true)) {
//			if (aGoodsVO.getGoods_picture2() == null) {
//
//			} else {
//				ps.write(aGoodsVO.getGoods_picture2());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		try (PrintStream ps = new PrintStream(new FileOutputStream("readImgJDBC/eventTitleTest.jpg"), true)) {
//			if (aGoodsVO.getGoods_picture3() == null) {
//
//			} else {
//				ps.write(aGoodsVO.getGoods_picture3());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(aGoodsVO.getGoods_picture1());
//		System.out.println(aGoodsVO.getGoods_picture2());
//		System.out.println(aGoodsVO.getGoods_picture3());
//		System.out.println(aGoodsVO.getGoods_introduction());
//		System.out.println(aGoodsVO.getForsales_a());
//		System.out.println(aGoodsVO.getFavorite_count());
//		System.out.println(aGoodsVO.getGoods_status());
//		System.out.println(aGoodsVO.getLaunchdate());
//		System.out.println(aGoodsVO.getOffdate());
//		System.out.println(aGoodsVO.getGoods_group_count());
//		System.out.println(aGoodsVO.getGoods_want_count());
//		System.out.println(aGoodsVO.getGoods_sales_count());

//			 查詢全部
//		List<GoodsVO> list = dao.getAll();
//		for (GoodsVO aGoodsVO : list) {
//			System.out.println(aGoodsVO.getGoods_no());
//			System.out.println(aGoodsVO.getEvetit_no());
//			System.out.println(aGoodsVO.getGoods_name());
//			System.out.println(aGoodsVO.getGoods_price());
//			System.out.println(aGoodsVO.getGoods_picture1());
//			System.out.println(aGoodsVO.getGoods_picture2());
//			System.out.println(aGoodsVO.getGoods_picture3());
//			System.out.println(aGoodsVO.getGoods_introduction());
//			System.out.println(aGoodsVO.getForsales_a());
//			System.out.println(aGoodsVO.getFavorite_count());
//			System.out.println(aGoodsVO.getGoods_status());
//			System.out.println(aGoodsVO.getLaunchdate());
//			System.out.println(aGoodsVO.getOffdate());
//			System.out.println(aGoodsVO.getGoods_group_count());
//			System.out.println(aGoodsVO.getGoods_want_count());
//			System.out.println(aGoodsVO.getGoods_sales_count());
//		}

//	}
// 
//
//		public static byte[] getPictureByteArray(String path) throws IOException {
//			File file = new File(path);
//			FileInputStream fis = new FileInputStream(file);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			byte[] buffer = new byte[8192];
//			int i;
//			while ((i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, 1);
//			}
//			baos.close();
//			fis.close();
//
//			return baos.toByteArray();
//		}
//
	}

	@Override
	public List<GoodsVO> getAllLaunched(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GoodsVO> getAllLaunched() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<GoodsVO> getEventsByEventTitle(String evetit_no) {
		// TODO Auto-generated method stub
		return null;
	}
}