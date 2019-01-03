package com.administrator.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.administrator.model.AdministratorService;

public class AdministratorImageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String administrator_no = req.getParameter("administrator_no");
		AdministratorService administratorService = new AdministratorService();
		byte[] administratorPic = administratorService.getOneAdministrator(administrator_no).getAdministrator_picture();
		
		ServletOutputStream output = res.getOutputStream();
		res.setContentLengthLong(administratorPic.length);
		res.setContentType("image/*");
		output.write(administratorPic);
		output.close();
//		doPost(req,res);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, res);
	}
	

}
