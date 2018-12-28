package android.com.EVENT_TITLE.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import android.com.EVENT_TITLE.model.Event_titleService;
import android.com.EVENT_TITLE.model.Event_titleVO;
import android.com.MEMBER.model.MemberService;
import android.com.MEMBER.model.MemberVO;
import android.com.main.ImageUtil;

public class Event_titleServlet extends HttpServlet {
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
		Event_titleService dao = new Event_titleService();
		JsonObject jsonObject = gson.fromJson(jsonin.toString(),JsonObject.class);
		String action = jsonObject.get("action").getAsString();
		
		if("getAll".equals(action)) {
			String list = gson.toJson(dao.getAll());
			writeText(res, list);
		}else if("getImage".equals(action)) {
			OutputStream os = res.getOutputStream();
			String evetit_no = jsonObject.get("No").getAsString();
			System.out.println(evetit_no);
			int imageSize = jsonObject.get("imageSize").getAsInt();
			byte[] image = dao.getImage(evetit_no);
			if(image != null) {
				image = ImageUtil.shrink(image, imageSize);
				res.setContentType("image/jpeg");
				res.setContentLength(image.length);
			}
			try {
			os.write(image);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(os != null) {
					os.close();
				}
			}
		}
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
		res.setContentType(CONTENT_TYPE);
		PrintWriter out = res.getWriter();
		out.print(outText);
		out.close();
	}
}
