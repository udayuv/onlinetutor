package com.onlinetutor.dao;

import java.util.ArrayList;

import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.Tutor;

public interface TutorDao {
	 boolean regTutor(Tutor tutor) throws Exception;
	 Tutor loginTutor(String phone, String pass)throws Exception;
	 ArrayList<PersonInfo> showRequest(int tid) throws Exception;
	 boolean approveReq(int tid, int uid,int cid) throws Exception;
	 Tutor getTutorDetails(String phone, String pass) throws Exception;
	 boolean rejectReq(int tid, int uid,int cid) throws Exception;
	 public boolean validateMobile(String phone) throws Exception ;
	 boolean updatePassword(int id,String curr_pwd,String new_pwd) throws Exception;
	 boolean resetPassword(int id, String curr_pwd, String new_pwd) throws Exception;
	boolean insertCourses(int tid, int cid, byte[] bytes, int filetypeid);
}
