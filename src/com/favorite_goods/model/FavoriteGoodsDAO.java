package com.favorite_goods.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FavoriteGoodsDAO implements FavoriteGoodsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO FAVORITE_GOODS (MEMBER_NO, GOODS_NO) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEMBER_NO, GOODS_NO FROM FAVORITE_GOODS ORDER BY MEMBER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEMBER_NO, GOODS_NO FROM FAVORITE_GOODS WHERE MEMBER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FAVORITE_GOODS WHERE MEMBER_NO = ? AND GOODS_NO = ?";
	private static final String UPDATE =
		"UPDATE FAVORITE_GOODS SET GOODS_NO = ? WHERE MEMBER_NO = ?";
	private static final String GET_ALL_MEMBERNO = 
		"SELECT DISTINCT MEMBER_NO FROM FAVORITE_GOODS ORDER BY MEMBER_NO";
	private static final String GET_ALL_GOODS_OF_A_MEMBERN = 
		"SELECT * FROM FAVORITE_GOODS WHERE MEMBER_NO = ?";
	private static final String GET_ALL_GOODSNO = 
		"SELECT DISTINCT GOODS_NO FROM FAVORITE_GOODS ORDER BY GOODS_NO";
	private static final String GET_ALL_MEMBERN_OF_A_GOODS = 
		"SELECT * FROM FAVORITE_GOODS WHERE GOODS_NO = ?";
	private static final String GET_ONE_FAVORITEGOODS =
		"SELECT MEMBER_NO, GOODS_NO FROM FAVORITE_GOODS WHERE MEMBER_NO=? AND GOODS_NO=?";	
	
	@Override
	public void insert(FavoriteGoodsVO favoriteGoodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, favoriteGoodsVO.getMember_no());
			pstmt.setString(2, favoriteGoodsVO.getGoods_no());
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
	public void update(FavoriteGoodsVO favoriteGoodsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, favoriteGoodsVO.getGoods_no());
			pstmt.setString(2, favoriteGoodsVO.getMember_no());
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
	public void delete(String member_no, String goods_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, member_no);
			pstmt.setString(2, goods_no);
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
	public FavoriteGoodsVO findByPrimaryKey(String member_no) {

		FavoriteGoodsVO favoriteGoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));
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
		return favoriteGoodsVO;
	}

	@Override
	public List<FavoriteGoodsVO> getAll() {
		List<FavoriteGoodsVO> list = new ArrayList<FavoriteGoodsVO>();
		FavoriteGoodsVO favoriteGoodsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));
				list.add(favoriteGoodsVO); 
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
	
	@Override
	public List<String> getAllMemberNo() {
		List<String> list = new ArrayList<String>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMBERNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("MEMBER_NO")); 
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
	
	@Override
	public List<FavoriteGoodsVO> findByMemberNo(String member_no) {
		
		List<FavoriteGoodsVO> list = new ArrayList<FavoriteGoodsVO>();
		FavoriteGoodsVO favoriteGoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GOODS_OF_A_MEMBERN);
			pstmt.setString(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));
				list.add(favoriteGoodsVO); 
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
	
	@Override
	public List<String> getAllGoodsNo() {
		List<String> list = new ArrayList<String>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GOODSNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("GOODS_NO")); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());

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
	
	@Override
	public List<FavoriteGoodsVO> findByGoodsNo(String goods_no) {
		
		List<FavoriteGoodsVO> list = new ArrayList<FavoriteGoodsVO>();
		FavoriteGoodsVO favoriteGoodsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEMBERN_OF_A_GOODS);
			pstmt.setString(1, goods_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favoriteGoodsVO = new FavoriteGoodsVO();
				favoriteGoodsVO.setMember_no(rs.getString("MEMBER_NO"));
				favoriteGoodsVO.setGoods_no(rs.getString("GOODS_NO"));
				list.add(favoriteGoodsVO); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "	+ se.getMessage());
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
	
	@Override
	public boolean getOneFavoriteGoods(String member_no, String goods_no) {

		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		boolean result;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_FAVORITEGOODS);
			
			pstmt.setString(1, member_no); 
			pstmt.setString(2, goods_no); 
			rs = pstmt.executeQuery();
			
			result = rs.next();

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
		return result;
	}
}
