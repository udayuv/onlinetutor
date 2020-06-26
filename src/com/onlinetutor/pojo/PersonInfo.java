package com.onlinetutor.pojo;

public class PersonInfo {
	int tid,uid, cid,exp;
	String fname, lname, email,addr,cname,qual,status;
	
	public PersonInfo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PersonInfo(String fname, String lname, String cname, String status) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cname = cname;
		this.status = status;
	}

	

	public PersonInfo(int tid, int uid, String fname, int cid, String cname, String status) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.cid = cid;
		this.fname = fname;
		this.cname = cname;
		this.status = status;
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getCid() {
		return cid;
	}


	public void setCid(int cid) {
		this.cid = cid;
	}


	public int getExp() {
		return exp;
	}


	public void setExp(int exp) {
		this.exp = exp;
	}


	public String getFname() {
		return fname;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getQual() {
		return qual;
	}


	public void setQual(String qual) {
		this.qual = qual;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "PersonInfo [tid=" + tid + ", uid=" + uid + ", cid=" + cid + ", exp=" + exp + ", fname=" + fname
				+ ", lname=" + lname + ", email=" + email + ", addr=" + addr + ", cname=" + cname + ", qual=" + qual
				+ ", status=" + status + "]";
	}


			
	
}
