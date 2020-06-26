package com.onlinetutor.listener;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import com.onlinetutor.dbUtils.DBConnector;

/**
 * Application Lifecycle Listener implementation class MyContextListener
 *
 */
@WebListener
public class MyContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public MyContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletevent)  { 
  
    	
		ServletContext ctx =servletevent.getServletContext();
		//getting parameter of database
		String dbURL = ctx.getInitParameter("dbURL");
    	String user = ctx.getInitParameter("dbUser");
    	String pwd = ctx.getInitParameter("dbPassword");
    	
    	try {
			DBConnector connectionManager = new DBConnector(dbURL, user, pwd);
			ctx.setAttribute("DBConnection", connectionManager.getConnection());
			System.out.println("DB Connection initialized successfully.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	//getting path for logger and configuring it
		String log4jFile = ctx.getInitParameter("log4j-config-location");
		String fullPath= ctx.getRealPath("")+File.separator+log4jFile;
		PropertyConfigurator.configure(fullPath);
    	
    	
		
		System.out.println("Value saved in context.");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletevent)  { 
      	
    	Connection con = (Connection) servletevent.getServletContext().getAttribute("DBConnection");
    	try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
}


