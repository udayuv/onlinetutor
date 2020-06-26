package com.onlinetutor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinetutor.daoImp.UserDaoImp;
import com.onlinetutor.pojo.User;

/**
 * Servlet implementation class RequestControl
 */
@WebServlet("/RequestControl")
public class RequestControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		
		//to check if student has access or not
		HttpSession session = request.getSession();
		User user =(User)session.getAttribute("User");
		String view = request.getParameter("action");

		int tid= Integer.parseInt(request.getParameter("tid"));
		int cid= Integer.parseInt(request.getParameter("cid"));
		 session.setAttribute("TID", tid);
		UserDaoImp userDao = new UserDaoImp();
		String status ="",msg="";
		boolean isReq;

		if(user != null){
			if(view.equals("View"))
			{
				
				try {
					status=userDao.checkRequest(user.getUid(), tid,cid);
					 System.out.println(status);
					 if(status.equals("pending")){
						 RequestDispatcher rd = getServletContext().getRequestDispatcher("/studentProfile.jsp");
						 out.println("<script type=\"text/javascript\">");
				         out.println("alert('Your request is in pending state. Please wait for tutor approval.');");
				         out.println("</script>");
				         rd.include(request, response);
					 }
				         else if(status.equals("approved")){
				        	 int a[] ={tid,cid};
				        	 System.out.println("array value: "+a[0]+", "+a[1]);
				        	 session.setAttribute("feedback", a);
				        	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/view.jsp");
					         rd.include(request, response);
				         }
				         
				         else if(status.equals("rejected")){
				        	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/studentProfile.jsp");
							 out.println("<script type=\"text/javascript\">");
					         out.println("alert('Your request has been rejected. You cannot view this page.');");
					         out.println("</script>");
					         rd.include(request, response); 
				         }
					 
					 else{
						 //isReq= userDao.sendReq(user.getUid(),tid,cid);
						 RequestDispatcher rd = getServletContext().getRequestDispatcher("/studentProfile.jsp");
						 out.println("<script type=\"text/javascript\">");
				         out.println("alert('You need to send request for this article. Click on Subscribe');");
				         out.println("</script>");
				         rd.include(request, response);
						// System.out.println("Request raised: "+isReq);
					 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(view.equals("Subscribe")){
				 try {
					 status=userDao.checkRequest(user.getUid(), tid,cid);
					 if(status.equals("pending") || status.equals("approved")){
						 msg="alert('You have already sent a request, check request status in approvals.');";
						 returnPage(msg, request, response);
					 }
					 
					 else if(status.equals("rejected")){
						 msg="alert('You are not allowed to send request again. Your requesst has been rejected.');";
						 returnPage(msg, request, response);
					 }
					 
					 else {
						 isReq= userDao.sendReq(user.getUid(),tid,cid);
						 msg="alert('Your request has been sent to the tutor for approval.');";
						 returnPage(msg, request, response);
					 }
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
			out.println("<font color=red>"+"Please Login First "+"</font>");
			rd.include(request, response);
		}
		
	}
	
	private void returnPage(String msg ,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/studentProfile.jsp");
		out.println("<script type=\"text/javascript\">");
        out.println(msg);
        out.println("</script>");
        rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
