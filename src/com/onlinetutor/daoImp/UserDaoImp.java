package com.onlinetutor.daoImp;

import static com.onlinetutor.dbUtils.MyConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.onlinetutor.dao.UserDao;
import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.User;

public class UserDaoImp implements UserDao {
	
	PreparedStatement ps=null;
	ResultSet rs= null;
	User user =new User();
	static final Logger log = Logger.getLogger(UserDaoImp.class);
	int status=0;
	boolean isValid =false;
	@Override
	public boolean regUser(User user) throws Exception {
		try (Connection con = getConnection();) {

			isValid =validateMobile(user.getPhone());
			if(!isValid){
				ps= con.prepareStatement("insert into user (phone, password,secret_question,secret_answer) values(?,?,?,?)");
				ps.setString(1, user.getPhone());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getSecret_question());
				ps.setString(4, user.getSecret_answer());
			status= ps.executeUpdate();
			
			user= loginUser(user.getPhone(), user.getPassword());
			ps= con.prepareStatement("insert into person_info values("+user.getUid()+",'','','','','')");
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
		if(status>0)
		{
			log.info("User registered with name = "+user.getUid());
		return true;
		}
		else
		{
			System.out.println("Registration Failed");
			return false;
		}
	}

	
	@Override
	public User loginUser(String phone, String pass) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from user where phone=? and password=?");
			ps.setString(1, phone);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new User(rs.getInt(1),rs.getString(2), rs.getString(3));
			}
			return null;
		}
	}


	
	
	@Override
	public String checkRequest(int uid, int tid, int cid) throws Exception {
		String status = "";
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select status from request where tutor_id=? and user_id=? and course_id=?");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				status = rs.getString(1);
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

		 return status;
		
	}


	@Override
	public boolean sendReq(int uid, int tid, int cid) throws Exception {
		try (Connection con = getConnection();) {
			
			ps= con.prepareStatement("insert into request values(?,?,?,?)");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			ps.setString(4, "pending");
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
			log.info("User has raised a req for tutor: "+tid+" and course:"+cid);
		return true;
		}
		else
		{
			log.info("request for course failed ");
			return false;
		}
	}



	@Override
	public ArrayList<PersonInfo> showApprovals(int uid) throws Exception {
		ArrayList<PersonInfo> reqList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select first,last,ctitle,status from (select tutor_id,user_id,first,last,course.course_id,ctitle,status from course,(select tutor_id,user_id,first,last,course_id,status from person_info,request where person_info.pid=request.tutor_id) as reqdetails where course.course_id=reqdetails.course_id) as checkRequsest where user_id=?");
			ps.setInt(1, uid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 reqList.add(new PersonInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
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
	public User getUserDetails(String phone, String pass) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from (select pid,first,last,gender,email,phone,password,address,secret_question,secret_answer from person_info, user  where person_info.pid = user.uid) as udetails where phone=? and password=?");
			ps.setString(1, phone);
			ps.setString(2, pass);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
			return null;
		}
	}

	public User getUserDetails(String phone) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from (select pid,first,last,gender,email,phone,password,address,secret_question,secret_answer from person_info, user  where person_info.pid = user.uid) as udetails where phone=?");
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				System.out.println("In UserDao GetUserDetails ");
				if (rs.next())
					return new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
			}
			System.out.println("In UserDao GetUserDetails NULL");
			return null;
		}
	}



	@Override
	public boolean validateMobile(String phone) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from user where phone=?");
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return true;
			}
			return false;
		}
	}


	@Override
	public boolean giveFeedback(int tid, int uid, int cid, String msg, int stars) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("insert into feedback value values(?,?,?,?,?)");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			ps.setString(4, msg);
			ps.setInt(5, stars);
			status= ps.executeUpdate();
			
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
			log.info("Feedback saved by = "+uid);
		return true;
		}
		else
		{
			log.error("Feedback submition failed by: "+uid);
			return false;
		}
}


	@Override
	public boolean updatePassword(int id, String curr_pwd, String new_pwd) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update user set password=? where uid=? and password=?");
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
			log.info("User updated with name = "+user.getUid());
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
			ps= con.prepareStatement("update user set password=? where uid=?");
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
			log.info("User updated with name = "+user.getUid());
		return true;
		}
		else
		{
			System.out.println("Updation Failed");
			return false;
		}
	}


	@Override
	public boolean submitFeedback(int uid, int tid, int cid, int likes, String comments) throws Exception {
try (Connection con = getConnection();) {
			
			ps= con.prepareStatement("insert into feedback values(?,?,?,?,?)");
			ps.setInt(1, tid);
			ps.setInt(2, uid);
			ps.setInt(3, cid);
			ps.setString(4, comments);
			ps.setInt(5, likes);
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
			log.info("User has raised a req for tutor: "+tid+" and course:"+cid);
		return true;
		}
		else
		{
			log.info("request for course failed ");
			return false;
		}
	}
	
	
	/*Display All */
	@Override
	public byte[] displayAlldata(int filetype, int tid) throws Exception {
		
		try (Connection con = getConnection();) {	
			ps = con.prepareStatement("Select filedata from tutor_course where filetype=? and tutor_id=?");
     // pstmt.setString(1, imgid.trim());
			ps.setInt(1, filetype);
			ps.setInt(2, tid);
			rs = ps.executeQuery();
	  	 	if (rs.next())
	  	 		return rs.getBytes("filedata");
	  	 		
	}catch (Exception e) {
		// TODO: handle exception
	}
		return null;

	}
}

