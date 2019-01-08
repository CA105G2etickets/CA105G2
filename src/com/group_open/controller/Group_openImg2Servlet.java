package com.group_open.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_open.model.Group_openService;

public class Group_openImg2Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		
		String groupno = req.getParameter("group_no");
		
		Group_openService grpSvc = new Group_openService();
		
		byte[] pic = grpSvc.getOneGroup_open(groupno).getGroup_banner_2();

		
		ServletOutputStream out = res.getOutputStream();
		
		res.setContentType("image/jpg");
		res.setContentLengthLong(pic.length);
		out.write(pic);
		out.close();
		
		
		
		
	}

}
