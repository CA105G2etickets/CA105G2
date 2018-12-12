package com.resaleorder.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticketorder.model.TicketOrderDAOJDBC;
import com.ticketorder.model.TicketOrderVO;

public class ResaleOrderDAOJDBC implements ResaleOrderDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";
	private static final String INSERT_STMT=
			"INSERT INTO resale_ord (resale_ordno,ticket_no,member_seller_no,member_buyer_no,resale_ordprice,resale_ordstatus,resale_ord_createtime,resale_ord_completetime,payment_method) VALUES (resale_ord_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT resale_ordno,ticket_no,member_seller_no,member_buyer_no,resale_ordprice,resale_ordstatus,resale_ord_createtime,resale_ord_completetime,payment_method FROM resale_ord order by resale_ordno";
	private static final String GET_ONE_STMT=
			"SELECT resale_ordno,ticket_no,member_seller_no,member_buyer_no,resale_ordprice,resale_ordstatus,resale_ord_createtime,resale_ord_completetime,payment_method FROM resale_ord where resale_ordno=?";
	private static final String DELETE = 
			"DELETE FROM resale_ord where resale_ordno = ?";
	private static final String UPDATE = 
			"UPDATE resale_ord set ticket_no=?, member_seller_no=?, member_buyer_no=?, resale_ordprice=?, resale_ordstatus=?, resale_ord_createtime=?,resale_ord_completetime=?,payment_method=? where resale_ordno=?";

	@Override
	public void insert(ResaleOrderVO resaleorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, resaleorderVO.getTicket_no());
			pstmt.setString(2, resaleorderVO.getMember_seller_no());
			pstmt.setString(3, resaleorderVO.getMember_buyer_no());
			pstmt.setInt(4, resaleorderVO.getResale_ordprice());
			pstmt.setString(5, resaleorderVO.getResale_ordstatus());
			pstmt.setTimestamp(6, resaleorderVO.getResale_ord_createtime());
			pstmt.setTimestamp(7, resaleorderVO.getResale_ord_completetime());
			pstmt.setString(8, resaleorderVO.getPayment_method());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(ResaleOrderVO resaleorderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, resaleorderVO.getTicket_no());
			pstmt.setString(2, resaleorderVO.getMember_seller_no());
			pstmt.setString(3, resaleorderVO.getMember_buyer_no());
			pstmt.setInt(4, resaleorderVO.getResale_ordprice());
			pstmt.setString(5, resaleorderVO.getResale_ordstatus());
			pstmt.setTimestamp(6, resaleorderVO.getResale_ord_createtime());
			pstmt.setTimestamp(7, resaleorderVO.getResale_ord_completetime());
			pstmt.setString(8, resaleorderVO.getPayment_method());
			pstmt.setString(9, resaleorderVO.getResale_ordno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void delete(String resale_ordno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, resale_ordno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ResaleOrderVO findByPrimaryKey(String resale_ordno) {
		ResaleOrderVO resaleorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, resale_ordno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				resaleorderVO = new ResaleOrderVO();
				resaleorderVO.setResale_ordno(rs.getString("resale_ordno"));
				resaleorderVO.setTicket_no(rs.getString("ticket_no"));
				resaleorderVO.setMember_seller_no(rs.getString("member_seller_no"));
				resaleorderVO.setMember_buyer_no(rs.getString("member_buyer_no"));
				resaleorderVO.setResale_ordprice(rs.getInt("resale_ordprice"));
				resaleorderVO.setResale_ordstatus(rs.getString("resale_ordstatus"));
				resaleorderVO.setResale_ord_createtime(rs.getTimestamp("resale_ord_createtime"));
				resaleorderVO.setResale_ord_completetime(rs.getTimestamp("resale_ord_completetime"));
				resaleorderVO.setPayment_method(rs.getString("payment_method"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return resaleorderVO;
	}

	@Override
	public List<ResaleOrderVO> getAll() {
		List<ResaleOrderVO> list = new ArrayList<ResaleOrderVO>();
		ResaleOrderVO resaleorderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				resaleorderVO = new ResaleOrderVO();
				resaleorderVO.setResale_ordno(rs.getString("resale_ordno"));
				resaleorderVO.setTicket_no(rs.getString("ticket_no"));
				resaleorderVO.setMember_seller_no(rs.getString("member_seller_no"));
				resaleorderVO.setMember_buyer_no(rs.getString("member_buyer_no"));
				resaleorderVO.setResale_ordprice(rs.getInt("resale_ordprice"));
				resaleorderVO.setResale_ordstatus(rs.getString("resale_ordstatus"));
				resaleorderVO.setResale_ord_createtime(rs.getTimestamp("resale_ord_createtime"));
				resaleorderVO.setResale_ord_completetime(rs.getTimestamp("resale_ord_completetime"));
				resaleorderVO.setPayment_method(rs.getString("payment_method"));
				list.add(resaleorderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public static void main (String[] args) {
		ResaleOrderDAOJDBC dao = new ResaleOrderDAOJDBC();
		//�s�W
//		dao.insert(ticketorderVO);
		
		//�ק�
//		dao.update(ticketorderVO2);
		
		//�R��
//		dao.delete("ticket_order_no"); //����ݭn��ʿ�J
		
		//�d��
		System.out.println("------select1 start--------");
		
		System.out.println("---------------------");
		
		//�d�� list
		List<ResaleOrderVO> list = dao.getAll();
		for(ResaleOrderVO aVO :list) {
			System.out.println("------select2 start--------");
			System.out.print(aVO.getResale_ordno() + ",");
			System.out.print(aVO.getTicket_no() + ",");
			System.out.print(aVO.getMember_seller_no() + ",");
			System.out.print(aVO.getMember_buyer_no() + ",");
			System.out.print(aVO.getResale_ordprice() + ",");
			System.out.print(aVO.getResale_ordstatus() + ",");
			System.out.print(aVO.getResale_ord_createtime() + ",");
			System.out.print(aVO.getResale_ord_completetime() + ",");
			System.out.print(aVO.getPayment_method() + ",");
			System.out.println("---------------------");
		}
	}
}
