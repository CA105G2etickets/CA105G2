package com.group_open.model;

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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.group_member.model.Group_memberDAO;
import com.group_member.model.Group_memberVO;

public class Group_openDAO implements Group_openDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_OPEN VALUES('G'||LPAD(to_char(GROUP_NO_SEQ.NEXTVAL),4,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE GROUP_OPEN SET MEMBER_NO = ?, GOODS_NO = ?, GROUP_NAME = ?, GROUP_LIMIT = ?, GROUP_INTRODUCTION = ?,GROUP_MIND = ?, GROUP_START_DATE = ? ,GROUP_CLOSE_DATE = ?,GROUP_BANNER_1 = ?,GROUP_BANNER_2= ?, GROUP_STATUS= ? , GROUP_ADDRESS = ? ,LATITUDE = ?,LONGITUDE = ?,GROUP_TIME = ?, GROUP_PRICE = ? WHERE GROUP_NO = ?";
	private static final String DELETE_STMT = "DELETE FROM GROUP_OPEN WHERE GROUP_NO = ?";
	private static final String DELETE_STMTGROUP_MEMBER = "DELETE FROM GROUP_MEMBER WHERE GROUP_NO = ?";
	
	private static final String GET_ONE_STMT = "SELECT * FROM GROUP_OPEN WHERE GROUP_NO = ? ";
	private static final String GET_ALL_STMT = "select * from GROUP_OPEN";

	private static final String GET_Group_open_ByGroup_no_STMT = "SELECT member_no,group_no,join_time, product_quantity ,pay_status,group_member_status,log_out_reason,order_phone,pay_methods FROM group_member where group_no = ? order by member_no";
	private static final String GET_Group_open_ByMember_no_STMT = "select *  from group_open  where member_no = ? ";
	private static final String GET_ALL_Member_BYGroup_open_STMT = "SELECT DISTINCT member_no FROM group_open ORDER BY member_no";
	private static final String GET_ALL_MEMBER_BY_GROUP = "select GROUP_NO , count(*) as  GROUP_MEMBER_COUNT  from GROUP_MEMBER  group by GROUP_NO order by GROUP_NO";
	private static final String GET_GROUP_OPEN_MEMBER_NO = "select DISTINCT  member_no from group_open where group_no = ?";
//	@Override
	// 新增
	public void add(Group_openVO group_openVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
		
						
			//先新增開團
	
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, group_openVO.getMember_no());
			pstmt.setString(2, group_openVO.getGoods_no());
			pstmt.setString(3, group_openVO.getGroup_name());
			pstmt.setInt(4, group_openVO.getGroup_limit());
			pstmt.setString(5, group_openVO.getGroup_introduction());
			pstmt.setString(6, group_openVO.getGroup_mind());
			pstmt.setTimestamp(7, group_openVO.getGroup_start_date());
			pstmt.setTimestamp(8, group_openVO.getGroup_close_date());
			pstmt.setBytes(9, group_openVO.getGroup_banner_1());
			pstmt.setBytes(10, group_openVO.getGroup_banner_2());
			pstmt.setString(11, group_openVO.getGroup_status());
			pstmt.setString(12, group_openVO.getGroup_address());
			pstmt.setDouble(13, group_openVO.getLatitude());
			pstmt.setDouble(14, group_openVO.getLongitude());
			pstmt.setTimestamp(15, group_openVO.getGroup_time());
			pstmt.setInt(16, group_openVO.getGroup_price());

			pstmt.executeUpdate();		

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("DAO新增失敗");
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
	// 修改
	public void update(Group_openVO group_openVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, group_openVO.getMember_no());
			pstmt.setString(2, group_openVO.getGoods_no());
			pstmt.setString(3, group_openVO.getGroup_name());
			pstmt.setInt(4, group_openVO.getGroup_limit());
			pstmt.setString(5, group_openVO.getGroup_introduction());
			pstmt.setString(6, group_openVO.getGroup_mind());
			pstmt.setTimestamp(7, group_openVO.getGroup_start_date());
			pstmt.setTimestamp(8, group_openVO.getGroup_close_date());
			pstmt.setBytes(9, group_openVO.getGroup_banner_1());
			pstmt.setBytes(10, group_openVO.getGroup_banner_2());
			pstmt.setString(11, group_openVO.getGroup_status());
			pstmt.setString(12, group_openVO.getGroup_address());
			pstmt.setDouble(13, group_openVO.getLatitude());
			pstmt.setDouble(14, group_openVO.getLongitude());
			pstmt.setTimestamp(15, group_openVO.getGroup_time());
			pstmt.setInt(16, group_openVO.getGroup_price());
			pstmt.setString(17, group_openVO.getGroup_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	// 刪除
	public void delete(String group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, group_no);

			pstmt.executeUpdate();

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
	//給開團編號找一組開團
	public Group_openVO findByPrimaryKey(String group_no) {
		Group_openVO grp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				grp = new Group_openVO();
				grp.setGroup_no(rs.getString("GROUP_NO"));
				grp.setMember_no(rs.getString("MEMBER_NO"));
				grp.setGoods_no(rs.getString("GOODS_NO"));
				grp.setGroup_name(rs.getString("GROUP_NAME"));
				grp.setGroup_limit(rs.getInt("GROUP_LIMIT"));
				grp.setGroup_introduction(rs.getString("GROUP_INTRODUCTION"));
				grp.setGroup_mind(rs.getString("GROUP_MIND"));
				grp.setGroup_start_date(rs.getTimestamp("GROUP_START_DATE"));
				grp.setGroup_close_date(rs.getTimestamp("GROUP_CLOSE_DATE"));
				grp.setGroup_banner_1(rs.getBytes("GROUP_BANNER_1"));
				grp.setGroup_banner_2(rs.getBytes("GROUP_BANNER_2"));
				grp.setGroup_status(rs.getString("GROUP_STATUS"));
				grp.setGroup_address(rs.getString("GROUP_ADDRESS"));
				grp.setLatitude(rs.getDouble("LATITUDE"));
				grp.setLongitude(rs.getDouble("LONGITUDE"));
				grp.setGroup_time(rs.getTimestamp("GROUP_TIME"));
				grp.setGroup_price(rs.getInt("GROUP_PRICE"));
			}

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
		return grp;
	}

	@Override
	public List<Group_openVO> getAll() {
		List<Group_openVO> list = new ArrayList<>();

		Group_openVO grp = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				grp = new Group_openVO();
				grp.setGroup_no(rs.getString("GROUP_NO"));
				grp.setMember_no(rs.getString("MEMBER_NO"));
				grp.setGoods_no(rs.getString("GOODS_NO"));
				grp.setGroup_name(rs.getString("GROUP_NAME"));
				grp.setGroup_limit(rs.getInt("GROUP_LIMIT"));
				grp.setGroup_introduction(rs.getString("GROUP_INTRODUCTION"));
				grp.setGroup_mind(rs.getString("GROUP_MIND"));
				grp.setGroup_start_date(rs.getTimestamp("GROUP_START_DATE"));
				grp.setGroup_close_date(rs.getTimestamp("GROUP_CLOSE_DATE"));
				grp.setGroup_banner_1(rs.getBytes("GROUP_BANNER_1"));
				grp.setGroup_banner_2(rs.getBytes("GROUP_BANNER_2"));
				grp.setGroup_status(rs.getString("GROUP_STATUS"));
				grp.setGroup_address(rs.getString("GROUP_ADDRESS"));
				grp.setLatitude(rs.getDouble("LATITUDE"));
				grp.setLongitude(rs.getDouble("LONGITUDE"));
				grp.setGroup_time(rs.getTimestamp("GROUP_TIME"));
				grp.setGroup_price(rs.getInt("GROUP_PRICE"));
				list.add(grp);
		
			}

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

	public Set<Group_memberVO> getGroup_memberByGroup_no(String group_no) {
		Set<Group_memberVO> set = new LinkedHashSet<Group_memberVO>();
		Group_memberVO group_memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Group_open_ByGroup_no_STMT);
			pstmt.setString(1, group_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_memberVO = new Group_memberVO();
				group_memberVO.setMember_no(rs.getString("MEMBER_NO"));
				group_memberVO.setGroup_no(rs.getString("GROUP_NO"));
				group_memberVO.setJoin_time(rs.getTimestamp("JOIN_TIME"));
				group_memberVO.setProduct_quantity(rs.getInt("PRODUCT_QUANTITY"));
				group_memberVO.setPay_status(rs.getString("PAY_STATUS"));
				group_memberVO.setGroup_member_status(rs.getString("GROUP_MEMBER_STATUS"));
				group_memberVO.setLog_out_reason(rs.getString("LOG_OUT_REASON"));
				group_memberVO.setOrder_phone(rs.getString("ORDER_PHONE"));
				group_memberVO.setPay_method(rs.getString("PAY_METHODS"));
				set.add(group_memberVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return set;
	}

	public List<Group_openVO> getgroup_openBymember_no(String member_no) {
		List<Group_openVO> list = new ArrayList<Group_openVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Group_openVO group_openVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Group_open_ByMember_no_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_openVO = new Group_openVO();
				group_openVO.setGroup_no(rs.getString("GROUP_NO"));
				group_openVO.setMember_no(rs.getString("MEMBER_NO"));
				group_openVO.setGoods_no(rs.getString("GOODS_NO"));
				group_openVO.setGroup_name(rs.getString("GROUP_NAME"));
				group_openVO.setGroup_limit(rs.getInt("GROUP_LIMIT"));
				group_openVO.setGroup_introduction(rs.getString("GROUP_INTRODUCTION"));
				group_openVO.setGroup_mind(rs.getString("GROUP_MIND"));
				group_openVO.setGroup_start_date(rs.getTimestamp("GROUP_START_DATE"));
				group_openVO.setGroup_close_date(rs.getTimestamp("GROUP_CLOSE_DATE"));
				group_openVO.setGroup_banner_1(rs.getBytes("GROUP_BANNER_1"));
				group_openVO.setGroup_banner_2(rs.getBytes("GROUP_BANNER_2"));
				group_openVO.setGroup_status(rs.getString("GROUP_STATUS"));
				group_openVO.setGroup_address(rs.getString("GROUP_ADDRESS"));
				group_openVO.setLatitude(rs.getDouble("LATITUDE"));
				group_openVO.setLongitude(rs.getDouble("LONGITUDE"));
				group_openVO.setGroup_time(rs.getTimestamp("GROUP_TIME"));
				group_openVO.setGroup_price(rs.getInt("GROUP_PRICE"));
				list.add(group_openVO);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
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

	public List<Group_openVO> getall_member_BYgroup_open() {
		List<Group_openVO> list = new ArrayList<Group_openVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Group_openVO group_openVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_Member_BYGroup_open_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_openVO = new Group_openVO();
				group_openVO.setMember_no(rs.getString("MEMBER_NO"));
				list.add(group_openVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	   // 同時新增 開團者跟跟團者
	public void add2(Group_openVO group_openVO, Group_memberVO group_memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增開團
			String cols[] = {"GROUP_NO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
//			columnNames - 一個列名稱數組，指示應從插入的行返回的列
			pstmt.setString(1, group_openVO.getMember_no());
			pstmt.setString(2, group_openVO.getGoods_no());
			pstmt.setString(3, group_openVO.getGroup_name());
			pstmt.setInt(4, group_openVO.getGroup_limit());
			pstmt.setString(5, group_openVO.getGroup_introduction());
			pstmt.setString(6, group_openVO.getGroup_mind());
			pstmt.setTimestamp(7, group_openVO.getGroup_start_date());
			pstmt.setTimestamp(8, group_openVO.getGroup_close_date());
			pstmt.setBytes(9, group_openVO.getGroup_banner_1());
			pstmt.setBytes(10, group_openVO.getGroup_banner_2());
			pstmt.setString(11, group_openVO.getGroup_status());
			pstmt.setString(12, group_openVO.getGroup_address());
			pstmt.setDouble(13, group_openVO.getLatitude());
			pstmt.setDouble(14, group_openVO.getLongitude());
			pstmt.setTimestamp(15, group_openVO.getGroup_time());
			pstmt.setInt(16, group_openVO.getGroup_price());
			System.out.println("Group_openDAO");
			pstmt.executeUpdate();
			// 取的自增主鍵值
			String next_group_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_group_no = rs.getString(1);
				System.out.println("成功取得自增主見值" + next_group_no);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 同時新增開團者進入跟團表單

			Group_memberDAO group_memberdao = new Group_memberDAO();
		
			group_memberVO.setGroup_no(next_group_no);
			group_memberVO.setMember_no(group_openVO.getMember_no());
			
			group_memberdao.add2(group_memberVO, con);
		

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("開團人被新增了");

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	  public Map<String, Integer> getmember_count() {
			Map<String, Integer> map = new HashMap<>();

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_MEMBER_BY_GROUP);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					map.put(rs.getString("GROUP_NO"), rs.getInt("GROUP_MEMBER_COUNT"));
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}finally {
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

			return map;
		}
	  @Override
	  public void delete2(String group_no) {
			int updatecount = 0;
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = ds.getConnection();
				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(true);
				// 先刪除跟團人
				pstmt = con.prepareStatement(DELETE_STMTGROUP_MEMBER);
				pstmt.setString(1, group_no);
				updatecount = pstmt.executeUpdate();
				// 再刪除開團
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setString(1, group_no);
				pstmt.executeUpdate();

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("刪除開團編號" + group_no + "時，共有" + updatecount + "人被同時刪除");

			} catch (SQLException e) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. " + excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. " + e.getMessage());
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
	  public String getgroup_open_member_no(String group_no) {

			String group_memberno = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt=con.prepareStatement(GET_GROUP_OPEN_MEMBER_NO);
				pstmt.setString(1, group_no);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					group_memberno = rs.getString(1);
				} else {
					System.out.println("取不到值");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
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

			return group_memberno;
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
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();

		return buffer;
	}



	

}
