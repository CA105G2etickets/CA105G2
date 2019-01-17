package com.group_open.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_open.model.Group_openService;
import com.group_open.model.Group_openVO;

public class Group_openImg2Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");

		String groupno = req.getParameter("group_no");
		System.out.println("Group_ImageServlet2" + groupno);

		Group_openService grpSvc = new Group_openService();

		ServletContext context = getServletContext();

		InputStream fis = context.getResourceAsStream("/NoData/imageComingSoon.jpg");

		byte[] pic = null;

		Group_openVO group_openVO = new Group_openVO();

		group_openVO = grpSvc.getOneGroup_open(groupno);

		if (group_openVO == null) {

			pic = new byte[fis.available()];
			for (int i = 0; i < pic.length; i++) {
				pic[i] = (byte) fis.read();
			}

		} else {

			pic = grpSvc.getOneGroup_open(groupno).getGroup_banner_2();

		}

		ServletOutputStream out = res.getOutputStream();

		res.setContentType("image/*");
		res.setContentLengthLong(pic.length);
		out.write(pic);
		out.close();
		fis.close();

	}

}
