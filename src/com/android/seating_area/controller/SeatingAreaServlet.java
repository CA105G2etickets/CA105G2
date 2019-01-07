package com.android.seating_area.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.main.ImageUtil;
import com.android.seating_area.model.SeatingAreaService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SeatingAreaServlet extends HttpServlet {
	
	
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
		SeatingAreaService dao = new SeatingAreaService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		System.out.println(action);
		if("getSeat".equals(action)) {
			String eventNo = jsonObject.get("eventNo").getAsString();
			String list = gson.toJson(dao.findByPrimaryKey(eventNo));
			writeText(res, list);
		}
		if("getEvent".equals(action)) {
			String eventNo = jsonObject.get("eventNo").getAsString();
			writeText(res, dao.getEvent(eventNo));
		}
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
