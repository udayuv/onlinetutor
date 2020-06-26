package com.onlinetutor.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAttributeListener implements ServletContextAttributeListener {

    public MyAttributeListener() {
        // TODO Auto-generated constructor stub
    }

    
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
    	System.out.println("ServletContext attribute added::{"+arg0.getName()+","+arg0.getValue()+"}");
    	   
    }

    
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
    	System.out.println("ServletContext attribute removed::{"+arg0.getName()+","+arg0.getValue()+"}");
    }

    
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
    	System.out.println("ServletContext attribute replaced::{"+arg0.getName()+","+arg0.getValue()+"}");
    	  
    }
	
}
