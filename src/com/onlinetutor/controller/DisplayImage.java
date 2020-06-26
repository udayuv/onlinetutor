package com.onlinetutor.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinetutor.dao.UserDao;
import com.onlinetutor.daoImp.UserDaoImp;

/**
 * Servlet implementation class DisplayImage
 */
@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDaoImp ud=new UserDaoImp();
		  int showFile=Integer.parseInt(request.getParameter("showFile")); 
		 HttpSession session = request.getSession();
		 int tid=(int) session.getAttribute("TID");
		 System.out.println("TID------------->"+ session.getAttribute("TID"));
/*		  int showFile=Integer.parseInt(request.getParameter("filetype")); 
*/
        ServletOutputStream sos;
        if(showFile==1){
       	 response.setContentType("image/jpeg");
         sos = response.getOutputStream();
       
           	 		byte data[];
					try {
						data = ud.displayAlldata(1, tid);
						sos.write(data);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
           	 
       sos.flush();
       sos.close();
        
	
	}/*end of  if  fileimage*/
	if(showFile==2)
	{
		
	        response.setContentType("application/pdf");
	 
	       // response.setHeader("Content-disposition","inline; filename="+bookId+".pdf" );
	        response.setHeader("Content-disposition","inline; filename="+""+".pdf" );
	         sos = response.getOutputStream();
	          ResultSet rset=null;
	          byte data[];
				try {
					data = ud.displayAlldata(2, tid);
					sos.write(data);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        sos.flush();
	        sos.close();
	         
	    }
		
	}
	

}

