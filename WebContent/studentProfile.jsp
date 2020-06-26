<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.onlinetutor.pojo.*, java.util.ArrayList"%>
 <%@include file="header.html" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!------ Include the above in your HEAD tag ---------->
    <title>Document</title>

</head>
<body>
<div class="container">
<div class="row divArticle">
    <div class="col-12">
        <div class="float-left ">
            <i class="fa fa-arrow-circle-left fa-2x" aria-hidden="true"></i>&nbsp;
        </div>
        <div class=" float-right">
            <i class="fa fa-arrow-circle-right fa-2x" aria-hidden="true"></i>
        </div>  
    </div>
  </div>

    <div class="row">
        <div class="divArticle col-md-3 ">
            <% User user = (User)session.getAttribute("User"); %>
		<div class="card shadow  mb-5 bg-white rounded" style="max-width: 18rem;">
			  <img class="card-img-top customImage" src="IMAGES/user.png" alt="Card image cap">
			              <!-- personal information -->
			  <div class="card-body">
			    <h5 class="card-title text-center"><b class="text-uppercase" ><%= user.getFname() %></b></h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
					<li class="list-group-item "><i class="fa fa-user" aria-hidden="true"></i>&nbsp; <%= user.getFname() %> &nbsp; <%= user.getLname() %></li>
			    	<li class="list-group-item"><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp; <%= user.getEmail() %></li>
			    	<li class="list-group-item"><i class="fa fa-phone" aria-hidden="true"></i>&nbsp; <%= user.getPhone() %></li>
			     	<li class="list-group-item"><i class="fa fa-address-card" aria-hidden="true"></i>&nbsp; <%= user.getAddress() %></li>
			  	<li class="list-group-item">
                    <form action="LogoutControl" method="post">
                    <button type="submit"  class="btn btn-link" value="Logout" ><i class="fa fa-power-off" aria-hidden="true"></i>&nbsp;Logout</button></form>
              	</li>
  			  </ul>
	</div>
        </div>
        
        <div class=" col-md-9 ">
          <div class="card-body">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Course</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile Update</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Approvals</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="myCourse-tab" data-toggle="tab" href="#myCourse" role="tab" aria-controls="myCourse" aria-selected="false">Approved Course</a>
                </li>
              </ul>

              <div class="tab-content" id="myTabContent">
                <!-- to Show all Courses -->
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <%-- <%request.getRequestDispatcher("SortingController").include(request,response);%> --%>
                        
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
                        <c:forEach items="${applicationScope['courseList']}" var="course">
                        
                            <div class="card floatLeft shadow  mb-5 bg-white rounded" style="width: 20rem;">
                                <div class="card-body">
                                    <h1 class="card-title">${course.cname}</h1>
                                    <blockquote class="blockquote mb-0">
                                        <footer class="blockquote-footer">given by <b >${course.fname}</b></footer>
                                    </blockquote>
                                      <br>
                                      <c:forEach items="${applicationScope['FeedbackList']}" var="feedback">
                                <div class="">
									  <div class="card-body">
									  <h6 class="card-subtitle mb-2 text-muted"> ${feedback.fname} &nbsp;  ${feedback.lname}</h6>
    									<p class="card-text">&nbsp; &nbsp; ${feedback.comments}</p>
									   
									  </div>
									</div>
               					</c:forEach>
                                    <form action="RequestControl" method="post">
                                        <input type="hidden" name="tid" value=${course.tid } >
                                        <input type="hidden" name="cid" value=${course.cid } >
                                        <input type="submit" class="cbutton" name="action" value="View">
                                        <input type="submit" class="cbutton" name="action" value="Subscribe" >
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                       </div>
                </div>

                <!-- Profile Updation -->
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="divMarginTop">
                   
					 <!-- Personal Information Update -->
                        <br><strong>Personal Information</strong>
                        <button id="enablePI" type="submit" class="btn btn-link" >Edit</button><br>     
                        <form id="update_pi" action="UpdateInfoController" method="post">
                                <div class="form-row">
                                  <div class="form-group col-md-5">
                                    <input type="text" class="form-control" id="fname" name="fname" disabled="disabled" value="<%= user.getFname() %>">
                                  </div>
                                  <div class="form-group col-md-5">
                                    <input type="text" class="form-control" id="lname" name="lname" disabled="disabled" value="<%= user.getLname() %>">
                                  </div>
  
                                  <div id="pi_save" class="form-group col-md-2">
                                      <button name="update" value="piInfo"  type="submit" class="btn btn-primary">Save</button>
                                    </div>    
  
                                </div>
  
                                <div class="form-group">
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline1" name="gender" class="custom-control-input" disabled="disabled" value="M">
                                        <label class="custom-control-label" for="customRadioInline1">Male</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <input type="radio" id="customRadioInline2" name="gender" class="custom-control-input" disabled="disabled" value="F">
                                        <label class="custom-control-label" for="customRadioInline2">Female</label>
                                    </div>
                                </div>
                          
                        </form>
                              
                        <!-- Email Update -->
                        <br><strong>Email Address</strong>
                        <button id="enableEa" class="btn btn-link" >Edit</button> &nbsp;&nbsp;
                        <button id="enableMo" class="btn btn-link" data-toggle="modal" data-target="#exampleModalCenter" >Change Password</button><br>
                        <form id="update_em" action="UpdateInfoController" method="post">
                                      <div class="form-row">
                                        <div class="form-group col-md-5">
                                          <input type="email" class="form-control" id="email" name="email" disabled="disabled" value="<%= user.getEmail() %>">
                                        </div>
                                        <div id="email_save" class="form-group col-md-2">
                                            <button name="update" value="updateEmail" type="submit" class="btn btn-primary">Save</button>
                                          </div>    
        
                                      </div>
                                
                        </form>
                              
                          <!-- Mobile Update -->
                        <br><strong>Mobile Number</strong>
                        <button id="enableMob" class="btn btn-link" >Edit</button><br>
                        <form id="update_mo" action="UpdateInfoController" method="post">
                              <div class="form-row">
                                    <div class="form-group col-md-5">
                                      <input type="text" class="form-control" id="phone" disabled="disabled" name="phone" value="<%= user.getPhone() %>">
                                    </div>
                                    <div id="mobile_save" class="form-group col-md-2">
                                        <button name="update" value="updateMobile type="submit" class="btn btn-primary">Save</button>
                                      </div>    
                                  </div>
                        </form>
  
                      </div>
                  </div>
  
        <script >
        /** Script to hide and show input divs while updating the profile */
        $("#enablePI").click(function() {
        	debugger;
            $("#update_pi :input").attr('disabled', !$("#update_pi :input").attr('disabled'));
            if ($(this).text() == "Edit") {
                $(this).text("Cancel"); 
                $("#pi_save").show();
            }
             else {
              $(this).text("Edit");
              $("#pi_save").hide();
              }; 
          });
          
        $("#enableEa").click(function() {
            $("#update_em :input").attr('disabled', !$("#update_em :input").attr('disabled'));
            if ($(this).text() == "Edit") {
                $(this).text("Cancel"); 
                $("#email_save").show();
            }
             else {
              $(this).text("Edit"); 
              $("#email_save").hide();
              }; 
          });
          
        $("#enableMob").click(function() {
            $("#update_mo :input").attr('disabled', !$("#update_mo :input").attr('disabled'));
            if ($(this).text() == "Edit") {
                $(this).text("Cancel"); 
                $("#mobile_save").show();
            }
             else {
              $(this).text("Edit"); 
              $("#mobile_save").hide();
              }; 
          });
        </script>           
                <!-- Status of All Approvals -->
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <%ArrayList<PersonInfo> ReqList=(ArrayList<PersonInfo>)session.getAttribute("ReqList"); %>
                     <div class="divMarginTop">  
                    <table class="table table-bordered">
                        <tr>
                        <th>Tutor Name</th>
                        <th>Course Name</th>
                        <th>Status</th>
                        </tr>
                            <c:forEach items="${ReqList}" var="req">
                                <tr>
                                <td>${req.fname} &nbsp; ${req.lname}</td>
                                <td>${req.cname}</td>
                                <td>
                                <c:choose>
                                    <c:when test="${req.status =='approved'}">
                                    <i class="text-uppercase text-success">${req.status}</i>
                                </c:when>
                                
                                <c:when test="${req.status =='rejected'}">
                                    <i class="text-uppercase text-danger">${req.status}</i>
                                </c:when>
                                
                                <c:otherwise>
                                    <i class="text-uppercase text-secondary">${req.status}</i>
                                </c:otherwise>
                                </c:choose>
                                </td>
                                </tr>
                            </c:forEach>
                        </table>
                        </div>
                </div>
                
                
                <!-- Student Approved Course List -->
                <div class="tab-pane fade" id="myCourse" role="tabpanel" aria-labelledby="myCourse-tab">
                        <div class="divMarginTop">
                            <c:forEach items="${applicationScope['ApprCourseList']}" var="course">
                            
                                <div class="card floatLeft shadow  mb-5 bg-white rounded" style="width: 20rem;">
                                    <div class="card-body">
                                        <h1 class="card-title">${course.cname}</h1>
                                        <blockquote class="blockquote mb-0">
                                            <footer class="blockquote-footer">given by <b >${course.fname}</b></footer>
                                        </blockquote>
                                          <br>
                                        <form action="RequestControl" method="post">
                                            <input type="hidden" name="tid" value=${course.tid } >
                                            <input type="hidden" name="cid" value=${course.cid } >
                                            <input type="submit" class="btn btn-outline-success" name="action" value="View">
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
                        <div class="col-md-6">
                           <h6> Your new Password must:</h6>
                            <ul>
                                <li>Be atleast 4 characters in length</li>
                                <li>Not be same as your current Password</li>
                                <li>Not contain common Password</li>
                            </ul>
                        </div>

                        <div class="col-md-6">
                                <h6 class="modal-title" id="exampleModalCenterTitle">Change Password</h6><br>
                                <form action="UpdateInfoController" method="post">
                                    
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="curr_pass" name="curr_pass" placeholder="Current Password">
                                        </div>

                                        <div class="form-group">
                                            <input type="text" class="form-control" id="new_pass" name="new_pass" placeholder="Type New Password">
                                        </div>
                        
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="retype_new_pass" name="retype_new_pass" placeholder="Retype New Password">
                                        </div>
                                        
                                        <div class="form-group ">
                                            <button name="update" value="changePassword" type="submit" class="btn btn-primary btn-block">CHANGE PASSWORD</button>
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
