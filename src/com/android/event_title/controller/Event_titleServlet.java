package com.android.event_title.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.event_title.model.Event_titleService;
import com.android.main.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Event_titleServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonin = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonin.append(line);
		}
		System.out.println(jsonin);
		Event_titleService dao = new Event_titleService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if("getAll".equals(action)) {
			String search = jsonObject.get("search").getAsString();
			String className = jsonObject.get("className").getAsString();
			String list = gson.toJson(dao.getAll(search,className));
			writeText(res, list);
		}
		if ("getAllByClass".equals(action)) {
			String className = jsonObject.get("className").getAsString();
			System.out.println(className);
			String list = gson.toJson(dao.getAllByClass(className));
			writeText(res, list);
		}
		if("getFavrEvent".equals(action)) {
				String memberNo = jsonObject.get("memberNo").getAsString();
				String list = gson.toJson(dao.getFavr(memberNo));
				writeText(res, list);
		}
		if("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String evetit_no = jsonObject.get("No").getAsString();
			System.out.println(evetit_no);
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(evetit_no);
			if(image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			try {
			os.write(image);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(os != null) {
					os.close();
				}
			}
		}
		if("getClass".equals(action)){
			writeText(res, gson.toJson(dao.getclass()));
		}
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
