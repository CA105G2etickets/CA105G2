package com.android.news.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.news.model.NewsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class NewsServlet extends HttpServlet{

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
		NewsService dao = new NewsService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if("getAll".equals(action)) {
			String search = jsonObject.get("search").getAsString();
			System.out.println(search);
			String list = gson.toJson(dao.getAll(search));
			writeText(res, list);
		}else if("findByClassification".equals(action)) {
			String classification = jsonObject.get("classification").getAsString();
			String list = gson.toJson(dao.findByClassification(classification));
			writeText(res,list);
		}
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(com.utility.Util.CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
