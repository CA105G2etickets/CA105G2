package com.android.ticket.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.ticket.model.TicketService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TicketServlet extends HttpServlet {

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
		TicketService dao = new TicketService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if("getAll".equals(action)) {
			String memberNo = jsonObject.get("memberNo").getAsString();
			String status = jsonObject.get("status").getAsString();
			String className = jsonObject.get("className").getAsString();
			String list = gson.toJson(dao.getAll(memberNo,status,className));
			writeText(res,list);
		}
		if("isTicket".equals(action)) {
			String ticketNo = jsonObject.get("ticketNo").getAsString();
			String eventNo = jsonObject.get("eventNo").getAsString();
			writeText(res, String.valueOf(dao.isTicket(ticketNo,eventNo)));
		}
		if("findID".equals(action)) {
			String ticketNo = jsonObject.get("ticketNo").getAsString();
			writeText(res, dao.findIDbyTicket(ticketNo));
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		System.out.println(outText);
		out.close();
	}
}
