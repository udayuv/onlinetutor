package com.onlinetutor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.onlinetutor.daoImp.TutorDaoImp;
import com.onlinetutor.daoImp.UserDaoImp;
import com.onlinetutor.pojo.Tutor;
import com.onlinetutor.pojo.User;

/**
 * Servlet implementation class RegUser
 */
@WebServlet("/RegUser")
public class RegUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static final Logger log = Logger.getLogger(RegUser.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    Boolean status = false;
		    //log.debug("Active Users: "+MySessionListener.getLoggedUsers());
		    String qual="";
		    int exp=0;
		    String uphone=request.getParameter("reg_phone");
		    String upass=request.getParameter("reg_password");
		    String userType=request.getParameter("reg_userType");
		    String securityQuestion=request.getParameter("secQues");
		    String securityAnswer=request.getParameter("secAns");
		    
		    if(userType.equals("T")){
		    qual=request.getParameter("qual");
		    exp=Integer.parseInt(request.getParameter("exp"));
		    }
		    
		    String errorMsg = null;
			if(uphone == null || uphone.equals("")){
				errorMsg = "Name can't be null or empty.";
			}
			
			if(upass == null || upass.equals("")){
				errorMsg = "Password can't be null or empty.";
			}
			
			if(userType.equals("T")){
				if(qual == null || qual.equals("")){
					errorMsg = "qual can't be null or empty.";
				}
			}
			if(errorMsg != null){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
				out.println("<font color=red>"+errorMsg+"</font>");
				rd.include(request, response);
			}
			else{
				
				System.out.println(uphone+upass+securityQuestion+securityAnswer);
		    User user = new User(uphone,upass,securityQuestion,securityAnswer);
		    Tutor tutor= new Tutor(uphone,upass,qual,exp,securityQuestion,securityAnswer);
		    UserDaoImp userdaoImp =new UserDaoImp();
		    TutorDaoImp tutdaoImp = new TutorDaoImp();
		   
				if(userType.equals("T")){
					try {
						status =tutdaoImp.regTutor(tutor);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					try {
						status =userdaoImp.regUser(user);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (status) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
					out.println("<font color=green>Registration successful, please login below.</font>");
					rd.include(request, response);
				} 
				else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
					out.println("<script type=\"text/javascript\">");
			        out.println("alert('Registration failed, Phone no is registered with us.');");
			        out.println("</script>");
			        rd.include(request, response);
				}

				
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

