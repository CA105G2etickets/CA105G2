package android.com.MEMBER.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.fabric.xmlrpc.base.Member;

import android.com.MEMBER.model.MemberJDBCDAO;
import android.com.MEMBER.model.MemberVO;
import android.com.main.ImageUtil;

public class MemberServlet extends HttpServlet{
		private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
		
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
			MemberJDBCDAO memberDAO = new MemberJDBCDAO();
			JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
			String action = jsonObject.get("action").getAsString();
			
			if("isMember".equals(action)) {
				String userName = jsonObject.get("userName").getAsString();
				String userPassword = jsonObject.get("userPassword").getAsString();
				writeText(res,memberDAO.isMember(userName, userPassword));
			}else if("getImage".equals(action)) {
				OutputStream os = res.getOutputStream();
				String memberNo = jsonObject.get("memberNo").getAsString();
				int imageSize = jsonObject.get("imageSize").getAsInt();
				byte[] image = memberDAO.getImage(memberNo);
				if(image != null) {
					image = ImageUtil.shrink(image, imageSize);
					res.setContentType("image/jpeg");
					res.setContentLength(image.length);
				}
				os.write(image);
			}else if("getOne".equals(action)) {
				String memberNo = jsonObject.get("memberNo").getAsString();
				MemberVO memberVO = memberDAO.findByPrimaryKey(memberNo);
				System.out.println(memberVO);
				JsonObject memberJson = new JsonObject();
				memberJson.addProperty("memberName", memberVO.getMemberFullname());
				memberJson.addProperty("memberEmail", memberVO.getEmail());
				memberJson.addProperty("memberAccount", memberVO.getMemberAccount());
				memberJson.addProperty("memberPassword", memberVO.getMemberPassword());
				memberJson.addProperty("memberPhone", memberVO.getPhone());
				memberJson.addProperty("memberID", memberVO.getIdcard());
				memberJson.addProperty("memberWallet", String.valueOf(memberVO.getEwalletBalance()));
				writeText(res, memberJson.toString());
			}
		}
	
		private void writeText(HttpServletResponse res, String outText) throws IOException {
			res.setContentType(CONTENT_TYPE);
			PrintWriter out = res.getWriter();
			out.print(outText);
			out.close();
		}
}
