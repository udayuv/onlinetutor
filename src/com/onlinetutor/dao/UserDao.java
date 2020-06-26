package com.onlinetutor.dao;

import java.util.ArrayList;

import com.onlinetutor.pojo.PersonInfo;
import com.onlinetutor.pojo.User;

public interface UserDao {
	 boolean regUser(User user) throws Exception;
	 User loginUser(String phone, String pass) throws Exception;
	 String checkRequest(int uid, int tid, int cid)throws Exception;
	 boolean sendReq(int uid,int tid,int cid) throws Exception;
	 User getUserDetails(String phone, String pass) throws Exception;
	 ArrayList<PersonInfo> showApprovals(int uid) throws Exception;
	 boolean validateMobile(String phone) throws Exception;
	 boolean giveFeedback(int tid,int uid,int cid,String msg,int stars)throws Exception;
	 boolean updatePassword(int id,String curr_pwd,String new_pwd) throws Exception;
	 boolean resetPassword(int id, String curr_pwd, String new_pwd) throws Exception;
	 boolean submitFeedback(int uid,int tid,int cid, int likes, String comments)throws Exception;
	byte[] displayAlldata(int filetype, int tid) throws Exception;
}
