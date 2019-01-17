package com.utility;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_member.model.Group_memberService;
import com.group_open.model.Group_openService;
import com.group_open.model.Group_openVO;
@WebServlet(
		urlPatterns = "/Group_status_scheduleServlet", 
		loadOnStartup = 100
		)

public class Group_status_scheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Timer timer;

	public void destroy() {
		timer.cancel();
	}

	public void init() {
		timer = new Timer();
		TimerTask task = new TimerTask() {

			public void run() {
				Group_openService group_openSvc = new Group_openService();
				Group_memberService group_memberSvc = new Group_memberService();

				List<Group_openVO> list = group_openSvc.getAll();

				for (Group_openVO group_openVO : list) {
				
					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String group_closetime = format.format(group_openVO.getGroup_close_date());
					String currenttime = format.format(timestamp);
					String group_no = group_openVO.getGroup_no();

					System.out.println("開團編號" + group_openVO.getGroup_no());
					System.out.println("結束時間" + group_closetime);
					System.out.println("當下時間" + currenttime);

					System.out.println(timestamp.after(group_openVO.getGroup_close_date()));

					if (timestamp.after(group_openVO.getGroup_close_date())) {
						String productst = group_memberSvc.getproductquantity(group_no);
						System.out.println(productst);
						int product = Integer.valueOf(productst);
						System.out.println(product);
						int grouplimit = group_openVO.getGroup_limit();
						System.out.println(grouplimit);
						if (product > grouplimit) {
//							寫到如果數量大於limit開團成功  否則開團失敗
							group_openSvc.group_open_sucess(group_no);
							System.out.println("開團成功");

						} else {
//							否則開團失敗
							group_openSvc.group_open_quit(group_no);
							System.out.println("開團失敗");
						}

					}
				}
				System.out.println("Group_status_scheduleServlet is working : " + new java.util.Date());

			}

		};
		
		timer.schedule(task, 1000*60, 1000*60*60);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
