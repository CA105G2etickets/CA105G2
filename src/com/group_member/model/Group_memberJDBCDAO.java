package com.group_member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.group_open.model.Group_openVO;

public class Group_memberJDBCDAO implements Group_memberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "Wilson";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO GROUP_MEMBER (MEMBER_NO ,GROUP_NO ,JOIN_TIME ,PRODUCT_QUANTITY ,PAY_STATUS ,GROUP_MEMBER_STATUS ,LOG_OUT_REASON ,ORDER_PHONE ,PAY_METHODS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select * from group_member";
	private static final String GET_ONE_STMT = "SELECT * FROM GROUP_MEMBER where MEMBER_NO = ?";
	private static final String DELETE = "DELETE FROM GROUP_MEMBER where MEMBER_NO = ?";
	private static final String UPDATE = "UPDATE GROUP_MEMBER set GROUP_NO = ?, JOIN_TIME=?, PRODUCT_QUANTITY=?, PAY_STATUS=?, GROUP_MEMBER_STATUS=?, LOG_OUT_REASON=?,ORDER_PHONE=?,PAY_METHODS=? where  MEMBER_NO = ? ";
	
	
	
	@Override
	public void add(Group_memberVO group_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_memberVO.getMember_no());
			pstmt.setString(2, group_memberVO.getGroup_no());
			pstmt.setTimestamp(3, group_memberVO.getJoin_time());
			pstmt.setInt(4, group_memberVO.getProduct_quantity());
			pstmt.setString(5, group_memberVO.getPay_status());
			pstmt.setString(6, group_memberVO.getGroup_member_status());
			pstmt.setString(7, group_memberVO.getLog_out_reason());
			pstmt.setString(8, group_memberVO.getOrder_phone());
			pstmt.setString(9, group_memberVO.getPay_method());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(String member_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public List<Group_memberVO> getAll() {
		List<Group_memberVO> list = new ArrayList<Group_memberVO>();
		Group_memberVO grmvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱?�� Domain objects
				grmvo.setMember_no(rs.getString("MEMBER_NO"));
				grmvo.setGroup_no(rs.getString("GROUP_NO"));
				grmvo.setJoin_time(rs.getTimestamp("JOIN_TIME"));
				grmvo.setProduct_quantity(rs.getInt("PRODUCT_QUANTITY"));
				grmvo.setPay_status(rs.getString("PAY_STATUS"));
				grmvo.setGroup_member_status(rs.getString("GROUP_MEMBER_STATUS"));
				grmvo.setLog_out_reason(rs.getString("LOG_OUT_REASON"));
				grmvo.setOrder_phone(rs.getString("ORDER_PHONE"));
				grmvo.setPay_method(rs.getString("PAY_METHODS"));
				list.add(grmvo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	
	
		
		
		
		
		
		
	
	
	
	
	
	public static void main(String[] args) {
		Group_memberDAO dao = new Group_memberDAO();
//		
		//?���?
//		Group_MemberVO Group_MemberVO1 = new Group_MemberVO();
//		
//		Group_MemberVO1.setMEMBER_NO("M000009");
//		Group_MemberVO1.setGROUP_NO("G0003");
//		Group_MemberVO1.setJOIN_TIME(Timestamp.valueOf("2018-12-11 18:00:00"));
//		Group_MemberVO1.setPRODUCT_QUANTITY(5);
//		Group_MemberVO1.setPAY_STATUS("COMPLETE2");
//		Group_MemberVO1.setGROUP_MEMBER_STATUS("withgroup");
//		Group_MemberVO1.setLOG_OUT_REASON(" ");
//		Group_MemberVO1.setORDER_PHONE(937485997);
//		Group_MemberVO1.setPAY_METHODS("EWALLET");
//		
//		dao.add(Group_MemberVO1);
//		System.out.println("?��增�?��??");
		
	    //修改 
//		Group_MemberVO Group_MemberVO2 = new Group_MemberVO();
//		Group_MemberVO2.setMEMBER_NO("M000002");
//		Group_MemberVO2.setGROUP_NO("G0001");
//		Group_MemberVO2.setJOIN_TIME(Timestamp.valueOf("2018-12-11 18:00:00"));
//		Group_MemberVO2.setPRODUCT_QUANTITY(100000);
//		Group_MemberVO2.setPAY_STATUS("COMPLETE2");
//		Group_MemberVO2.setGROUP_MEMBER_STATUS("withgroup");
//		Group_MemberVO2.setLOG_OUT_REASON(" ");
//		Group_MemberVO2.setORDER_PHONE(937485997);
//		Group_MemberVO2.setPAY_METHODS("CREDITCARD");
//		
//		dao.update(Group_MemberVO2);
//		System.out.println("修改完�??");
		
		//?��?��
//		dao.delete("M000009");
//		System.out.println("?��?��完�??");
		
		
		//查詢
//		Group_memberVO grm2 = dao.findByPrimaryKey("M000001");
//		System.out.println(grm2.getMember_no()+",");
//		System.out.println(grm2.getGroup_no()+",");
//		System.out.println(grm2.getJoin_time()+",");
//		System.out.println(grm2.getProduct_quantity()+",");
//		System.out.println(grm2.getPay_status()+",");
//		System.out.println(grm2.getGroup_member_status()+",");
//		System.out.println(grm2.getLog_out_reason()+",");
//		System.out.println(grm2.getOrder_phone()+",");
//		System.out.println(grm2.getPay_method()+",");
		
		//?���?
		List<Group_memberVO> list = dao.getAll();
		for(Group_memberVO grm : list) {
			System.out.println(grm.getMember_no()+",");
			System.out.println(grm.getGroup_no()+",");
			System.out.println(grm.getJoin_time()+",");
			System.out.println(grm.getProduct_quantity()+",");
			System.out.println(grm.getPay_method()+",");
			System.out.println(grm.getGroup_member_status()+",");
			System.out.println(grm.getLog_out_reason()+",");
			System.out.println(grm.getOrder_phone()+",");
			System.out.println(grm.getPay_method()+",");	
			System.out.println();
			
		}
	
		
		
			}

	@Override
	public String getproductquantity(String group_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group_memberVO> getmember_BY_group_member() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<Group_memberVO> getgroup_BY_member_no(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add2(Group_memberVO group_memberVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> getgroup_quantity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Group_memberVO group_memberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Group_memberVO findByPrimaryKey(String member_no, String group_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void change_quit(Group_memberVO group_memberVO) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getemail(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void sendMail(String to, String messageText) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void allgroup_member_quit(String group_no) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Group_memberVO> getall_member_dimiss(String group_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getgroup_member_product(String group_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group_memberVO> getgroupsucesslist(String group_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Integer getewallet(String member_no) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void updateewallet(Integer ewalletBalance, String member_no) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Group_memberVO> getquitgroup_member(String group_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}