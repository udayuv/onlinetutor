package com.onlinetutor.pojo;


public class User {
	int uid;
	String fname, lname, gender, email, phone, password, address;
	String secret_question;
	String secret_answer;
	
	
	
	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
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



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getSecret_question() {
		return secret_question;
	}



	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}



	public String getSecret_answer() {
		return secret_answer;
	}



	public void setSecret_answer(String secret_answer) {
		this.secret_answer = secret_answer;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public User(int uid, String phone, String password) {
		super();
		this.uid = uid;
		this.phone = phone;
		this.password = password;
	}
	
	
	public User(String phone, String password) {
		super();
		this.phone = phone;
		this.password = password;
	}
	
	public User(String phone, String password, String secret_question, String secret_answer) {
		super();
		this.phone = phone;
		this.password = password;
		this.secret_question = secret_question;
		this.secret_answer = secret_answer;
	}
	
	public User(int uid, String fname, String lname, String gender, String email, String phone, String password,
			String address) {
		super();
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
	}
	
	public User(int uid, String fname, String lname, String gender, String email, String phone, String password,
			String address, String secret_question, String secret_answer) {
		super();
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.secret_question = secret_question;
		this.secret_answer = secret_answer;
	}



	@Override
	public String toString() {
		return "User [uid=" + uid + ", fname=" + fname + ", lname=" + lname + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", password=" + password + ", address=" + address + ", secret_question="
				+ secret_question + ", secret_answer=" + secret_answer + "]";
	}
	
	

	
		
	
}

