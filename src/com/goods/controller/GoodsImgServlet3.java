package com.goods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods.model.GoodsService;

public class GoodsImgServlet3 extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String goods_no = req.getParameter("goods_no");
		GoodsService goodsSvc = new GoodsService();
		
		byte [] pic = goodsSvc.getOneGoods(goods_no).getGoods_picture3();		
//		System.out.println();
		System.out.println(pic.length);
		
		if(pic.length==0) {
			System.out.println("picture3 not exist");
			//show noImage.jpg
			
			
			
			
		}else {
			ServletOutputStream out = res.getOutputStream();
			
			res.setContentType("image/jpg");
			res.setContentLength(pic.length);
			out.write(pic);
			out.close();
		}

}
}