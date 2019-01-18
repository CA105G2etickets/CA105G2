package com.ticket.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ticket.model.*;

/**
 * Servlet implementation class TicketOrderServlet
 */
//@WebServlet("/TicketOrderServlet")
public class TicketBackendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static boolean checkInputValueAtAddTicketNo = false;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String member_no = req.getParameter("member_no");
		
		
		
	}
	
	public boolean checkInputTicketOrderNo(String str) {
		String strTest = str.substring(str.length()-1, str.length());
		Integer iTest = Integer.parseInt(strTest);
		if(iTest<0) {
			return false;
		}else if (iTest >3) {
			return false;
		}else {
			return true;
		}
	}
	public boolean containsHanScript(String s) {
	    for (int i = 0; i < s.length(); ) {
	        int codepoint = s.codePointAt(i);
	        i += Character.charCount(codepoint);
	        if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
	            return true;
	        }
	    }
	    return false;
	}

}
