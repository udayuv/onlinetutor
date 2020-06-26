
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
        <div class=" divArticle col-md-3">
           <% Tutor tutor = (Tutor)session.getAttribute("Tutor");
              boolean flag=false;
              
              if(tutor.getFirst().equals("") || tutor.getLast().equals("") || tutor.getEmail().equals(""))
            	  flag=true;
              else
            	  flag=false;
              
              %>
		<div class="card shadow  mb-5 bg-white rounded" style="max-width: 18rem;">
			  <img class="card-img-top customImage " src="IMAGES/user.png" alt="Card image cap">
			              <!-- personal information -->
			  <div class="card-body">
			    <h5 class="card-title text-center"><b class="text-uppercase" ><%= tutor.getFirst() %></b></h5>
			    </div>
			    
              <ul class="list-group list-group-flush ">
                  <li class="list-group-item "><i class="fa fa-user" aria-hidden="true"></i>&nbsp;<%= tutor.getFirst()%> &nbsp; <%= tutor.getLast() %></li>
                  <li class="list-group-item"><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp; <%= tutor.getEmail() %></li>
                  <li class="list-group-item"><i class="fa fa-phone" aria-hidden="true"></i>&nbsp; <%= tutor.getPhone() %></li>
                  <li class="list-group-item"><i class="fa fa-address-card" aria-hidden="true"></i>&nbsp;<%= tutor.getAddress() %></li>
                  <li class="list-group-item"><i class="fa fa-graduation-cap"" aria-hidden="true"></i>&nbsp;<%= tutor.getQual() %></li>
                  <li class="list-group-item"><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;<%= tutor.getExp() %>&nbsp; years exp</li>
                  <li class="list-group-item">
                      <form action="LogoutControl" method="post">
                      <button type="submit"  class="btn btn-link" value="Logout" ><i class="fa fa-power-off" aria-hidden="true"></i>&nbsp;Logout</form>
                  </li>
                </ul>
             
          </div>
        </div>
        
        <div class=" col-md-9 ">
          <div class="card-body">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Pending Request</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Profile Update</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Upload Course</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" id="myCourse-tab" data-toggle="tab" href="#myCourse" role="tab" aria-controls="myCourse" aria-selected="false">Uploaded Course</a>
                </li>
              </ul>

              <div class="tab-content" id="myTabContent">
                <!-- to Show all coming requests -->
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="divMarginTop">
                        <%ArrayList<Request> ReqList=(ArrayList<Request>)session.getAttribute("ReqList");%>
                        
                                <c:forEach items="${ReqList}" var="req">
                                    <c:if test = "${req.status == 'pending'}">
                                        
                                        <form action="AccessControl" method="post">
                                        <input type="hidden" name="uid" value=${req.uid } >
                                        <input type="hidden" name="cid" value=${req.cid } >
                                        
                                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
	                                        <div class="row ">
			                                        <div class="col">
			                                        <strong>${req.fname}</strong> has requested access for <strong>${req.cname}</strong> course.
			                                        </div>
			                                        
			                                        <div class="col ">
			                                        	<div class="float-right ">
			                            					<button type="submit" class="btn btn-outline-success btn-sm" name="action" value="Approve"><i class="fa fa-check" aria-hidden="true">Approve</i></button>
													  	</div>
													  	<div class=" float-right mx-2">
													  		<button type="submit" class="btn btn-outline-danger btn-sm float-right" name="action" value="Reject"><i class="fa fa-remove" aria-hidden="true">Reject</i></button>
			                                        	</div>
			                                        </div>
	                                        </div>

										</div>
                                        </form>
                                        
                                    </c:if>
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
                                    <input type="text" class="form-control" id="fname" name="fname" disabled="disabled" value="<%= tutor.getFirst()%>">
                                  </div>
                                  <div class="form-group col-md-5">
                                    <input type="text" class="form-control" id="lname" name="lname" disabled="disabled" value="<%= tutor.getLast() %>">
                                  </div>
  
                                  <div id="pi_save" class="form-group col-md-2">
                                      <button name="update" value="piInfo" type="submit" class="btn btn-primary">Save</button>
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
                        <button id="enableMo" class="btn btn-link" data-toggle="modal" data-target="#exampleModalCenter">Change Password</button><br>
                        <form id="update_em" action="UpdateInfoController" method="post">
                                  
                                      <div class="form-row">
                                        <div class="form-group col-md-5">
                                          <input type="email" class="form-control" id="email" name="email" disabled="disabled" value="<%= tutor.getEmail() %>">
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
                                      <input type="text" class="form-control" id="phone" disabled="disabled" name="phone" value="<%= tutor.getPhone() %>">
                                    </div>
                                    <div id="mobile_save" class="form-group col-md-2">
                                        <button name="update" value="updateMobile" type="submit" class="btn btn-primary">Save</button>
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

                <!-- Form to upload course -->
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <div class="divMarginTop">  
                    
                    
                    
						<%if (flag){ %>
		                    <div class="alert alert-info" role="alert">
		  						Please Update the profile to upload course details!
							</div>
					   <%}else{ %>
					      <form id="viewform" action="UploadAllServlet" method="post" enctype="multipart/form-data"  >
                                    <div class="form-group">
                                      <label for="exampleFormControlSelect1">Select Course</label>
                                      <select class="form-control" id="exampleFormControlSelect1" name="courses">
                                         <option selected>Choose...</option>
                                         <option value="1">Java</option>
                 
                                         <option value="3">MySql</option>
                                         <option value="2">JavaScript</option>
                                      </select>
                                    </div>
                                    <div class="form-group">
                                      <label for="exampleFormControlSelect2">Select Content type</label>
                                      <select class="form-control" id="exampleFormControlSelect1" name="filetype">
                                          <option selected>Choose...</option>
                                          <option value="1">Image</option>
                                          <option value="2">Pdf</option>
                                          <option value="3">Video</option>
                                      </select>
                                    </div>
                                    <div class="form-group">
                                      <label for="exampleFormControlTextarea1">Upload File</label>
                                      <input type="file" name="file" value="Browse">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-primary my-1">Submit</button>
                              </form>   
					  <%} %>    
                    </div>
                </div>
                
                
                 <!-- Tutor Uploaded Course List -->
                <div class="tab-pane fade" id="myCourse" role="tabpanel" aria-labelledby="myCourse-tab">
                        <div class="divMarginTop">
                            <c:forEach items="${applicationScope['UploadCourseList']}" var="course">
                            
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
