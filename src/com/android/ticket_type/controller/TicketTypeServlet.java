package com.android.ticket_type.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.ticket_type.model.TicketTypeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TicketTypeServlet extends HttpServlet {

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
		TicketTypeService dao = new TicketTypeService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		if("getPrice".equals(action)) {
			String tictype_no = jsonObject.get("tictype_no").getAsString();
			String price = dao.getPrice(tictype_no);
			writeText(res,price);
		}
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
	
}
