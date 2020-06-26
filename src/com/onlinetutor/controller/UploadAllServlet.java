package com.onlinetutor.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.onlinetutor.daoImp.TutorDaoImp;
import com.onlinetutor.pojo.Tutor;

/**
 * Servlet implementation class UploadAllServlet
 */
@WebServlet("/UploadAllServlet")
@MultipartConfig
public class UploadAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UploadAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			boolean status;
			TutorDaoImp td=new TutorDaoImp();
			HttpSession session = request.getSession();
			Tutor tutor = (Tutor)session.getAttribute("Tutor");
			int tid = tutor.getTid();
			System.out.println(tid);
		 //int tid=Integer.parseInt(request.getParameter("tid"));
			int cid=Integer.parseInt(request.getParameter("courses"));
			System.out.println("courseNameId:"+cid);
			final Part filePart = request.getPart("file");
			int filetypeid=Integer.parseInt(request.getParameter("filetype"));
			System.out.println("FileType(pdf/image/video)Id:"+filetypeid);
		//c
			System.out.println(filePart.getContentType());
		
			/* ******************* Upload the image ******************  */
		if(filePart.getContentType().equals("image/jpeg"))
		{
		        /*String imgid = request.getParameter("imgid");*/
		        InputStream pdfFileBytes = null;
		        final PrintWriter writer = response.getWriter();
		        try {
		 
		        	System.out.println(filePart.getContentType());
		          if (!filePart.getContentType().equals("image/jpeg"))
		            {
		                       writer.println("<br/> Invalid File");
		                       return;
		            }
		 
		           else if (filePart.getSize()>1048576 ) { //2mb
		               {
		              writer.println("<br/> File size too (more than 2MB)big");
		              return;
		               }
		           }
		 
		            pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data
		            final byte[] bytes = new byte[pdfFileBytes.available()];
		            pdfFileBytes.read(bytes);  //Storing the binary data in bytes arra
		 
                 
                 /*  insert data*/
                
                  status= td.insertCourses(tid, cid, bytes, filetypeid);
                 if(status==true)
                 {writer.println("<br/> Image Successfully Stored");}
                 else
                 {
                	 writer.println("<br/> Image not Stored");
                 }
         
		        } catch (FileNotFoundException fnf) {
		            writer.println("You  did not specify a file to upload");
		            writer.println("<br/> ERROR: " + fnf.getMessage());
		 
		        } finally {
		 
		            if (pdfFileBytes != null) {
		                pdfFileBytes.close();
		            }
		            if (writer != null) {
		                writer.close();
		            }
		        }
			
		        /* ******************* Upload the Pdf ******************  */
		}else if(filePart.getContentType().equals("application/pdf"))            
		{
			
			  final Part filePartP = request.getPart("file");
		      String bookId = request.getParameter("bookId");
		      InputStream pdfFileBytes = null;
		      final PrintWriter writer = response.getWriter();
		        try {
		          if (!filePartP.getContentType().equals("application/pdf"))
		            {
		                writer.println("<br/> Invalid File");
		                return;
		            }
		 
		           else if(filePartP.getSize()>1048576 ) { //2mb
		               {
			              writer.println("<br/> File size too big");
			              return;
		               }
		           }
		            pdfFileBytes = filePartP.getInputStream();  // to get the body of the request as binary data
		            final byte[] bytes = new byte[pdfFileBytes.available()];
		             pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
		                 /*Store Pdf*/
		             
		                 status=td.insertCourses(tid, cid, bytes, filetypeid);
		                 if(status==true)
		                 {writer.println("<br/> pdf Successfully Stored");}
		                 else
		                 {
		                	 writer.println("<br/> pdf not Stored");
		                 } 
		                 writer.println("<br/> pdf Successfully Stored");
		 
		        } catch (FileNotFoundException fnf) {
		            writer.println("You  did not specify a file to upload");
		            writer.println("<br/> ERROR: " + fnf.getMessage());
		 
		        } finally {
		 
		            if (pdfFileBytes != null) {
		                pdfFileBytes.close();
		            }
		            if (writer != null) {
		                writer.close();
		            }
		}
		
		}
		
		
	}

}
