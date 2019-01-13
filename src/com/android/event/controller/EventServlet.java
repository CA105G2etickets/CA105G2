package com.android.event.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.event.model.EventService;
import com.android.main.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EventServlet extends HttpServlet{

	
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
		EventService dao = new EventService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		if("getEvents".equals(action)) {
			String evetit_no = jsonObject.get("evetit_no").getAsString();
			writeText(res, gson.toJson(dao.getEvents(evetit_no)));
		}
		if("getImage".equals(action)) {
			String eve_no = jsonObject.get("No").getAsString();
			int imageSize = jsonObject.get("imageSize").getAsInt();
			OutputStream os = res.getOutputStream();
			byte[] image = dao.getEventImage(eve_no);
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
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
