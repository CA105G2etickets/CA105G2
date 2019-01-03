package com.android.favorite_event.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favorite_event.model.FavoriteEventService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import oracle.net.aso.a;

public class FavoriteEventServlet extends HttpServlet{
	
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
		FavoriteEventService dao = new FavoriteEventService();
		JsonObject jsin = gson.fromJson(jsonin.toString(), JsonObject.class);
		String action = jsin.get("action").getAsString();
		System.out.println(action);
		
		if ("insert".equals(action)) {
			String memberNo = jsin.get("memberNo").getAsString();
			String evetitNo = jsin.get("evetitNo").getAsString();
			dao.addFavoriteEvent(memberNo, evetitNo);
			System.out.println("insertFavr");
		}
		if("delete".equals(action)) {
			String memberNo = jsin.get("memberNo").getAsString();
			String evetitNo = jsin.get("evetitNo").getAsString();
			dao.deleteFavoriteEvent(memberNo, evetitNo);
			System.out.println("deleteFavr");
		}
		if("isFavr".equals(action)) {
			String memberNo = jsin.get("memberNo").getAsString();
			String evetitNo = jsin.get("evetitNo").getAsString();
			writeText(res, String.valueOf(dao.isFavr(memberNo, evetitNo)));
			System.out.println(String.valueOf(dao.isFavr(memberNo, evetitNo)));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
