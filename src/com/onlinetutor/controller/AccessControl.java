package com.onlinetutor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlinetutor.daoImp.TutorDaoImp;
import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.Tutor;

/**
 * Servlet implementation class AccessControl
 */
@WebServlet("/AccessControl")
public class AccessControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		
	
		//condition to check for accept or reject
		HttpSession session = request.getSession();
		String view = request.getParameter("view");
		int cid=0,uid=0;
		
		ArrayList<PersonInfo> reqArray=null;
		TutorDaoImp tutdaoImp = new TutorDaoImp();
		Tutor tutor =(Tutor)session.getAttribute("Tutor");
		view = request.getParameter("action");
		System.out.println("action is : "+request.getParameter("action"));
		TutorDaoImp tutorDao = new TutorDaoImp();
		uid= Integer.parseInt(request.getParameter("uid"));
		cid= Integer.parseInt(request.getParameter("cid"));
		
			try {

				if(view.equals("Approve")){
				tutorDao.approveReq(tutor.getTid(),uid,cid);
				reqArray =tutdaoImp.showRequest(tutor.getTid());
				session.setAttribute("ReqList", reqArray);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/tutorProfile.jsp");
				out.println("<script type=\"text/javascript\">");
		         out.println("alert(' request has been approved !!!!');");
		         out.println("</script>");
				rd.include(request, response);
				}
				
				else if(view.equals("Reject")){
					tutorDao.rejectReq(tutor.getTid(),uid,cid);
					reqArray =tutdaoImp.showRequest(tutor.getTid());
					session.setAttribute("ReqList", reqArray);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/tutorProfile.jsp");
					out.println("<script type=\"text/javascript\">");
			         out.println("alert(' request has been rejected !!!!');");
			         out.println("</script>");
					rd.include(request, response);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
