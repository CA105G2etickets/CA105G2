package com.GROUP_OPEN.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Group_openDAO implements Group_openDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Wilson";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO GROUP_OPEN VALUES('G'||LPAD(to_char(GROUP_NO_SEQ.NEXTVAL),4,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE GROUP_OPEN SET MEMBER_NO = ?, GOODS_NO = ?, GROUP_NAME = ?, GROUP_LIMIT = ?, GROUP_INTRODUCTION = ?,GROUP_MIND = ?, GROUP_START_DATE = ? ,GROUP_CLOSE_DATE = ?,GROUP_BANNER_1 = ?,GROUP_BANNER_2= ?, GROUP_STATUS= ? , GROUP_ADDRESS = ? ,LATITUDE = ?,LONGITUDE = ?,GROUP_TIME = ?, GROUP_PRICE = ? WHERE GROUP_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM GROUP_OPEN WHERE GROUP_NO = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM GROUP_OPEN WHERE GROUP_NO = ? ";
	private static final String GET_ALL_STMT = "select * from GROUP_OPEN";

	@Override
	// 開團紀錄新增
	public void add(Group_openVO Group_open) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Group_open.getMEMBER_NO());
			pstmt.setString(2, Group_open.getGOODS_NO());
			pstmt.setString(3, Group_open.getGROUP_NAME());
			pstmt.setInt(4, Group_open.getGROUP_LIMIT());
			pstmt.setString(5, Group_open.getGROUP_INTRODUCTION());
			pstmt.setString(6, Group_open.getGROUP_MIND());
			pstmt.setTimestamp(7, Group_open.getGROUP_START_DATE());
			pstmt.setTimestamp(8, Group_open.getGROUP_CLOSE_DATE());
			pstmt.setBytes(9, Group_open.getGROUP_BANNER_1());
			pstmt.setBytes(10, Group_open.getGROUP_BANNER_2());
			pstmt.setString(11, Group_open.getGROUP_STATUS());
			pstmt.setString(12, Group_open.getGROUP_ADDRESS());
			pstmt.setDouble(13, Group_open.getLATITUDE());
			pstmt.setDouble(14, Group_open.getLONGITUDE());
			pstmt.setTimestamp(15, Group_open.getGROUP_TIME());
			pstmt.setInt(16, Group_open.getGROUP_PRICE());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	// 修改開團紀錄
	public void update(Group_openVO Group_open) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, Group_open.getMEMBER_NO());
			pstmt.setString(2, Group_open.getGOODS_NO());
			pstmt.setString(3, Group_open.getGROUP_NAME());
			pstmt.setInt(4, Group_open.getGROUP_LIMIT());
			pstmt.setString(5, Group_open.getGROUP_INTRODUCTION());
			pstmt.setString(6, Group_open.getGROUP_MIND());
			pstmt.setTimestamp(7, Group_open.getGROUP_START_DATE());
			pstmt.setTimestamp(8, Group_open.getGROUP_CLOSE_DATE());
			pstmt.setBytes(9, Group_open.getGROUP_BANNER_1());
			pstmt.setBytes(10, Group_open.getGROUP_BANNER_2());
			pstmt.setString(11, Group_open.getGROUP_STATUS());
			pstmt.setString(12, Group_open.getGROUP_ADDRESS());
			pstmt.setDouble(13, Group_open.getLATITUDE());
			pstmt.setDouble(14, Group_open.getLONGITUDE());
			pstmt.setTimestamp(15, Group_open.getGROUP_TIME());
			pstmt.setInt(16, Group_open.getGROUP_PRICE());
			pstmt.setString(17, Group_open.getGROUP_NO());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
	}

	@Override
	// 刪除開團紀錄
	public void delete(String group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, group_no);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
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
	}

	@Override
	// 查詢指定欄位
	public Group_openVO findByPrimaryKey(String group_no) {
		Group_openVO grp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, group_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				grp = new Group_openVO();
				grp.setGROUP_NO(rs.getString("GROUP_NO"));
				grp.setMEMBER_NO(rs.getString("MEMBER_NO"));
				grp.setGOODS_NO(rs.getString("GOODS_NO"));
				grp.setGROUP_NAME(rs.getString("GROUP_NAME"));
				grp.setGROUP_LIMIT(rs.getInt("GROUP_LIMIT"));
				grp.setGROUP_INTRODUCTION(rs.getString("GROUP_INTRODUCTION"));
				grp.setGROUP_MIND(rs.getString("GROUP_MIND"));
				grp.setGROUP_START_DATE(rs.getTimestamp("GROUP_START_DATE"));
				grp.setGROUP_CLOSE_DATE(rs.getTimestamp("GROUP_CLOSE_DATE"));
				grp.setGROUP_BANNER_1(rs.getBytes("GROUP_BANNER_1"));
				grp.setGROUP_BANNER_2(rs.getBytes("GROUP_BANNER_2"));
				grp.setGROUP_STATUS(rs.getString("GROUP_STATUS"));
				grp.setGROUP_ADDRESS(rs.getString("GROUP_ADDRESS"));
				grp.setLATITUDE(rs.getDouble("LATITUDE"));
				grp.setLONGITUDE(rs.getDouble("LONGITUDE"));
				grp.setGROUP_TIME(rs.getTimestamp("GROUP_TIME"));
				grp.setGROUP_PRICE(rs.getInt("GROUP_PRICE"));
				
			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

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
		return grp;
	}

	@Override
	public List<Group_openVO> getAll() {
		List<Group_openVO> list = new ArrayList<>();
		
		Group_openVO grov = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();			

			while (rs.next()) {
				grov = new Group_openVO();
				grov.setGROUP_NO(rs.getString("GROUP_NO"));
				grov.setMEMBER_NO(rs.getString("MEMBER_NO"));
				grov.setGOODS_NO(rs.getString("GOODS_NO"));
				grov.setGROUP_NAME(rs.getString("GROUP_NAME"));
				grov.setGROUP_LIMIT(rs.getInt("GROUP_LIMIT"));
				grov.setGROUP_INTRODUCTION(rs.getString("GROUP_INTRODUCTION"));
				grov.setGROUP_MIND(rs.getString("GROUP_MIND"));
				grov.setGROUP_START_DATE(rs.getTimestamp("GROUP_START_DATE"));
				grov.setGROUP_CLOSE_DATE(rs.getTimestamp("GROUP_CLOSE_DATE"));
				grov.setGROUP_BANNER_1(rs.getBytes("GROUP_BANNER_1"));
				grov.setGROUP_BANNER_2(rs.getBytes("GROUP_BANNER_2"));
				grov.setGROUP_STATUS(rs.getString("GROUP_STATUS"));
				grov.setGROUP_ADDRESS(rs.getString("GROUP_ADDRESS"));
				grov.setLATITUDE(rs.getDouble("LATITUDE"));
				grov.setLONGITUDE(rs.getDouble("LONGITUDE"));
				grov.setGROUP_TIME(rs.getTimestamp("GROUP_TIME"));
				grov.setGROUP_PRICE(rs.getInt("GROUP_PRICE"));
				list.add(grov);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	public static void main(String[] args) throws IOException {

		Group_openDAO dao = new Group_openDAO();

		// 新增
//	Group_openVO grp1 = new Group_openVO();
//	grp1.setMEMBER_NO("M000008");
//	grp1.setGOODS_NO("A10003");
//	grp1.setGROUP_NAME("美國大豆精油蠟燭-酸甜滋味(330g/可燃燒80hr)");
//	grp1.setGROUP_LIMIT(10);
//	String stri = getLongString("items/BM.txt");
//	grp1.setGROUP_INTRODUCTION(stri);
//	String strm =getLongString("items/BM.txt");
//	grp1.setGROUP_MIND(strm);
//   Timestamp Timestamp = new Timestamp(System.currentTimeMillis());	
//   Format tsft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//   String time = tsft.format(Timestamp);   
//	grp1.setGROUP_START_DATE(Timestamp);
//	grp1.setGROUP_CLOSE_DATE(Timestamp);
//	byte [] pic1 = getPictureByteArray("items/FC_Barcelona.png");
//	byte [] pic2 = getPictureByteArray("items/FC_Bayern.png");
//	grp1.setGROUP_BANNER_1(pic1);
//	grp1.setGROUP_BANNER_2(pic2);
//	grp1.setGROUP_STATUS("fail2");
//	grp1.setGROUP_ADDRESS("桃園市桃園區中正路420號");
//	grp1.setLATITUDE(25.0003198);
//	grp1.setLONGITUDE(121.2997996);
//	grp1.setGROUP_TIME(Timestamp.valueOf("2018-12-07 13:00:48"));
//	grp1.setGROUP_PRICE(2000);
//	dao.add(grp1);
//	System.out.println("新增成功");

		// 修改
//	Group_openVO grp2 = new Group_openVO();
//	grp2.setGROUP_NO("G0002");
//	grp2.setMEMBER_NO("M000003");
//	grp2.setGOODS_NO("A10003");
//	grp2.setGROUP_NAME("美國大豆精油蠟燭-酸甜滋味(330g/可燃燒80hr)");
//	grp2.setGROUP_LIMIT(1000);
//	String stri2 = getLongString("items/BM.txt");
//	grp2.setGROUP_INTRODUCTION(stri2);
//	String strm2 =getLongString("items/BM.txt");
//	grp2.setGROUP_MIND(strm2);
//   Timestamp Timestamp2 = new Timestamp(System.currentTimeMillis());	
//   Format tsft2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//   String time2 = tsft2.format(Timestamp2);   
//	grp2.setGROUP_START_DATE(Timestamp2);
//	grp2.setGROUP_CLOSE_DATE(Timestamp2);
//	byte [] pic3 = getPictureByteArray("items/FC_Barcelona.png");
//	byte [] pic4 = getPictureByteArray("items/FC_Bayern.png");
//	grp2.setGROUP_BANNER_1(pic3);
//	grp2.setGROUP_BANNER_2(pic4);
//	grp2.setGROUP_STATUS("fail2");
//	grp2.setGROUP_ADDRESS("桃園市桃園區中正路420號");
//	grp2.setLATITUDE(25.0003198);
//	grp2.setLONGITUDE(121.2997996);
//	grp2.setGROUP_TIME(Timestamp.valueOf("2018-12-07 13:00:48"));
//	grp2.setGROUP_PRICE(2);
//
//	
//	dao.update(grp2);
//	System.out.println("修改成功");
//	

		// 刪除
//		dao.delete("G0006");
//		System.out.println("刪除完成");

		// 查詢
//		Group_openVO grov = dao.findByPrimaryKey("G0002");
//		System.out.println(grov.getGROUP_NO() + ",");
//		System.out.println(grov.getMEMBER_NO() + ",");
//		System.out.println(grov.getGOODS_NO() + ",");
//		System.out.println(grov.getGROUP_NAME() + ",");
//		System.out.println(grov.getGROUP_LIMIT() + ",");
//		System.out.println(grov.getGROUP_INTRODUCTION() + ",");
//		System.out.println(grov.getGROUP_MIND() + ",");
//		System.out.println(grov.getGROUP_START_DATE() + ",");
//		System.out.println(grov.getGROUP_CLOSE_DATE() + ",");
//		System.out.println(grov.getGROUP_BANNER_1() + ",");
//		System.out.println(grov.getGROUP_BANNER_2() + ",");
//		System.out.println(grov.getGROUP_ADDRESS() + ",");
//		System.out.println(grov.getGROUP_STATUS()+",");
//		System.out.println(grov.getLATITUDE() + ",");
//		System.out.println(grov.getLONGITUDE() + ",");
//		System.out.println(grov.getGROUP_TIME() + ",");
//		System.out.println(grov.getGROUP_PRICE());

		// 查詢
		List<Group_openVO> list = dao.getAll();
		for (Group_openVO gro1 : list) {
			System.out.println(gro1.getGROUP_NO() + ",");
			System.out.println(gro1.getMEMBER_NO() + ",");
			System.out.println(gro1.getGOODS_NO() + ",");
			System.out.println(gro1.getGROUP_NAME() + ",");
			System.out.println(gro1.getGROUP_LIMIT() + ",");
			System.out.println(gro1.getGROUP_INTRODUCTION() + ",");
			System.out.println(gro1.getGROUP_MIND() + ",");
			System.out.println(gro1.getGROUP_START_DATE() + ",");
			System.out.println(gro1.getGROUP_CLOSE_DATE() + ",");
			System.out.println(gro1.getGROUP_BANNER_1() + ",");
			System.out.println(gro1.getGROUP_BANNER_2() + ",");
			System.out.println(gro1.getGROUP_STATUS()+",");
			System.out.println(gro1.getGROUP_ADDRESS() + ",");
			System.out.println(gro1.getLATITUDE() + ",");
			System.out.println(gro1.getLONGITUDE() + ",");
			System.out.println(gro1.getGROUP_TIME() + ",");
			System.out.println(gro1.getGROUP_PRICE());
			System.out.println();
		}

	}

	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, 1);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

}
