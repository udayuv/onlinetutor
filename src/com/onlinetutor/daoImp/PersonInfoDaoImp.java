package com.onlinetutor.daoImp;

import static com.onlinetutor.dbUtils.MyConnection.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.onlinetutor.dao.PersonInfoDao;
import com.onlinetutor.pojo.FeedbackPojo;
import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.User;

public class PersonInfoDaoImp implements PersonInfoDao {
	PreparedStatement ps=null;
	ResultSet rs= null;
	User user =new User();
	static final Logger log = Logger.getLogger(UserDaoImp.class);
	int status=0;
	

	@Override
	public boolean updatePersonInfo(int pid, String fname, String lname,String gender)
			throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update person_info set first=?, last=?, gender=? where pid=?");
			ps.setInt(4, pid);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, gender);
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
	public ArrayList<PersonInfo> showTutorCourse() throws Exception {
		ArrayList<PersonInfo> courseList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select pid,first,last,course_id, ctitle from person_info,(select tutor_id, tutor_course.course_id, ctitle from tutor_course,course where tutor_course.course_id=course.course_id)as cdetails where pid=cdetails .tutor_id");
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 PersonInfo pi = new PersonInfo();
				 pi.setTid(rs.getInt(1));
				 pi.setFname(rs.getString(2));
				 pi.setLname(rs.getString(3));
				 pi.setCid(rs.getInt(4));
				 pi.setCname(rs.getString(5));
				 
				 courseList.add(pi);
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

		 return courseList;
	}



	@Override
	public ArrayList<PersonInfo> getBySubjectId(int cid) throws Exception {
		ArrayList<PersonInfo> courseList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select * from(select pid,first,last,course_id, ctitle from person_info,(select tutor_id, tutor_course.course_id, ctitle from tutor_course,course where tutor_course.course_id=course.course_id)as cdetails where pid=cdetails .tutor_id)as bycourse where course_id=?");
			ps.setInt(1, cid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 PersonInfo pi = new PersonInfo();
				 pi.setTid(rs.getInt(1));
				 pi.setFname(rs.getString(2));
				 pi.setLname(rs.getString(3));
				 pi.setCid(rs.getInt(4));
				 pi.setCname(rs.getString(5));
				 
				 courseList.add(pi);
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

		 return courseList;
	}



	@Override
	public boolean updateEmail(int pid, String email) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update person_info set email=? where pid=?");
			ps.setInt(2, pid);
			ps.setString(1, email);
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
	public boolean updateMobile(int pid, String phone) throws Exception {
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("update person_info set phone=? where pid=?");
			ps.setInt(2, pid);
			ps.setString(1, phone);
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
	public ArrayList<PersonInfo> showApprovedCourse(int uid) throws Exception {
		ArrayList<PersonInfo> apprCourseList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select tutor_id,first,last,course_id,ctitle from (select tutor_id,user_id,first,last,course.course_id,ctitle,status from course, (select tutor_id,user_id,first,last,course_id,status from person_info,request where person_info.pid=request.tutor_id) as reqdetails where course.course_id=reqdetails.course_id) as checkRequsest where user_id=? and status='approved'");
			ps.setInt(1, uid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 PersonInfo pi = new PersonInfo();
				 pi.setTid(rs.getInt(1));
				 pi.setFname(rs.getString(2));
				 pi.setLname(rs.getString(3));
				 pi.setCid(rs.getInt(4));
				 pi.setCname(rs.getString(5));
				 
				 apprCourseList.add(pi);
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

		 return apprCourseList;
	}



	@Override
	public ArrayList<PersonInfo> showUploadedCourse(int tid) throws Exception {
		ArrayList<PersonInfo> uploadCourseList = new ArrayList<>();
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select pid,first,last,course_id, ctitle from person_info,(select tutor_id, tutor_course.course_id, ctitle from tutor_course,course where tutor_course.course_id=course.course_id)as cdetails where pid=?");
			ps.setInt(1, tid);
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 PersonInfo pi = new PersonInfo();
				 pi.setTid(rs.getInt(1));
				 pi.setFname(rs.getString(2));
				 pi.setLname(rs.getString(3));
				 pi.setCid(rs.getInt(4));
				 pi.setCname(rs.getString(5));
				 
				 uploadCourseList.add(pi);
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

		 return uploadCourseList;
	}

	
	@Override
	public ArrayList <FeedbackPojo >showFeedback() throws Exception {
		ArrayList<FeedbackPojo> feedbackList = new ArrayList<>();
	
		try (Connection con = getConnection();) {
			ps= con.prepareStatement("select first,last,feedback,likes from person_info,feedback where feedback.user_id=person_info.pid;");
			rs = ps.executeQuery(); 
			 while (rs.next()) {
				 FeedbackPojo fb=new FeedbackPojo();
				fb.setFname(rs.getString(1));
				fb.setLname(rs.getString(2));
				fb.setComments(rs.getString(3));
				fb.setLikes(rs.getInt(4));
				
				 feedbackList.add(fb);
				 System.out.println("FBL"+fb);
				
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

		return feedbackList;
	}
}

