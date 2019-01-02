package com.android.news.model;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class NewsDAO implements NewsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(com.utility.Util.JNDI_DATABASE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public List<NewsVO> findByClassification(String news_classification) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NewsVO> newsList = new ArrayList<NewsVO>();
		
		String getAll = "select * " + 
				"from news left join news_classification " + 
				"on news.news_classification_no = news_classification.news_classification_no " +
				"where upper(news_classification) like upper('%"+news_classification+"%') " +
				"order by announce_date";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NewsVO newsVO = new NewsVO();
				newsVO.setNews_classification(rs.getString("News_classification"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsList.add(newsVO);
			}
			
		} catch (Exception e) {
			System.out.println(e);
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
		return newsList;
	}

	@Override
	public List<NewsVO> getAll(String str) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NewsVO> newsList = new ArrayList<NewsVO>();
		
		String getAll = "select * " + 
				"from news left join news_classification " + 
				"on news.news_classification_no = news_classification.news_classification_no " +
				"where upper(news_title) like upper('%"+str+"%') " +
				"order by announce_date";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				NewsVO newsVO = new NewsVO();
				newsVO.setNews_classification(rs.getString("news_classification"));
				newsVO.setNews_title(rs.getString("news_title"));
				newsVO.setNews_content(rs.getString("news_content"));
				newsList.add(newsVO);
			}
			
		} catch (Exception e) {
			System.out.println(e);
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
		return newsList;
	}
}