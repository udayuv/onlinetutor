<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<title>Insert title here</title>
</head>
<body>

   <div class="container-fluid">
       <div class="row ">&nbsp;</div>
       		<div class="col-md-10 offset-md-1">
			      <div class="col-md-9">
			             <div class="card-body divMarginTop col-md-5">
                     			<div class="divMarginTop">	
				                	<form id="viewform " action="DisplayImage" method="get">
										  <div class="form-group">
										    <label for="exampleFormControlSelect1">View Image</label>
											    <input type="hidden" name="showFile" value="1">
											    <input type="submit" class="btn btn-primary my-1" value="Show">
										  </div>
										  </form>  
										  
										  <form id="viewform " action="DisplayImage" method="get" >
                  							<div class="form-group">
											    <label for="exampleFormControlSelect1">View pdf</label>
											    <input type="hidden" name="showFile" value="2">
											    <input type="submit" class="btn btn-primary my-1" value="Show">
									   		 </div>
									  	</form>
										
										  	
									  	<form id="viewform " action="DisplayImage" method="get" >
                  							<div class="form-group">
											    <label for="exampleFormControlSelect1">View Video</label>
											    <input type="hidden" name="showFile" value="3">
											    <input type="submit" class="btn btn-primary my-1" value="Show">
									   		 </div>
									  	</form>    	
                       			</div>  
                		 </div>
         		</div>
       		</div>
		</div>


<!-- Button trigger modal -->
<button type="button" class="btn btn-link" data-toggle="modal" data-target="#exampleModalCenter">
  Feedback
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content" ><!-- style="width: 23rem;" -->
       
      <div class="modal-body">
         <form class="" action="FeedbackControl" method="post">                        
                        <!-- Rating Stars Box -->
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <br>
					      <div class='rating-stars text-center'>
					        <ul id='stars'>
					          <li class='star' title='Poor' data-value='1'>
					            <i class='fa fa-star fa-fw'></i>
					          </li>
					          <li class='star' title='Fair' data-value='2'>
					            <i class='fa fa-star fa-fw'></i>
					          </li>
					          <li class='star' title='Good' data-value='3'>
					            <i class='fa fa-star fa-fw'></i>
					          </li>
					          <li class='star' title='Excellent' data-value='4'>
					            <i class='fa fa-star fa-fw'></i>
					          </li>
					          <li class='star' title='WOW!!!' data-value='5'>
					            <i class='fa fa-star fa-fw'></i>
					          </li>
					        </ul>
					      </div>
                        <div class='text-message'></div>
                        <input type="hidden" id="rating" name="rating" value="">
                        	<div class="form-group">
                                <textarea class="form-control" id="comment" name="comment" rows="2" placeholder="text your comments"></textarea>
                            </div>
    
                        <div class="d-flex bd-highlight mb-3 ">
                        	<div class="ml-auto">
                        	<input type="submit" class="btn btn-outline-success" value="send">
                        	</div>
                            
                        </div>
                  </form> 
      </div>
        
    </div>
  </div>
  <script>
  $(document).ready(function(){
	  
	  // 1. Visualizing things on Hover - See next part for action on click 
	  $('#stars li').on('mouseover', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
	   
	    // Now highlight all the stars that's not after the current hovered star
	    $(this).parent().children('li.star').each(function(e){
	      if (e < onStar) {
	        $(this).addClass('hover');
	      }
	      else {
	        $(this).removeClass('hover');
	      }
	    });
	    
	  }).on('mouseout', function(){
	    $(this).parent().children('li.star').each(function(e){
	      $(this).removeClass('hover');
	    });
	  });
	  
	  
	//   2. Action to perform on click 
	  $('#stars li').on('click', function(){
	    var onStar = parseInt($(this).data('value'), 10); // The star currently selected
	    var stars = $(this).parent().children('li.star');
	    
	    for (i = 0; i < stars.length; i++) {
	      $(stars[i]).removeClass('selected');
	    }
	    
	    for (i = 0; i < onStar; i++) {
	      $(stars[i]).addClass('selected');
	    }
	    
	    // JUST RESPONSE (Not needed)
	    var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
	    var msg = "";
	    if (ratingValue > 1) {
	        msg = "Thanks! ";
	    }
	    else {
	        msg = "We will improve ourselves.";
	    }
	    
	    $("#rating").val(ratingValue);
	    
	  });
	  
	  
	});

  </script>
</div>
</body>
</html>