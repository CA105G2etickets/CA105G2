package com.administrator.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.permission.model.*;

public class AdministratorDAO implements AdministratorDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO ADMINISTRATOR (ADMINISTRATOR_NO,ADMINISTRATOR_NAME,ADMINISTRATOR_ACCOUNT,ADMINISTRATOR_PASSWORD,CREATION_DATE,ADMINISTRATOR_PICTURE,ADMINISTRATOR_STATUS) VALUES ('A'||LPAD(to_char(administrator_no_seq.NEXTVAL), 3, '0'), ?, ?, ?,CURRENT_TIMESTAMP, ?, ?)";
	private static final String UPDATE = 
			"UPDATE ADMINISTRATOR SET ADMINISTRATOR_NAME = ?, ADMINISTRATOR_ACCOUNT = ?, ADMINISTRATOR_PASSWORD = ?, CREATION_DATE = ?, ADMINISTRATOR_PICTURE = ?, ADMINISTRATOR_STATUS = ? WHERE ADMINISTRATOR_NO = ?";
	private static final String DELETE = 
			"DELETE FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM ADMINISTRATOR ORDER BY ADMINISTRATOR_NO";
	private static final String GET_ONE_ADMINISTRATOR_BY_ACCOUNT = 
			"SELECT * FROM ADMINISTRATOR WHERE ADMINISTRATOR_ACCOUNT=?";
	private static final String GET_Permissions_ByAdministratorno_STMT = 
			"SELECT * FROM PERMISSION WHERE ADMINISTRATOR_NO = ?";

	@Override
	public void insert(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administratorVO.getAdministrator_name());
			pstmt.setString(2, administratorVO.getAdministrator_account());
			pstmt.setString(3, administratorVO.getAdministrator_password());
//			pstmt.setTimestamp(5, administratorVO.getCreation_date());
			pstmt.setBytes(4, administratorVO.getAdministrator_picture());
			pstmt.setString(5, administratorVO.getAdministrator_status());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("錯誤"
					+ se.getMessage());
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
	public void update(AdministratorVO administratorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdministrator_name());
			pstmt.setString(2, administratorVO.getAdministrator_account());
			pstmt.setString(3, administratorVO.getAdministrator_password());
			pstmt.setTimestamp(4, administratorVO.getCreation_date());
			pstmt.setBytes(5, administratorVO.getAdministrator_picture());
			pstmt.setString(6, administratorVO.getAdministrator_status());
			pstmt.setString(7, administratorVO.getAdministrator_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
					+ se.getMessage());
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
	public void delete(String administrator_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, administrator_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
					+ se.getMessage());
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
	public AdministratorVO findByPrimaryKey(String administrator_no) {

		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, administrator_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
					+ se.getMessage());
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
		return administratorVO;
	}

	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO administratorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
				list.add(administratorVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
					+ se.getMessage());
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
	
public AdministratorVO findByAccount(String administrator_account) {
		
		AdministratorVO administratorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ADMINISTRATOR_BY_ACCOUNT);
			
			pstmt.setString(1, administrator_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				administratorVO = new AdministratorVO();
				administratorVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
				administratorVO.setAdministrator_name(rs.getString("ADMINISTRATOR_NAME"));
				administratorVO.setAdministrator_account(rs.getString("ADMINISTRATOR_ACCOUNT"));
				administratorVO.setAdministrator_password(rs.getString("ADMINISTRATOR_PASSWORD"));
				administratorVO.setCreation_date(rs.getTimestamp("CREATION_DATE"));
				administratorVO.setAdministrator_picture(rs.getBytes("ADMINISTRATOR_PICTURE"));
				administratorVO.setAdministrator_status(rs.getString("ADMINISTRATOR_STATUS"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("BuBu!"
					+ se.getMessage());
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
		return administratorVO;
	}

@Override
public Set<PermissionVO> getPermissionsByAdministratorno(String administrator_no) {
	Set<PermissionVO> set = new HashSet<PermissionVO>();
	PermissionVO permissionVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_Permissions_ByAdministratorno_STMT);
		
		pstmt.setString(1, administrator_no);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			permissionVO = new PermissionVO();
			permissionVO.setPermission_list_no(rs.getString("PERMISSION_LIST_NO"));
			permissionVO.setAdministrator_no(rs.getString("ADMINISTRATOR_NO"));
			set.add(permissionVO);
		}

	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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

@Override
public void insertWithPermission (AdministratorVO administratorVO , List<PermissionVO> list) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		
		con = ds.getConnection();
		con.setAutoCommit(false);
		
		String cols[] = {"ADMINISTRATOR_NO"};
		pstmt = con.prepareStatement(INSERT_STMT, cols);
		
		pstmt.setString(1, administratorVO.getAdministrator_name());
		pstmt.setString(2, administratorVO.getAdministrator_account());
		pstmt.setString(3, administratorVO.getAdministrator_password());
//		pstmt.setTimestamp(5, administratorVO.getCreation_date());
		pstmt.setBytes(4, administratorVO.getAdministrator_picture());
		pstmt.setString(5, administratorVO.getAdministrator_status());
		pstmt.executeUpdate();
		
		String next_administratorno = null;
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			next_administratorno = rs.getString(1);
		}
		
		rs.close();
		PermissionDAO dao = new PermissionDAO();
		for (PermissionVO aPermission : list) {
			aPermission.setAdministrator_no(new String(next_administratorno));
			dao.insert2(aPermission,con);
		}

		con.commit();
		con.setAutoCommit(true);
		
	} catch (SQLException se) {
		if (con != null) {
			try {
				System.err.print("Transaction is being ");
				System.err.println("rolled back");
				con.rollback();
			} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. "
						+ excep.getMessage());
			}
		}
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
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


}