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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ForumDAO implements ForumDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Wilson";
	private static final String PASSWORD = "123456";

	private static final String INSERT_STMT = "INSERT INTO FORUM VALUES('F'||LPAD(to_char(FORUM_SEQ.NEXTVAL),4,'0'),?,?,?,?)";
																		
	private static final String UPDATE_STMT = "UPDATE FORUM SET GROUP_NO = ?,  MEMBER_NO = ?, "
			+ "FORUM_CONTENT = ?, FORUM_TIME = ? where FORUM_NO = ? ";
	private static final String DELETE_STMT = "DELETE FROM FORUM WHERE FORUM_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FORUM WHERE FORUM_NO = ?";
	private static final String GET_ALL = "SELECT * FROM FORUM";
	private static final String GET_ALL_FORUM_BY_GROUP = "select *  from forum  where group_no = ? ";
	private static final String GET_ALL_GROUP = "SELECT DISTINCT group_no FROM forum ORDER BY group_no";

	@Override
	// 新增
	public void add(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, forumVO.getGroup_no());
			pstmt.setString(2, forumVO.getMember_no());
			pstmt.setString(3, forumVO.getForum_content());
			pstmt.setTimestamp(4, forumVO.getForum_time());

			pstmt.executeUpdate();

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
	// 修改
	public void update(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(5, forumVO.getForum_no());
			pstmt.setString(1, forumVO.getGroup_no());
			pstmt.setString(2, forumVO.getMember_no());
			pstmt.setString(3, forumVO.getForum_content());
			pstmt.setTimestamp(4, forumVO.getForum_time());

			pstmt.executeUpdate();

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
	// 刪除一則留言
	public void delete(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, forum_no);

			pstmt.executeUpdate();

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
	// 尋找一個發言
	public ForumVO findByPK(String forum_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumVO forum1 = null;

		try {
			con = ds.getConnection();
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
	// 取得全部發言
	public List<ForumVO> getAll() {

		List<ForumVO> ForumList = new ArrayList<>();
		ForumVO forum1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
	//	根據開團編號取得發言
	public List<ForumVO> getall_forum_by_group(String group_no) {

		List<ForumVO> list = new ArrayList<ForumVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumVO forumVO = new ForumVO();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_FORUM_BY_GROUP);
			pstmt.setString(1, group_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setForum_no(rs.getString("FORUM_NO"));
				forumVO.setGroup_no(rs.getString("GROUP_NO"));
				forumVO.setMember_no(rs.getString("MEMBER_NO"));
				forumVO.setForum_content((rs.getString("FORUM_CONTENT")));
				forumVO.setForum_time(rs.getTimestamp("FORUM_TIME"));
				list.add(forumVO);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	//取得有討論區所有開團編號
	public List<ForumVO> getallgroup() {
		List<ForumVO> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ForumVO forumVO = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GROUP);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setGroup_no(rs.getNString("GROUP_NO"));
				list.add(forumVO);

			}

		} catch (Exception e) {
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

}
