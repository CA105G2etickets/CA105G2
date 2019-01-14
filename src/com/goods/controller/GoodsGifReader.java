package com.goods.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.event_title.model.EventTitleService;
import com.goods.model.GoodsService;
import com.utility.ImageUtil;

@WebServlet("/goods/GoodsGifReader")
public class GoodsGifReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
 
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ETIckeTsDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		
		request.setCharacterEncoding("utf-8");
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("image/gif");

		try {
			if (!request.getParameter("goods_no").isEmpty()) {
				String goods_no = request.getParameter("goods_no");
				int scaleSize = Integer.parseInt((request.getParameter("scaleSize")));

				GoodsService goodsService = new GoodsService();
				byte[] srcImageData = goodsService.getOneGoods(goods_no).getGoods_picture1();

				byte[] scaledImageDate = ImageUtil.shrink(srcImageData, scaleSize);
				response.setContentLength(scaledImageDate.length);
				out.write(scaledImageDate);
			} else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/imageComingSoon.jpg");
				byte[] bytes = new byte[in.available()];
				in.read(bytes);
				out.write(bytes);
				in.close();
			}
			
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/NoData/imageComingSoon.jpg");
			byte[] bytes = new byte[in.available()];
			in.read(bytes);
			out.write(bytes);
			in.close();					
		}

	}
}
