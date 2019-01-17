package com.ticketorder.controller;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ticketorder.model.TicketOrderService;

@WebListener
public class TicketOrderWithTicArea_HSB_Listener implements javax.servlet.http.HttpSessionBindingListener {

    public TicketOrderWithTicArea_HSB_Listener() {
    }

    public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println(new Date()+"---valueBound()方法-自動啟動---:"+event.getSession().getId());
    }
    
    public void valueUnbound(HttpSessionBindingEvent event)  { 
    	System.out.println(new Date()+"---UnBound()方法-卸載時自動啟動:"+event.getName()+"---"+event.getSession().getId());
    	
    	//these hibernate5.3 cant work, see TicketOrderHibernateDAO's comment
//    	String ticket_order_no = (String) event.getName();
//    	System.out.println(ticket_order_no+"=before cahr are bounding ticketOrderNo");
//    	TicketOrderService toSvc = new TicketOrderService();
//    	
//    	try {
//    		toSvc.cancelTargetTicketOrderByServletDueToOutDatedWithConditions(ticket_order_no);
//    		System.out.println("the target of cancel success, the ticket_order_no="+ticket_order_no);
//    	} catch( RuntimeException re) {
//    		System.out.println("the target of cancel fail, RunTimeException cause the toStatus is complete,can't be cancel");
//    	} catch( Exception e) {
//    		System.out.println("the target of cancel fail, the ticket_order_no="+ticket_order_no);
//    	}
    	
    	String ticket_order_no = (String) event.getName();
    	TicketOrderService toSvc = new TicketOrderService();
    	try {
    		String str = toSvc.cancelTicketOrderByServlet(ticket_order_no);
    		System.out.println("the target of cancel success, the ticket_order_no="+str);
    	}catch(RuntimeException re) {
    		System.out.println("the target of cancel fail, RunTimeException cause the toStatus is complete,cant be cancel");
    	}catch(Exception e) {
    		System.out.println("the target of cancel fail, the ticket_order_no="+ticket_order_no);
    	}
    	
    }
	
}
