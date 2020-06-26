package com.onlinetutor.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.onlinetutor.daoImp.TutorDaoImp;
import com.onlinetutor.daoImp.UserDaoImp;
import com.onlinetutor.listener.MySessionListener;
import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.Tutor;
import com.onlinetutor.pojo.User;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	static final Logger log = Logger.getLogger(LoginControl.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    User user=null;
	    Tutor tutor=null;
	    PersonInfoDaoImp pinfoDao= new PersonInfoDaoImp();
	    String phone=request.getParameter("phone");
	    String pass=request.getParameter("password");
	    String userType=request.getParameter("userType");
	    String errorMsg = null;
	    
	    ServletContext ctx = request.getServletContext();
	    
		if(phone == null || phone.equals("")){
			errorMsg = "phone can't be 0 or empty.";
		}
		if(pass == null || pass.equals("")){
			errorMsg = "Password can't be null or empty.";
		}
		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}
		else{
	    //getting connection
	    //Connection con = (Connection) getServletContext().getAttribute("DBConnection");
	    UserDaoImp daoImp =new UserDaoImp();
	    TutorDaoImp tutdaoImp = new TutorDaoImp();
	    ArrayList<PersonInfo> reqArray=null, uploadCourse=null, apprCourseList=null;
	    request.getSession();
		HttpSession sessionObj = request.getSession(true);
		sessionObj.setMaxInactiveInterval(60);
		log.info("Current Active Users: "+MySessionListener.getLoggedUsers());
		
		if(userType.equals("T")){
			try {
				tutor =tutdaoImp.getTutorDetails(phone, pass);
				reqArray =tutdaoImp.showRequest(tutor.getTid());
				uploadCourse = pinfoDao.showUploadedCourse(tutor.getTid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(phone+", pass: "+pass);
			if(tutor!=null){
				log.info("User found with details="+tutor);
				System.out.println(reqArray);
				HttpSession session = request.getSession();
				session.setAttribute("Tutor", tutor);
				session.setAttribute("ReqList", reqArray);
				ctx.setAttribute("UploadCourseList", uploadCourse );
				response.sendRedirect("tutorProfile.jsp");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				log.error("Tutor not found with phone="+phone);
				out.println("<font color=red>No tutor found with given phone, please register first.</font>");
				rd.include(request, response);
			}
		}
		else{
			try {
				user =daoImp.getUserDetails(phone, pass);
				reqArray =daoImp.showApprovals(user.getUid());
				apprCourseList = pinfoDao.showApprovedCourse(user.getUid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(phone+", pass: "+pass);
			if(user!=null){
				log.info("User found with details="+user);
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				session.setAttribute("ReqList", reqArray);
				ctx.setAttribute("ApprCourseList", apprCourseList );
				response.sendRedirect("studentProfile.jsp");
			}else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				log.error("User not found with phone="+phone);
				out.println("<font color=red>No student found with given phone, please register first.</font>");
				rd.include(request, response);
			}
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

