package com.onlinetutor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.onlinetutor.daoImp.PersonInfoDaoImp;
import com.onlinetutor.pojo.FeedbackPojo;
import com.onlinetutor.pojo.PersonInfo;

/**
 * Servlet implementation class SortingController
 */
@WebServlet("/SortingController")
public class SortingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getLogger(SortingController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
		 // PrintWriter out = response.getWriter();
		
			
		//to load the data on page StartUP
		ServletContext ctx = request.getServletContext();
		PersonInfoDaoImp pinfo = new PersonInfoDaoImp();
		ArrayList<PersonInfo> courseList=null;
		ArrayList<FeedbackPojo> FeedbackList=null;
		HttpSession session = request.getSession();
		//String action = request.getParameter("action");
		int search = Integer.parseInt(request.getParameter("view"));
		try {
			FeedbackList =pinfo.showFeedback();
			ctx.setAttribute("FeedbackList", FeedbackList );
			if(search == 0){
				courseList = pinfo.showTutorCourse();
				ctx.setAttribute("courseList", courseList );	
			}

			else if(search>0){
				courseList = pinfo.getBySubjectId(search);
				ctx.setAttribute("courseList", courseList );
			}
			log.info("course searched for the id ="+search);
			
			if(session.getAttribute("User") != null)
				response.sendRedirect("studentProfile.jsp");
			else
				response.sendRedirect("home.jsp");
			
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
