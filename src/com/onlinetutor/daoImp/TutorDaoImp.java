package com.onlinetutor.daoImp;

import static com.onlinetutor.dbUtils.MyConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.onlinetutor.dao.TutorDao;
import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.Tutor;

public class TutorDaoImp implements TutorDao {

	

	PreparedStatement ps=null;
	ResultSet rs= null;
	Tutor tutor =new Tutor();
	static final Logger log = Logger.getLogger(TutorDaoImp.class);
	int status=0;
	boolean isValid=false;
	
	@Override
	public boolean regTutor(Tutor tutor) throws Exception {
		try (Connection con = getConnection();) {
			isValid =validateMobile(tutor.getPhone());
			if(!isValid){
				ps= con.prepareStatement("insert into tutor (phone, password, qual, exp,secret_question,secret_answer) values(?,?,?,?,?,?)");
				ps.setString(1, tutor.getPhone());
				ps.setString(2, tutor.getPassword());
				ps.setString(3, tutor.getQual());
				ps.setInt(4, tutor.getExp());
				ps.setString(5, tutor.getSecret_question());
				ps.setString(6, tutor.getSecret_answer());
				status= ps.executeUpdate(); 
				
				tutor= loginTutor(tutor.getPhone(), tutor.getPassword());
				
				ps= con.prepareStatement("insert into person_info values("+tutor.getTid()+",'','','','','')");
				ps.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("SQLException in closing PreparedStatement");
			}
		//executing the query
		}
		if(status>0 )
		{
			log.info("User registered with name = "+tutor.getPhone());
		return true;
		}
		else
		{
			System.out.println("Registration Failed");
			return false;
		}
	}

	@Override
	public Tutor loginTutor(String phone, String pass)throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from tutor where phone=? and password=?");
			ps.setString(1, phone);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new Tutor(rs.getInt(1),rs.getString(2) , rs.getString(3));
			}
			return null;
		}
	}

	@Override
	public ArrayList<PersonInfo> showRequest(int tid) throws Exception {
		ArrayList<PersonInfo> reqList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from (select tutor_id,user_id,first,course.course_id,ctitle,status from course,(select tutor_id,user_id, first,course_id,status from person_info,request where person_info.pid=request.user_id) as reqdetails where course.course_id=reqdetails.course_id) as checkRequsest where tutor_id=?");
			ps.setInt(1, tid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 reqList.add(new PersonInfo(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
			      }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 finally {
		      try {
		        rs.close();
		        ps.close();
		      } catch (SQLException e) {
		    	  log.error("SQLException in closing PreparedStatement");
		    	  log.error("SQLException in closing ResultSet");
		    	  log.error("SQLException in closing Established Connection");
		      }
		    }

		 return reqList;
	}

	
	@Override
	public boolean approveReq(int tid, int uid, int cid) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update request set status='approved' where tutor_id=? and user_id=? and course_id=?");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			status = ps.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("SQLException in closing PreparedStatement");
			}
		//executing the query
		}
		if(status>0)
		{
			log.info("Req approved by user: "+tid);
		return true;
		}
		else
		{
			System.out.println("Approval Failed");
			return false;
		}
	}

	
	@Override
	public boolean rejectReq(int tid, int uid, int cid) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update request set status='rejected' where tutor_id=? and user_id=? and course_id=?");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			status = ps.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("SQLException in closing PreparedStatement");
			}
		//executing the query
		}
		if(status>0)
		{
			log.info("Req rejected by user: "+tid);
		return true;
		}
		else
		{
			System.out.println("Rejection Failed");
			return false;
		}
	}

	@Override
	public Tutor getTutorDetails(String phone, String pass) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from (select pid,exp,first,last,gender,email,qual,phone,password,address,secret_question,secret_answer from person_info, tutor where person_info.pid = tutor.tutor_id) as tdetails where phone=? and password=?");
			ps.setString(1, phone);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new Tutor(rs.getInt(1),rs.getInt(2) , rs.getString(3), rs.getString(4), rs.getString(5), 
							rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9),rs.getString(10), rs.getString(11), rs.getString(12));
			}
			return null;
		}
	}

	@Override
	public boolean validateMobile(String phone) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from tutor where phone=?");
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return true;
			}
			return false;
		}
	}

	@Override
	public boolean updatePassword(int id, String curr_pwd, String new_pwd) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update tutor set password=? where tutor_id=? and password=?");
			ps.setString(1, new_pwd);
			ps.setInt(2, id);
			ps.setString(3, curr_pwd);
			status = ps.executeUpdate(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("SQLException in closing PreparedStatement");
			}
		//executing the query
		}
		if(status>0)
		{
			log.info("User updated with name = "+tutor.getTid());
		return true;
		}
		else
		{
			System.out.println("Updation Failed");
			return false;
		}
	}

	@Override
	public boolean resetPassword(int id, String curr_pwd, String new_pwd) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update tutor set password=? where tutor_id=?");
			ps.setString(1, new_pwd);
			ps.setInt(2, id);
			status = ps.executeUpdate(); 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				ps.close();
			} catch (SQLException e) {
				log.error("SQLException in closing PreparedStatement");
			}
		//executing the query
		}
		if(status>0)
		{
			log.info("User updated with name = "+tutor.getTid());
		return true;
		}
		else
		{
			System.out.println("Updation Failed");
			return false;
		}
	}

	
	///   insert data 
		@Override
		public boolean insertCourses(int tid, int cid, byte[] bytes, int filetypeid) {
		
			try (Connection con = getConnection();){
			 int success=0;
	       
	           String query = "insert into tutor_course values (?,?,?,?)";
	           ps = con.prepareStatement(query);
	           ps.setInt(1, tid);
	           ps.setInt(2, cid);
	           ps.setBytes(3,bytes); 
	           ps.setInt(4, filetypeid);
	           success = ps .executeUpdate();
	           if(success>0)
	           	
	           	System.out.println("image Stored");
	            con.close(); 

	          return true;

			}catch (Exception e) {
				System.out.println("error image");
			}
			
			return false;
		}


}

