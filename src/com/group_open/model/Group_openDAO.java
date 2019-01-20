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
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.event_title.model.EventTitleVO;
import com.goods.model.GoodsVO;
import com.group_member.model.Group_memberDAO;
import com.group_member.model.Group_memberVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_group_open;

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
	private static final String GET_ALL_STMT = "select * from GROUP_OPEN order by GROUP_CLOSE_DATE";

	private static final String GET_Group_open_ByGroup_no_STMT = "SELECT member_no,group_no,join_time, product_quantity ,pay_status,group_member_status,log_out_reason,order_phone,pay_methods FROM group_member where group_no = ? order by member_no";
	private static final String GET_Group_open_ByMember_no_STMT = "select *  from group_open  where member_no = ? ";
	private static final String GET_ALL_Member_BYGroup_open_STMT = "SELECT DISTINCT member_no FROM group_open ORDER BY member_no";
	private static final String GET_ALL_MEMBER_BY_GROUP = "select GROUP_NO , count(*) as  GROUP_MEMBER_COUNT  from GROUP_MEMBER  group by GROUP_NO order by GROUP_NO";
	private static final String GET_GROUP_OPEN_MEMBER_NO = "select DISTINCT  member_no from group_open where group_no = ?";
	private static final String GROUP_OPEN_QUIT = "UPDATE GROUP_OPEN set GROUP_STATUS = 'fail2' where GROUP_NO = ? ";
	private static final String GROUP_OPEN_SUCESS = "UPDATE GROUP_OPEN set GROUP_STATUS = 'sucess3' where GROUP_NO = ? ";
	private static final String GROUP_SALESPRICE = "select FORSALES_A from group_open right join goods on group_open.goods_no = goods.goods_no where group_open.goods_no = ?";
//	private static final String GETEVENTITLE_GOODS = "select event_title.evetit_name,goods.goods_name from event_title right join goods on event_title.evetit_no = goods.evetit_no where event_title.evetit_no = ?";
	private static final String GETEVENTITLE_GOODS = "select goods.goods_no,goods.goods_name from event_title right join goods on event_title.evetit_no = goods.evetit_no where event_title.evetit_no = ?";
	private static final String GETEVENTITLE = "select DISTINCT event_title.evetit_no,event_title.evetit_name from event_title right join goods on event_title.evetit_no = goods.evetit_no ";
	private static final String GROUP_OPEN_COMPLETE = "UPDATE GROUP_OPEN set GROUP_STATUS = 'finish4' where GROUP_NO = ? ";
	private static final String FAVORITE = "select * from (select goods_no,count(*) as goods_count from favorite_goods group by goods_no order by goods_count desc) where rownum<=3";
	private static final String GROUP_GOODS = "select * from event_title right join goods on event_title.evetit_no = goods.evetit_no where goods.evetit_no = ?";
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
	  //開團失敗
	  public void group_open_quit(String group_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GROUP_OPEN_QUIT);
				
				pstmt.setString(1, group_no);
				
				rs = pstmt.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
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
			
		}
	  //開團成功
	  public void group_open_sucess(String group_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				
				pstmt = con.prepareStatement(GROUP_OPEN_SUCESS);
				
				pstmt.setString(1, group_no);
				
				rs = pstmt.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs!=null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
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
			
		}
	  public String getgroup_price(String goods_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String price = null;

			try {
				con = ds.getConnection();

				pstmt = con.prepareStatement(GROUP_SALESPRICE);
				
				System.out.println("Group_openServlet"+goods_no);
				pstmt.setString(1, goods_no);
				System.out.println(goods_no);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					price = rs.getString("FORSALES_A");
				} else {
					System.out.println("xxxxxxxx");
					System.out.println("Group_price取不到值");
				}

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
			return price;
		}
	  @Override
	  public List<Group_openVO> getcompoundsearch(Map<String,String[]>map){
		  List<Group_openVO> list = new ArrayList<Group_openVO>();
		  Group_openVO group_openVO = null;
		  
		  Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				
				String finalSQL = "select * from event_title right join (select * from group_open left join goods on group_open.goods_no = goods.goods_no order by group_no) group_goods on event_title.evetit_no = group_goods.evetit_no"
									+ jdbcUtil_CompositeQuery_group_open.get_WhereCondition(map)
									+" order by group_no";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("finalSQL(byDAO) = " +finalSQL);
				rs=pstmt.executeQuery();
				
				System.out.println("DAO測試到while885");
				
				while(rs.next()) {
					System.out.println("DAO測試到while888");
					group_openVO = new Group_openVO();
					group_openVO.setGroup_no(rs.getString("GROUP_NO"));
					System.out.println(rs.getString("GROUP_NO"));
					group_openVO.setMember_no(rs.getString("MEMBER_NO"));
					System.out.println(rs.getString("MEMBER_NO"));
					group_openVO.setGoods_no(rs.getString("QCSJ_C000000000500000"));
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
					
					System.out.println("Group_openDAO測試"+rs.getString("GROUP_NO"));
					System.out.println("Group_openDAO測試"+rs.getString("MEMBER_NO"));
					System.out.println("Group_openDAO測試"+rs.getDouble("LONGITUDE"));
					System.out.println("Group_openDAO測試"+rs.getString("GROUP_NAME"));
				}			
			}catch(SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			
	  public Map<String,String> getevetitle_goods(String evetit_no) {
			Map<String, String> map = new IdentityHashMap<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GETEVENTITLE_GOODS);
				
				pstmt.setString(1,evetit_no);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					map.put(rs.getString("GOODS_NO"),rs.getString("GOODS_NAME"));	
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
	  public List <EventTitleVO> geteventitle(){
		  
		  List<EventTitleVO> list = new ArrayList<>();
		  EventTitleVO eventTitleVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GETEVENTITLE);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					eventTitleVO = new EventTitleVO();
					eventTitleVO.setEvetit_no(rs.getString("evetit_no"));
					eventTitleVO.setEvetit_name(rs.getString("evetit_name"));
					list.add(eventTitleVO);
				}
			
			}catch(Exception e) {
				e.printStackTrace(System.err);
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
	  public void group_complete(String group_no) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GROUP_OPEN_COMPLETE);
				
				pstmt.setString(1, group_no);
				
				pstmt.executeQuery();
		
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
			
		}
	  public Map<String, Integer> getfavorite() {
			Map<String, Integer> map = new LinkedHashMap<>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();

				pstmt = con.prepareStatement(FAVORITE);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					map.put(rs.getString("GOODS_NO"), rs.getInt("GOODS_COUNT"));
				}

			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
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
	  public List<GoodsVO> getgroup_goods(String evetit_no) {
			
			List<GoodsVO> list = new ArrayList<GoodsVO>();
			
			GoodsVO goodsVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GROUP_GOODS);
				pstmt.setString(1,evetit_no);
				
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
					goodsVO.setForsales_a(rs.getInt("Forsales_a"));
					goodsVO.setFavorite_count(rs.getInt("Favorite_count"));
					goodsVO.setGoods_status(rs.getString("Goods_status"));
					goodsVO.setLaunchdate(rs.getTimestamp("Launchdate"));
					goodsVO.setOffdate(rs.getTimestamp("Offdate"));
					goodsVO.setGoods_group_count(rs.getInt("Goods_group_count"));
					goodsVO.setGoods_want_count(rs.getInt("Goods_want_count"));
					goodsVO.setGoods_sales_count(rs.getInt("Goods_sales_count"));
					list.add(goodsVO);				
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
