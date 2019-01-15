package com.group_member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.group_open.model.Group_openVO;

public class Group_memberDAO implements Group_memberDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_MEMBER (MEMBER_NO ,GROUP_NO ,JOIN_TIME ,PRODUCT_QUANTITY ,"
			+ "PAY_STATUS ,GROUP_MEMBER_STATUS ,LOG_OUT_REASON ,ORDER_PHONE ,"
			+ "PAY_METHODS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "select * from group_member order by member_no ";
	private static final String GET_ONE_GROUP_STMT = "SELECT * FROM GROUP_MEMBER where MEMBER_NO = ? and GROUP_NO = ?";
	private static final String DELETE = "DELETE FROM GROUP_MEMBER where MEMBER_NO = ?";
	private static final String DELETE_STMTGROUP_MEMBER = "DELETE FROM GROUP_MEMBER WHERE GROUP_NO = ?";
	private static final String UPDATE_GROUP_MEMBER = "UPDATE GROUP_MEMBER set JOIN_TIME=?, PRODUCT_QUANTITY=?, PAY_STATUS=?, GROUP_MEMBER_STATUS=?, LOG_OUT_REASON=?,ORDER_PHONE=?,PAY_METHODS=? where  GROUP_NO = ? AND  MEMBER_NO = ? ";
	private static final String GET_ALL_PRODUCT_QUANTITY = "SELECT SUM (product_quantity) FROM group_member where (group_member_status = 'withgroup' or group_member_status = 'grouplead') and group_no = ? ";
	private static final String GET_ALL_MEMBER_BY_GROUP_MEMBER = "SELECT DISTINCT member_no FROM group_member ORDER BY member_no";
	private static final String GET_ALL_GROUP_OPEN_BY_GROUP_MEMBER = "SELECT * FROM group_member where member_no = ? ";
	private static final String GET_GROUP_BY_QUANTITY = "select group_no, SUM (product_quantity) product from group_member where group_member_status = 'withgroup' or group_member_status = 'grouplead' group by  group_no  order by group_no";
	private static final String CHANGE_QUIT = "UPDATE GROUP_MEMBER set GROUP_MEMBER_STATUS= ? , LOG_OUT_REASON= ? , PAY_STATUS= ?  where MEMBER_NO = ? and GROUP_NO = ? ";
	private static final String GETEMAIL = "select DISTINCT member.email from group_member left join member on group_member.member_no = member.member_no where group_member.member_no = ?";
	private static final String ALLGROUP_MEMBER_QUIT = "UPDATE GROUP_MEMBER set GROUP_MEMBER_STATUS = 'quit' where group_no = ? ";
	private static final String ALLGROUP_MEMBER_DIMISS = "select member_no from group_member where group_no = ? and group_member_status = 'withgroup' ";
	private static final String GET_GROUP_PRODUCT_QUANTITY = "select  SUM (product_quantity) product from group_member where (group_member_status = 'withgroup' or group_member_status = 'grouplead') and group_no= ? group by  group_no  order by group_no";
	private static final String GETGROUPSUCESSLIST = "select * from group_member where (group_member_status = 'withgroup' or group_member_status = 'grouplead') and group_no = ? order by group_member_status";
	private static final String GETMEMBEREWALLET = "SELECT EWALLET_BALANCE FROM MEMBER WHERE MEMBER_NO = ?";
	private static final String UPDATEEWALLET = "UPDATE MEMBER SET  EWALLET_BALANCE = ? WHERE MEMBER_NO = ?";
	private static final String GETQUITALLMEMBER_NO = "select * from group_member where group_no = ? ";

	@Override
	public void add(Group_memberVO group_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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
			System.out.println("xxxxxxxxxxx");

			pstmt.executeUpdate();
			System.out.println("yyyyyyyyyyyy");

			// Handle any driver errors
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
	public void update(Group_memberVO group_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GROUP_MEMBER);

			pstmt.setString(9, group_memberVO.getMember_no());
			pstmt.setString(8, group_memberVO.getGroup_no());
			pstmt.setTimestamp(1, group_memberVO.getJoin_time());
			pstmt.setInt(2, group_memberVO.getProduct_quantity());
			pstmt.setString(3, group_memberVO.getPay_status());
			pstmt.setString(4, group_memberVO.getGroup_member_status());
			pstmt.setString(5, group_memberVO.getLog_out_reason());
			pstmt.setString(6, group_memberVO.getOrder_phone());
			pstmt.setString(7, group_memberVO.getPay_method());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Group_memberVO findByPrimaryKey(String member_no, String group_no) {

		Group_memberVO grmvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_GROUP_STMT);
			pstmt.setString(1, member_no);
			pstmt.setString(2, group_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				grmvo = new Group_memberVO();
				grmvo.setMember_no(rs.getString("MEMBER_NO"));
				grmvo.setGroup_no(rs.getString("GROUP_NO"));
				grmvo.setJoin_time(rs.getTimestamp("JOIN_TIME"));
				grmvo.setProduct_quantity(rs.getInt("PRODUCT_QUANTITY"));
				grmvo.setPay_status(rs.getString("PAY_STATUS"));
				grmvo.setGroup_member_status(rs.getString("GROUP_MEMBER_STATUS"));
				grmvo.setLog_out_reason(rs.getString("LOG_OUT_REASON"));
				grmvo.setOrder_phone(rs.getString("ORDER_PHONE"));
				grmvo.setPay_method(rs.getString("PAY_METHODS"));
			}

			// Handle any driver errors
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
		return grmvo;
	}

	@Override
	public List<Group_memberVO> getAll() {
		List<Group_memberVO> list = new ArrayList<Group_memberVO>();
		Group_memberVO grmvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱?�� Domain objects
				grmvo = new Group_memberVO();
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

//	public List<Group_memberVO> getAll(Map<String, String[]> map) {
//		List<Group_memberVO> list = new ArrayList<>();
//		Group_memberVO group_memberVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			String finalSQL = "select * from group_member" + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map);
//			pstmt = con.prepareStatement(finalSQL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				group_memberVO = new Group_memberVO();
//				group_memberVO.setMember_no(rs.getString("member_no"));
//				group_memberVO.setGroup_no(rs.getString("group_no"));
//				group_memberVO.setJoin_time(rs.getTimestamp("join_time"));
//				group_memberVO.setProduct_quantity(rs.getInt("product_quantity"));
//				group_memberVO.setPay_status(rs.getString("pay_status"));
//				group_memberVO.setGroup_member_status(rs.getString("group_member_status"));
//				group_memberVO.setLog_out_reason(rs.getString("log_out_reason"));
//				group_memberVO.setOrder_phone(rs.getString("order_phone"));
//				group_memberVO.setPay_method(rs.getString("pay_method"));
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException("A database error occured. " + e.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}

	public String getproductquantity(String group_no) {

		Group_memberVO grmvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_quantity = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_PRODUCT_QUANTITY);
			pstmt.setString(1, group_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				product_quantity = rs.getString(1);
				System.out.println("Group_memberDAO" + product_quantity);

			} else {
				System.out.println("找不到商品數量");
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

		return product_quantity;

	}

	public List<Group_memberVO> getmember_BY_group_member() {

		List<Group_memberVO> list = new ArrayList<Group_memberVO>();

		Group_memberVO group_memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMBER_BY_GROUP_MEMBER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_memberVO = new Group_memberVO();
				group_memberVO.setMember_no(rs.getString("member_no"));
				list.add(group_memberVO);

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
		return list;
	}

	public List<Group_memberVO> getgroup_BY_member_no(String member_no) {

		List<Group_memberVO> list = new ArrayList<Group_memberVO>();

		Group_memberVO grmvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GROUP_OPEN_BY_GROUP_MEMBER);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				grmvo = new Group_memberVO();
				grmvo.setMember_no(rs.getString("MEMBER_NO"));
				grmvo.setGroup_no(rs.getString("GROUP_NO"));
				grmvo.setJoin_time(rs.getTimestamp("JOIN_TIME"));
				grmvo.setProduct_quantity(rs.getInt("PRODUCT_QUANTITY"));
				grmvo.setPay_status(rs.getString("PAY_STATUS"));
				grmvo.setGroup_member_status(rs.getString("GROUP_MEMBER_STATUS"));
				grmvo.setLog_out_reason(rs.getString("LOG_OUT_REASON"));
				grmvo.setOrder_phone(rs.getString("ORDER_PHONE"));
				grmvo.setPay_method(rs.getString("PAY_METHODS"));
				list.add(grmvo);

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

		return list;

	}

	// 同時新增開團者與跟團者
	public void add2(Group_memberVO group_memberVO, Connection con) {

		PreparedStatement pstmt = null;

		try {
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

		} catch (SQLException e) {
			System.out.println(e);
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
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
						} catch (Exception e1) {
							e1.printStackTrace(System.err);
						}
					}
				}
			}
		}

	}

	public Map<String, Integer> getgroup_quantity() {

		Map<String, Integer> map = new HashMap<>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GROUP_BY_QUANTITY);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				map.put(rs.getString("GROUP_NO"), rs.getInt("PRODUCT"));
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
		return map;
	}

	@Override
	public void change_quit(Group_memberVO group_memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_QUIT);

			pstmt.setString(1, group_memberVO.getGroup_member_status());
			pstmt.setString(2, group_memberVO.getLog_out_reason());
			pstmt.setString(3, group_memberVO.getPay_status());
			pstmt.setString(4, group_memberVO.getMember_no());
			pstmt.setString(5, group_memberVO.getGroup_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public String getemail(String member_no) {
		System.out.println("有進來getmail");
		String email = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETEMAIL);
			pstmt.setString(1, member_no);
			System.out.println("getmailServlet" + member_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				email = rs.getString(1);
				System.out.println("getmailServlet" + email);
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

		return email;
	}

	public void sendMail(String to, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "ixlogic.wu@gmail.com";
			final String myGmail_password = "BBB45678";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject("退團通知");
			// 設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");

		} catch (Exception e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}

	}

	public void allgroup_member_quit(String group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ALLGROUP_MEMBER_QUIT);

			pstmt.setString(1, group_no);

			rs = pstmt.executeQuery();

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

	public List<Group_memberVO> getall_member_dimiss(String group_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Group_memberVO group_memberVO = null;
		List<Group_memberVO> list = new ArrayList<Group_memberVO>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ALLGROUP_MEMBER_DIMISS);

			pstmt.setString(1, group_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_memberVO = new Group_memberVO();
				group_memberVO.setMember_no(rs.getString("MEMBER_NO"));
				list.add(group_memberVO);
				System.out.println(list.size());

			}
			System.out.println(list.isEmpty());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public String getgroup_member_product(String group_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_GROUP_PRODUCT_QUANTITY);

			pstmt.setString(1, group_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				product = rs.getString("product");
			} else {
				System.out.println("取不到值");
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

		return product;

	}

	public List<Group_memberVO> getgroupsucesslist(String group_no) {
		List<Group_memberVO> list = new ArrayList<>();
		Group_memberVO group_memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETGROUPSUCESSLIST);
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
				list.add(group_memberVO);

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

		return list;
	} /// 加 sql指令 interface service

	public Integer getewallet(String member_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer ewallet = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETMEMBEREWALLET);

			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ewallet = rs.getInt("EWALLET_BALANCE");

			} else {
				System.out.println("取不到錢包值");
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

		return ewallet;
	}

	public void updateewallet(Integer ewalletBalance, String member_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			;

			pstmt = con.prepareStatement(UPDATEEWALLET);

			pstmt.setInt(1, ewalletBalance);

			pstmt.setString(2, member_no);

			rs = pstmt.executeQuery();

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

	public List<Group_memberVO> getquitgroup_member(String group_no) {

		List<Group_memberVO> list = new ArrayList();
		Group_memberVO group_memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			;
			pstmt = con.prepareStatement(GETQUITALLMEMBER_NO);
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
				list.add(group_memberVO); // Store the row in the list
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
		return list;
	}

}
