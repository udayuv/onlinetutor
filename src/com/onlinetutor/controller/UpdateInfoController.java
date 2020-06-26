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
import com.onlinetutor.daoImp.TutorDaoImp;
import com.onlinetutor.daoImp.UserDaoImp;
import com.onlinetutor.pojo.Tutor;
import com.onlinetutor.pojo.User;

/**
 * Servlet implementation class UpdateInfoController
 */
@WebServlet("/UpdateInfoController")
public class UpdateInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger log = Logger.getLogger(RegUser.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoController() {
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
		    boolean flag=false;
		    PersonInfoDaoImp pinfo = new PersonInfoDaoImp();
		    String chkReq=request.getParameter("update");
		    int id=0;
		    
		    String fname,lname,email,gender,phone,navPage="",curr_pwd,new_pwd;
		    String secQues,secAns;
		    HttpSession session = request.getSession();
		    User user=null;
		    Tutor tutor=null;
		    UserDaoImp daoImp =new UserDaoImp();
		    TutorDaoImp tutdaoImp = new TutorDaoImp();
		   
		    if(session.getAttribute("Tutor") != null){
		    	tutor= (Tutor)session.getAttribute("Tutor");
		    	id= tutor.getTid();
		    	
		    	//navPage="tutorProfile";
		    }
		    
		    else if(session.getAttribute("User") != null){
		    	user= (User)session.getAttribute("User");
		    	id= user.getUid();
		    	
		    	//navPage="studentProfile";
		    }
		    
		    if(chkReq.equals("piInfo") || chkReq.equals("updateEmail") || chkReq.equals("updateMobile")){
		    // to update personal informations
		    if(chkReq.equals("piInfo"))
		    {

		    	fname =request.getParameter("fname");
		    	lname =request.getParameter("lname");
		    	gender =request.getParameter("gender");
		    	try {
					status = pinfo.updatePersonInfo(id, fname, lname, gender);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
		    }//ends of personal info if
		    
		    // to update the email address
		    else if(chkReq.equals("updateEmail")){
		    	email=request.getParameter("email");
		    	try {
					status = pinfo.updateEmail(id, email);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		    }
		    
		    // to update the mobile number
		    else if(chkReq.equals("updateMobile")){
		    	phone=request.getParameter("phone");
		    	try {
					status = pinfo.updateMobile(id, phone);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		    
		    try {
				
				if (status) {
					if(tutor!= null){
				    	navPage="tutorProfile";
				    	System.out.println("---OLD-----"+tutor+"---------");
						session.setAttribute("Tutor", tutdaoImp.getTutorDetails(tutor.getPhone(), tutor.getPassword()));
						System.out.println("--------"+(Tutor)session.getAttribute("Tutor")+"---------");
					}
					else if(user!=null){
				    	navPage="studentProfile";
				    	System.out.println("---OLD-----"+user+"---------");
						session.setAttribute("User", daoImp.getUserDetails(user.getPhone(), user.getPassword()));
						System.out.println("--------"+(User)session.getAttribute("User")+"---------");
					}
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+navPage+".jsp");
					out.println("<font color=green>UPdation successful,.</font>");
					rd.include(request, response);
				} else {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+navPage+".jsp");
					out.println("<font color=red>Updation failed,.</font>");
					rd.include(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    }
		    //to update the password
		    else if(chkReq.equals("changePassword")){
		    	curr_pwd =request.getParameter("curr_pass");
		    	new_pwd =request.getParameter("new_pass");
		    	try {
		    		if(tutor!=null){
		    			flag = tutdaoImp.updatePassword(id, curr_pwd,new_pwd);
		    			navPage="tutorProfile";
		    		}
		    		
		    		else if(user!=null){
		    			flag = daoImp.updatePassword(id, curr_pwd,new_pwd);
		    			navPage="studentProfile";
		    		}
		    		
		    		if(flag){
		    			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
						out.println("<font color=green>Password has been succesfully updated,Please login again.</font>");
						rd.include(request, response);
		    		}
		    		else{
		    			RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+navPage+".jsp");
						out.println("<font color=red>Updation failed.</font>");
						rd.include(request, response);
		    		}
		    		

				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		    
		//to reset the password
		    else if(chkReq.equals("resetPassword")){
		    	phone =request.getParameter("cp_phone");
		    	navPage="home";
		    	String userType =request.getParameter("cp_userType");
		    	secQues = request.getParameter("secQues");
		    	secAns = request.getParameter("secAns");
		    	curr_pwd =request.getParameter("pass");
		    	new_pwd =request.getParameter("conf_pass");
		    	try {
	    			System.out.println("-------------------------------------------------");
	    			System.out.println("PHONE" +phone);
	    			user = daoImp.getUserDetails(phone);
	    			System.out.println(user);
	    			
	    			System.out.println(user.getSecret_question() + secQues + user.getSecret_answer() + secAns);
	    			
	    		if((user.getSecret_question().equals(secQues)) && (user.getSecret_answer().equals(secAns)) && curr_pwd.equals(new_pwd))
	    			{
	    			id = user.getUid();
	    				System.out.println("Matched");
	    				System.out.println( id+curr_pwd + new_pwd);
		    			if(userType.equals("T")){
		    				
		    			flag = tutdaoImp.resetPassword(id, curr_pwd,new_pwd);
		    			}
		    			else{
				    		flag = daoImp.resetPassword(id, curr_pwd,new_pwd);
				    	}
	    			}
	    		else{
	    			out.println("Your Security Question or Password and Confirm Pssword Didn't Match");
	    		}
	    			if(flag){
	    				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
						out.println("<font color=green>Password Reset successful,.</font>");
						rd.forward(request, response);
	    			}
	    			else{
	    				RequestDispatcher rd = getServletContext().getRequestDispatcher("/"+navPage+".jsp");
						out.println("<font color=red>Incorrect credentials.</font>");
						rd.forward(request, response);
	    			}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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

