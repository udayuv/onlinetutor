package com.onlinetutor.pojo;
public class FeedbackPojo {
	//String username;
	String comments,fname,lname;
	int likes;
	int tid,cid,uid;
	boolean status;
	
	/*public FeedbackPojo() {
		super();
	}
*/
	


	public String getFname() {
		return fname;
	}
	public FeedbackPojo(String comments, String fname, String lname, int likes, int tid, int cid, int uid,
			boolean status) {
		super();
		this.comments = comments;
		this.fname = fname;
		this.lname = lname;
		this.likes = likes;
		this.tid = tid;
		this.cid = cid;
		this.uid = uid;
		this.status = status;
	}
	
	public FeedbackPojo() {
	System.out.println(".........");
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "FeedbackPojo [comments=" + comments + ", fname=" + fname + ", lname=" + lname + ", likes=" + likes
				+ ", tid=" + tid + ", cid=" + cid + ", uid=" + uid + ", status=" + status + "]";
	}

	
	
}
