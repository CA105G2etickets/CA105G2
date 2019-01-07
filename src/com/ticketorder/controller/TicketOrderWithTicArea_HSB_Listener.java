package com.ticketorder.controller;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class TicketOrderWithTicArea_HSB_Listener implements javax.servlet.http.HttpSessionBindingListener {

    public TicketOrderWithTicArea_HSB_Listener() {
        // TODO Auto-generated constructor stub
    }

    synchronized public void valueBound(HttpSessionBindingEvent event)  { 
    	System.out.println(new Date()+"---valueBound()方法-自動啟動"+event.getSession().getAttribute("CreatedTicketOrderNo"));
    	String ticket_order_no = (String) event.getSession().getAttribute("CreatedTicketOrderNo");
    	
    }
    public void valueUnbound(HttpSessionBindingEvent event)  { 
//    	System.out.println(new Date()+"---UnBound()方法-自動啟動"+event.getName()+"---"+event.getSession().getAttribute("CreatedTicketOrderNo"));
    	System.out.println(new Date()+"---UnBound()方法-自動啟動"+event.getName()+"---"+event.getSession().getId());
    	
    }
	
}
