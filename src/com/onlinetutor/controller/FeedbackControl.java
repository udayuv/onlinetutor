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

import org.apache.log4j.Logger;

import com.onlinetutor.daoImp.PersonInfoDaoImp;
import com.onlinetutor.daoImp.UserDaoImp;
import com.onlinetutor.pojo.Tutor;
import com.onlinetutor.pojo.User;

/**
 * Servlet implementation class FeedbackControl
 */
@WebServlet("/FeedbackControl")
public class FeedbackControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getLogger(FeedbackControl.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    
	    UserDaoImp userDao= new UserDaoImp();
	    int likes = Integer.parseInt(request.getParameter("rating"));
	    System.out.println("rating: "+request.getParameter("rating"));
	    String comments = request.getParameter("comment");
	    HttpSession session = request.getSession();
	    User user=(User)session.getAttribute("User");
	    int a[] = (int[])session.getAttribute("feedback");
	    boolean status= false;
	    String msg="";
	    try {
			status = userDao.submitFeedback(user.getUid(),a[0],a[1],likes,comments);
			if(status)
				msg= "alert('Feedback Submitted Successfully..');";
			else
				msg= "alert('Feedback submission FAILED..');";
			
			session.removeAttribute("feedback");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/view.jsp");
			 out.println("<script type=\"text/javascript\">");
	         out.println(msg);
	         out.println("</script>");
	         rd.include(request, response);
				
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
