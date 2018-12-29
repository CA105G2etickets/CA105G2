package com.forum.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ForumJDBCDAO implements ForumDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Wilson";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO FORUM VALUES('F'||LPAD(to_char(FORUM_SEQ.NEXTVAL),4,0), ?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE FORUM SET GROUP_NO = ?,  MEMBER_NO = ?, "
			+ "FORUM_CONTENT = ?, FORUM_TIME = ? where FORUM_NO = ? ";
	private static final String DELETE_STMT = "DELETE FROM FORUM WHERE FORUM_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FORUM WHERE FORUM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM FORUM";

	@Override
	// ?ñ∞Â¢?
	public void add(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumVO.getGroup_no());
			pstmt.setString(2, forumVO.getMember_no());
			pstmt.setString(3, forumVO.getForum_content());
			pstmt.setTimestamp(4, forumVO.getForum_time());

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
					se.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	// ‰øÆÊîπ
	public void update(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(5, forumVO.getForum_no());
			pstmt.setString(1, forumVO.getGroup_no());
			pstmt.setString(2, forumVO.getMember_no());
			pstmt.setString(3, forumVO.getForum_content());
			pstmt.setTimestamp(4, forumVO.getForum_time());

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
				} catch (SQLException se) {
					se.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	// ?à™?ô§
	public void delete(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, forum_no);

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
				} catch (SQLException se) {
					se.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	// ?ü•Ë©?
	public ForumVO findByPK(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumVO forum1 = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, forum_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forum1 = new ForumVO();
				forum1.setForum_no(rs.getString("FORUM_NO"));
				forum1.setGroup_no(rs.getString("GROUP_NO"));
				forum1.setMember_no(rs.getString("MEMBER_NO"));
				forum1.setForum_content((rs.getString("FORUM_CONTENT")));
				forum1.setForum_time(rs.getTimestamp("FORUM_TIME"));

			}

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
				} catch (SQLException se) {
					se.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return forum1;
	}

	@Override
	// ?ü•Ë©?
	public List<ForumVO> getAll() {

		List<ForumVO> ForumList = new ArrayList<>();
		ForumVO forum1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forum1 = new ForumVO();
				forum1.setForum_no(rs.getString("FORUM_NO"));
				forum1.setGroup_no(rs.getString("GROUP_NO"));
				forum1.setMember_no(rs.getString("MEMBER_NO"));
				forum1.setForum_content((rs.getString("FORUM_CONTENT")));
				forum1.setForum_time(rs.getTimestamp("FORUM_TIME"));
				ForumList.add(forum1);
			}

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
				} catch (SQLException se) {
					se.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return ForumList;

	}

	public static void main(String[] args) throws IOException {
		ForumJDBCDAO dao = new ForumJDBCDAO();
		// ?ñ∞Â¢?
//		ForumVO forumVO = new ForumVO();
//		forumVO.setGROUP_NO("G0001");
//		forumVO.setMEMBER_NO("M000008");
//		forumVO.setFORUM_CONTENT("Â•Ω‰?ÖÊ?íÂ?ò‰?ÜÂ?åÊ?õÊ?êÂ??");
//      TimeStamp Ê≠?Ë¶èÂ?? ??ñÂá∫??ÇÂ?ÜÁ?? ?â©‰ª? ?à©?î® Format ËΩâÊ?êË?ÅÁ?ÑÊ†ºÂº?
//		Timestamp Timestamp1 = new Timestamp(System.currentTimeMillis());
//		Format tsft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String time = tsft.format(Timestamp1);
//		System.out.println(time);
//		forumVO.setFORUM_TIME(Timestamp.valueOf("2018-12-11 18:00:00"));
//		dao.add(forumVO);
//		System.out.println("?ñ∞Â¢ûÊ?êÂ?ü‰??");

		// ‰øÆÊîπ
//		ForumVO forum2 = new ForumVO();
//		forum2.setFORUM_NO("F0001");
//		forum2.setGROUP_NO("G0001");
//		forum2.setMEMBER_NO("M000004");
//		forum2.setFORUM_CONTENT("??ãÂ?òÊ?êÂ?ü‰??");
//		Timestamp Timestamp2 = new Timestamp(System.currentTimeMillis());
//		forum2.setFORUM_TIME(Timestamp2);
//		dao.update(forum2);
//		System.out.println("‰øÆÊîπ??êÂ??");

		// ?à™?ô§
//		dao.delete("F0001");
//		System.out.println("?à™?ô§ÂÆåÊ??");

		// ?ü•Ë©?
//		ForumVO forum4 = dao.findByPK("F0002");
//
//		System.out.println(forum4.getFORUM_NO());
//		System.out.println(forum4.getGROUP_NO());
//		System.out.println(forum4.getMEMBER_NO());
//		System.out.println(forum4.getFORUM_CONTENT());
//		System.out.println(forum4.getFORUM_TIME());

//		 ?ü•Ë©?
//		List<ForumVO> list = dao.getAll();
//		String str;
//		for (ForumVO forum5 : list) {
//			System.out.println(forum5.getFORUM_NO());
//			System.out.println(forum5.getGROUP_NO());
//			System.out.println(forum5.getMEMBER_NO());
//			System.out.println(forum5.getFORUM_CONTENT());
//			System.out.println(forum5.getFORUM_TIME());
//			System.out.println("==================");
//		}
//	};

//	public static String getLongString(String path) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
//		String str;
//		while ((str = br.readLine()) != null) {
//			sb.append(str);
//			sb.append("\n");
//		}
//		br.close();
//
//		return sb.toString();
	}

}
