package com.utill;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import st.tool.FormatEmpty;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
        HttpSession session=event.getSession();  
        ServletContext context=session.getServletContext();  
       //用set集合来存储session对象  
        HashSet<HttpSession> sessionSet=(HashSet<HttpSession>) context.getAttribute("sessionSet");  
        if(sessionSet==null){  
            sessionSet=new HashSet<HttpSession>();  
            context.setAttribute("sessionSet", sessionSet);  
        }  
       //这里主要是为了检验用户是否登录，登录的话强制移除该session，加入新session  
        for(HttpSession s : sessionSet){  
            if(session.getAttribute("userName")==s.getAttribute("userName")){  
                sessionSet.remove(s);  
            }  
        }  
        sessionSet.add(session);
         Integer count = (Integer) context.getAttribute("counter");  
        if (FormatEmpty.isEmpty(count))  
        {  
            count = new Integer(1);  
        }  
        else  
        {  
            count = new Integer(count.intValue() + 1);  
        }  
        context.setAttribute("counter", count);  
       //存储在线人数，利用了set集合不重复的特性，避免了重复登录  
        context.setAttribute("lineCount", sessionSet.size()); 
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
         // TODO Auto-generated method stub
    	 ServletContext context = event.getSession().getServletContext();  
         if (context.getAttribute("lineCount") == null) {  
             context.setAttribute("lineCount", 0);  
         } else {  
             int lineCount = (Integer) context.getAttribute("lineCount");  
             if (lineCount < 1) {  
                 lineCount = 1;  
             }  
             context.setAttribute("lineCount", lineCount - 1);  
         }  
         HttpSession session = event.getSession();  
         HashSet<HttpSession> sessionSet = (HashSet<HttpSession>)context.getAttribute("sessionSet");  
         if(sessionSet!=null){  
             sessionSet.remove(session);  
         }  
    }
	
}
