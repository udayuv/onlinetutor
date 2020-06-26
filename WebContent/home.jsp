
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.onlinetutor.pojo.*, java.util.ArrayList"%>
<%@include file="header.html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
</head>
<body>

<div class="container">
    <div class="row">
    
    
     <div class="col-md-3">
   		 <div class="divArticle card bg-light mb-3" style="max-width: 18rem;">
  			<div class="card-header">Login Here</div>
  				<div class="card-body text-dark">
    				<form id="in_login_form " action="LoginControl" method="get" >
        
                   <div class="form-group">
                       <input type="text" class="form-control" id="phone" name="phone" required="" placeholder="Mobile Number">
                   </div>

                   <div class="form-group">
                       <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
                   </div>

                   <div class="form-group">
                    	<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline1" name="userType" class="custom-control-input" value="T" required>
						  <label class="custom-control-label" for="customRadioInline1">Tutor</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
						  <input type="radio" id="customRadioInline2" name="userType" class="custom-control-input" value="S" required>
						  <label class="custom-control-label" for="customRadioInline2">Student</label>
						</div>
                   </div>

                   <div class="form-group ">
                       <button type="submit" class="btn btn-primary btn-block btn-sm">Login</button> <br>
                       Forgot Password?<a href=# data-toggle="modal" data-target="#exampleModalCenter">Click here</a><br>
                       
                        New User?<a href=# data-toggle="modal" data-target="#exampleModal">Register</a> 
                   </div>
               </form>          

  				</div>
		</div>
	</div>
         
        <div class=" col-md-9 ">
				<div  class="row divMarginTop">
				<select id="mySelect" class="custom-select custom-select-lg mb-3" onchange="myFunction()">
				  <option selected>Search Course By Name</option>
				  <option value="0">All Courses</option>
				  <option value="1">Java</option>
				  <option value="2">JavaScript</option>
				  <option value="3">MySql</option>
				</select>
				</div>
				
				<script>
				var x=0;
					function myFunction() {
						
					    x = document.getElementById("mySelect").value;
					    document.location.href = '${pageContext.request.contextPath}/SortingController?view='+x;
					}
					
				</script>
            
                
                <div class="divMarginTop">
                 <%request.getRequestDispatcher("SortingController").include(request,response);%> 
               <%--  <%request.getRequestDispatcher("FeedbackControl").include(request,response); %> --%>
                <div class="divMarginTop">
                <c:forEach items="${applicationScope['courseList']}" var="course">
                 <%-- <%ArrayList<FeedbackPojo> FeedList=(ArrayList<FeedbackPojo>)application.getAttribute("FeedbackList"); %> --%>
                    <div class="card floatLeft shadow  mb-5 bg-white rounded" style="width: 20rem;">
                        <div class="card-body">
                            <h1 class="card-title">${course.cname}</h1>
                            <blockquote class="blockquote mb-0">
                                <footer class="blockquote-footer">given by <b >${course.fname}</b></footer>
                                </blockquote>
                                <h5 class="card-title">Comments</h5>
                                <c:forEach items="${applicationScope['FeedbackList']}" var="feedback">
                                <div class="">
									  <div class="card-body">
									  <h6 class="card-subtitle mb-2 text-muted"> ${feedback.fname} &nbsp;  ${feedback.lname}</h6>
    									<p class="card-text">&nbsp; &nbsp; ${feedback.comments}</p>
									   
									  </div>
									</div>
               					</c:forEach>
                            <br>
                            <form action="RequestControl" method="post">
                                <input type="hidden" name="tid" value=${course.tid } >
                                <input type="hidden" name="cid" value=${course.cid } >
                                <input type="submit" class="btn btn-outline-info" name="view" value="View">
                            </form>
                        </div>
                    </div>
                </c:forEach>
                </div>
        </div>
    </div>
  </div>
                </div>
        </div>
    </div>
  </div>
  
      
            <!--Change Password Modal -->
      <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-body">
                    <div class="container-fluid">
                            <div class="row">
                            <button type="button" class="close ml-auto" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                    </button>
                            </div>
                      <div class="row">
                       

                        <div class="col">
                                <h6 class="modal-title" id="exampleModalCenterTitle">Reset Password</h6><br>
                                <form action="UpdateInfoController" method="post">
                                    
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="cp_phone" name="cp_phone" placeholder="Mobile Number">
                                        </div>

		                        <div class="form-group">
		                            <select name="secQues" id="secQues" class="form-control form-control-sm">
		                            
		                            <option value= "">Choose Security Question</option>
		                            <option value= "BirthPlace">Insert Your Birth Place</option>
		                            <option value= "PetName">Insert Your Pet Name</option>
		                            </select><br>
		                       <input type="text" class="form-control" id="secAns" name="secAns" required="" placeholder="Security Question's Answer">
		                        </div>
		                        
				         <div class="form-group">
                            <input type="password" class="form-control" id="pass" name="pass" placeholder="Password">
                        </div>
    
                        <div class="form-group">
                            <input type="password" class="form-control" id="conf_pass" name="conf_pass" required="" placeholder="Confirm Password">
                        </div>
										
										<div class="form-group">
				                            <input type="radio" id="tutor" name="cp_userType"  value="T">
				                            <label >Tutor</label>
				                            
											&nbsp;&nbsp;
				                            <input type="radio" id="student" name="cp_userType" value="S">
				                            <label >Student</label>
				                        </div>
                                       
                                        <div class="form-group ">
                                            <button name="update" value="resetPassword" type="submit" class="btn btn-primary btn-block">RESET PASSWORD</button>
                                        </div>
                                </form>
                        </div>
                      </div>
                    </div>
                  </div>				
          </div>
        </div>
      </div>
</body>
</html>
