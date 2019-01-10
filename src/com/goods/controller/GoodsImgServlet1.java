package com.goods.controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods.model.GoodsService;


public class GoodsImgServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String goods_no = req.getParameter("goods_no");
		GoodsService goodsService = new GoodsService();
		byte[] goods_picture1 = goodsService.getOneGoods(goods_no).getGoods_picture1();
		
		ServletOutputStream output = res.getOutputStream();
		res.setContentLengthLong(goods_picture1.length);
		res.setContentType("image/*");
		output.write(goods_picture1);
		output.close();

		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
	}
	

}
