package com.transaction_history.model;

import java.sql.*;
import java.util.*;

public class TransactionHistoryJDBCDAO implements TransactionHistoryDAO_interface {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G2";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO TRANSACTION_HISTORY (TRANSACTION_HISTORY_NO,MEMBER_NO,TRANSACTION_DATETIME,TRANSACTION_CATEGORY,EXPENDITURES,RECEIPT,EWALLET_BALANCE,DESCRIPTION) VALUES ('20180225'||'-'||LPAD(to_char(transaction_history_no_seq.NEXTVAL), 2, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM TRANSACTION_HISTORY ORDER BY TRANSACTION_HISTORY_NO";
	private static final String DELETE = 
			"DELETE FROM TRANSACTION_HISTORY WHERE TRANSACTION_HISTORY_NO = ?";
	private static final String UPDATE = 
			"UPDATE TRANSACTION_HISTORY SET MEMBER_NO = ?, TRANSACTION_DATETIME = ?, TRANSACTION_CATEGORY = ?, EXPENDITURES = ?, RECEIPT = ?, EWALLET_BALANCE = ?, DESCRIPTION = ? WHERE TRANSACTION_HISTORY_NO = ?";
	private static final String FIND_BY_PK_SQL = 
			"SELECT * FROM TRANSACTION_HISTORY WHERE TRANSACTION_HISTORY_NO = ?";
	
	@Override
	public void insert(TransactionHistoryVO transactionHistory) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, transactionHistory.getMemberNo());
			pstmt.setTimestamp(2, transactionHistory.getTransactionDatetime());
			pstmt.setString(3, transactionHistory.getTransactionCategory());
			pstmt.setInt(4, transactionHistory.getExpenditures());
			pstmt.setInt(5, transactionHistory.getReceipt());
			pstmt.setInt(6, transactionHistory.getEwalletBalance());
			pstmt.setString(7, transactionHistory.getDescription());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
	public void update(TransactionHistoryVO transactionHistory) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, transactionHistory.getMemberNo());
			pstmt.setTimestamp(2, transactionHistory.getTransactionDatetime());
			pstmt.setString(3, transactionHistory.getTransactionCategory());
			pstmt.setInt(4, transactionHistory.getExpenditures());
			pstmt.setInt(5, transactionHistory.getReceipt());
			pstmt.setInt(6, transactionHistory.getEwalletBalance());
			pstmt.setString(7, transactionHistory.getDescription());
			pstmt.setString(8, transactionHistory.getTransactionHistoryNo());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
	public void delete(String transactionHistoryNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, transactionHistoryNo);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
	public TransactionHistoryVO findByPrimaryKey(String transactionHistoryNo) {
		
		TransactionHistoryVO transactionHistory = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK_SQL);

			pstmt.setString(1, transactionHistoryNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				transactionHistory = new TransactionHistoryVO();
				transactionHistory.setTransactionHistoryNo(rs.getString("transactionHistoryNo"));
				transactionHistory.setMemberNo(rs.getString("memberNo"));
				transactionHistory.setTransactionDatetime(rs.getTimestamp("transactionDatetime"));
				transactionHistory.setTransactionCategory(rs.getString("transactionCategory"));
				transactionHistory.setExpenditures(rs.getInt("expenditures"));
				transactionHistory.setReceipt(rs.getInt("receipt"));
				transactionHistory.setEwalletBalance(rs.getInt("ewalletBalance"));
				transactionHistory.setDescription(rs.getString("description"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
		return transactionHistory;
	}

	@Override
	public List<TransactionHistoryVO> getAll() {
		
		List<TransactionHistoryVO> list = new ArrayList<TransactionHistoryVO>();
		TransactionHistoryVO transactionHistory = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				transactionHistory = new TransactionHistoryVO();
				transactionHistory.setTransactionHistoryNo(rs.getString("transactionHistoryNo"));
				transactionHistory.setMemberNo(rs.getString("memberNo"));
				transactionHistory.setTransactionDatetime(rs.getTimestamp("transactionDatetime"));
				transactionHistory.setTransactionCategory(rs.getString("transactionCategory"));
				transactionHistory.setExpenditures(rs.getInt("expenditures"));
				transactionHistory.setReceipt(rs.getInt("receipt"));
				transactionHistory.setEwalletBalance(rs.getInt("ewalletBalance"));
				transactionHistory.setDescription(rs.getString("description"));
				list.add(transactionHistory);
			}

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("An error occured."
					+ e.getMessage());
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
