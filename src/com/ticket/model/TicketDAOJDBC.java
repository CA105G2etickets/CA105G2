package com.ticket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticketorder.model.TicketOrderDAOJDBC;
import com.ticketorder.model.TicketOrderVO;

public class TicketDAOJDBC implements TicketDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G2";
	String passwd = "123456";
	private static final String INSERT_STMT=
			"INSERT INTO ticket (ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale) VALUES (ticket_seq.NEXTVAL,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale FROM ticket order by ticket_no";
	private static final String GET_ONE_STMT=
			"SELECT ticket_no,ticarea_no,ticket_order_no,member_no,ticket_status,ticket_create_time,ticket_resale_status,ticket_resale_price,is_from_resale FROM ticket where ticket_no=?";
	private static final String DELETE = 
			"DELETE FROM ticket where ticket_no = ?";
	private static final String UPDATE = 
			"UPDATE ticket set ticarea_no=?, ticket_order_no=?, member_no=?, ticket_status=?, ticket_create_time=?, ticket_resale_status=?,ticket_resale_price=?,is_from_resale=? where ticket_no=?";

	@Override
	public void insert(TicketVO ticketVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, ticketVO.getTicarea_no());
			pstmt.setString(2, ticketVO.getTicket_order_no());
			pstmt.setString(3, ticketVO.getMember_no());
			pstmt.setString(4, ticketVO.getTicket_status());
			pstmt.setTimestamp(5, ticketVO.getTicket_create_time());
			pstmt.setString(6, ticketVO.getTicket_resale_status());
			pstmt.setInt(7, ticketVO.getTicket_resale_price());
			pstmt.setString(8, ticketVO.getIs_from_resale());

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
	public void update(TicketVO ticketVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ticketVO.getTicarea_no());
			pstmt.setString(2, ticketVO.getTicket_order_no());
			pstmt.setString(3, ticketVO.getMember_no());
			pstmt.setString(4, ticketVO.getTicket_status());
			pstmt.setTimestamp(5, ticketVO.getTicket_create_time());
			pstmt.setString(6, ticketVO.getTicket_resale_status());
			pstmt.setInt(7, ticketVO.getTicket_resale_price());
			pstmt.setString(8, ticketVO.getIs_from_resale());
			pstmt.setString(9, ticketVO.getTicket_no());

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
	public void delete(String ticket_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ticket_no);

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
	public TicketVO findByPrimaryKey(String ticket_no) {
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ticket_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				ticketVO = new TicketVO();
				ticketVO.setTicket_no(rs.getString("ticket_no"));
				ticketVO.setTicarea_no(rs.getString("ticarea_no"));
				ticketVO.setTicket_order_no(rs.getString("ticket_order_no"));
				ticketVO.setMember_no(rs.getString("member_no"));
				ticketVO.setTicket_status(rs.getString("ticket_status"));
				ticketVO.setTicket_create_time(rs.getTimestamp("ticket_create_time"));
				ticketVO.setTicket_resale_status(rs.getString("ticket_resale_status"));
				ticketVO.setTicket_resale_price(rs.getInt("ticket_resale_price"));
				ticketVO.setIs_from_resale(rs.getString("is_from_resale"));
				
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
		return ticketVO;
	}

	@Override
	public List<TicketVO> getAll() {
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;

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
				ticketVO = new TicketVO();
				ticketVO.setTicket_no(rs.getString("ticket_no"));
				ticketVO.setTicarea_no(rs.getString("ticarea_no"));
				ticketVO.setTicket_order_no(rs.getString("ticket_order_no"));
				ticketVO.setMember_no(rs.getString("member_no"));
				ticketVO.setTicket_status(rs.getString("ticket_status"));
				ticketVO.setTicket_create_time(rs.getTimestamp("ticket_create_time"));
				ticketVO.setTicket_resale_status(rs.getString("ticket_resale_status"));
				ticketVO.setTicket_resale_price(rs.getInt("ticket_resale_price"));
				ticketVO.setIs_from_resale(rs.getString("is_from_resale"));
				list.add(ticketVO); // Store the row in the list
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
		TicketDAOJDBC dao = new TicketDAOJDBC();
		//�s�W
		TicketVO ticketVO = new TicketVO();
//		dao.insert(ticketorderVO);
		
		//�ק�
//		dao.update(ticketorderVO2);
		
		//�R��
//		dao.delete("ticket_order_no"); //����ݭn��ʿ�J
		
		//�d��
		System.out.println("------select1 start--------");
		
		System.out.println("---------------------");
		
		//�d�� list
		List<TicketVO> list = dao.getAll();
		for(TicketVO aVO :list) {
			System.out.println("------select2 start--------");
			System.out.print(aVO.getTicket_no() + ",");
			System.out.print(aVO.getTicarea_no() + ",");
			System.out.print(aVO.getTicket_order_no() + ",");
			System.out.print(aVO.getMember_no() + ",");
			System.out.print(aVO.getTicket_status() + ",");
			System.out.print(aVO.getTicket_create_time() + ",");
			System.out.print(aVO.getTicket_resale_status() + ",");
			System.out.print(aVO.getTicket_resale_price() + ",");
			System.out.print(aVO.getIs_from_resale() + ",");
			System.out.println("---------------------");
		}
	}
}
