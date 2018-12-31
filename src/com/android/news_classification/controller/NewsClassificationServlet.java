package com.android.news_classification.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.android.news_classification.model.NewsClassificationService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class NewsClassificationServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = req.getReader();
		StringBuilder jsonin = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonin.append(line);
		}
		NewsClassificationService dao = new NewsClassificationService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(), JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if("getAll".equals(action)) {
			List list = dao.getAll();
			writeText(res, gson.toJson(list));
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
		out.close();
	}
}
