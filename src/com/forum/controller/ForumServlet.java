package com.forum.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.forum.model.ForumService;
import com.forum.model.ForumVO;

public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		

//		/-------------------------根據討論區編號查詢單一欄位-------------------------/
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String forum_no = req.getParameter("forum_no");
				if (forum_no == null || (forum_no.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				ForumService forumSvc = new ForumService();
				ForumVO forumVO = forumSvc.getOneForum(forum_no);
				if (forumVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("forumVO", forumVO);
				String url = "/forum/listforum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/group_open/group_open_index.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String forum_no = req.getParameter("forum_no");
				/*************************** 2.開始查詢資料 ****************************************/
				ForumService forumSvc = new ForumService();
				ForumVO forumVO = forumSvc.getOneForum(forum_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("forumVO", forumVO);
				String url = "/forum/updateforum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/forum/listallforum.jsp");
				failureView.forward(req, res);

			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String forum_no = req.getParameter("forum_no");
				if (forum_no == null || forum_no.trim().length() == 0) {
					errorMsgs.add("留言編號請勿空白");
				}
				String group_no = req.getParameter("group_no");
				if (forum_no == null || forum_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				String member_no = req.getParameter("member_no");
				if (forum_no == null || forum_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String forum_content = req.getParameter("forum_content");
				if (forum_no == null || forum_no.trim().length() == 0) {
					errorMsgs.add("討論區留言請勿空白");
				}
				java.sql.Timestamp forum_time = null;

				forum_time = new java.sql.Timestamp(System.currentTimeMillis());
				ForumVO forumVO = new ForumVO();
				forumVO.setForum_no(forum_no);
				forumVO.setGroup_no(group_no);
				forumVO.setMember_no(member_no);
				forumVO.setForum_content(forum_content);
				forumVO.setForum_time(forum_time);
				// 如果錯誤的話要返回
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/forum/updateforum.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 ****************************************/
				ForumService forumSvc = new ForumService();

				forumVO = forumSvc.updateForumVO(forum_no, group_no, member_no, forum_content, forum_time);
				/*************************** 3.修改完成,準備轉交(Send the Success view) ************/
				req.setAttribute("forumVO", forumVO);
				String url = "/frontend/forum/listallforum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/forum/update_group_member.jsp");
				failureView.forward(req, res);

			}

		}
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String group_no = req.getParameter("group_no");
				if (group_no == null || group_no.trim().length() == 0) {
					errorMsgs.add("開團編號請勿空白");
				}
				String member_no = req.getParameter("member_no");
				if (member_no == null || member_no.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				String forum_content = req.getParameter("forum_content");
				if (forum_content == null || forum_content.trim().length() == 0) {
					errorMsgs.add("討論區留言請勿空白");
				}
				java.sql.Timestamp forum_time = null;

				forum_time = new java.sql.Timestamp(System.currentTimeMillis());

				ForumVO forumVO = new ForumVO();
				forumVO.setGroup_no(group_no);
				forumVO.setForum_content(forum_content);
				forumVO.setMember_no(member_no);
				forumVO.setForum_time(forum_time);
				System.out.println(forum_content);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("forumVO", forumVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/forum/addforum.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				ForumService forumSvc = new ForumService();
				forumVO = forumSvc.addForum(group_no, member_no, forum_content, forum_time);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/frontend/forum/listallforum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/forum/addforum.jsp");
				failureView.forward(req, res);

			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String forum_no = req.getParameter("forum_no");
				/*************************** 2.開始刪除資料 ***************************************/
				ForumService forumSvc = new ForumService();
				forumSvc.deleteForum(forum_no);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/forum/listallforum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/forum/listallforum.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getall_forum_by_group".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String group_no = req.getParameter("group_no");
				if (group_no == null || (group_no.trim().length() == 0)) {
					errorMsgs.add("請輸入會員編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/group_open_index.jsp");
					failureView.forward(req, res);
					return;// 程式中斷 這裡沒有寫輸入格式錯誤
				}
				/************************ 開始查詢資料 *****************************/

				ForumService forumSvc = new ForumService();

				List<ForumVO> getall_forum_by_group = forumSvc.getall_forum_by_group(group_no);

				if (getall_forum_by_group == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/group_open_index.jsp");

					failureView.forward(req, res);
					return;// 程式中斷 這裡沒有寫輸入格式錯誤
				}

				/************************ 查詢完成 *****************************/
				req.setAttribute("getall_forum_by_group", getall_forum_by_group);

				String url = "/forum/listforum.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/group_open_index.jsp");
				failureView.forward(req, res);
			}

		}
		/************************ 跟團訂單裡面的討論區 *****************************/
		if ("ask".equals(action)) {
			
			JSONObject obj = new JSONObject(); // 傳送json文件
			try {
		/*************************** 2.取得參數 ***************************************/	
				String forum_content = req.getParameter("content");// 討論區發文用
				String group_no = req.getParameter("group_no");
				String member_no= req.getParameter("member_no");
				System.out.println(group_no);
				System.out.println(member_no);
				System.out.println(forum_content);
				java.sql.Timestamp forum_time = null;
				forum_time = new java.sql.Timestamp(System.currentTimeMillis());
		/*************************** 2.開始新增資料 ***************************************/	
				ForumService forumSvc = new ForumService();
				ForumVO forumVO = forumSvc.addForum(group_no, member_no, forum_content, forum_time);
				
		/*************************** 3.開始轉交資料 ***************************************/		
				
				obj.put("answer", forum_content);
				res.setCharacterEncoding("UTF-8");
				System.out.println(forum_content);
				System.out.println(action);
				out.write(obj.toString());
				out.flush();
				out.close();

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}
}
