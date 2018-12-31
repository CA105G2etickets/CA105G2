package com.android.news_classification.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

public class NewsClassificationDAO implements NewsClassificationDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//	private static final String INSERT_STMT = 
//			"INSERT INTO NEWS_CLASSIFICATION (NEWS_CLASSIFICATION_NO,NEWS_CLASSIFICATION) VALUES ( ?, ?)";
//	private static final String UPDATE = 
//			"UPDATE NEWS_CLASSIFICATION SET NEWS_CLASSIFICATION_NO = ?, NEWS_CLASSIFICATION = ? WHERE NEWS_CLASSIFICATION_NO = ?";
//	private static final String DELETE = 
//			"DELETE FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
//	private static final String GET_ONE_STMT = 
//			"SELECT * FROM NEWS_CLASSIFICATION WHERE NEWS_CLASSIFICATION_NO = ?";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM NEWS_CLASSIFICATION";


	@Override
	public List<NewsClassificationVO> getAll() {
		
		List<NewsClassificationVO> list = new ArrayList<NewsClassificationVO>();
		NewsClassificationVO newsClassification = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				newsClassification = new NewsClassificationVO();
//				newsClassification.setNewsClassificationNo(rs.getString("NEWS_CLASSIFICATION_NO"));
				newsClassification.setNewsClassification(rs.getString("NEWS_CLASSIFICATION"));
				list.add(newsClassification);
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
}