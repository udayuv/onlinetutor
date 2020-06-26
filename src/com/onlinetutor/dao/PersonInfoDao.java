package com.onlinetutor.dao;

import java.util.ArrayList;

import com.onlinetutor.pojo.FeedbackPojo;
import com.onlinetutor.pojo.PersonInfo;

public interface PersonInfoDao {

	boolean updatePersonInfo(int pid, String fname, String lname,String gender)throws Exception;
	boolean updateEmail(int pid, String email)throws Exception;
	boolean updateMobile(int pid, String phone)throws Exception;
	ArrayList<PersonInfo> showTutorCourse()throws Exception;
	ArrayList<PersonInfo> getBySubjectId(int cid) throws Exception;
	ArrayList<PersonInfo> showApprovedCourse(int uid)throws Exception;
	ArrayList<PersonInfo> showUploadedCourse(int tid)throws Exception;
	ArrayList<FeedbackPojo> showFeedback() throws Exception;
}
