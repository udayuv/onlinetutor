package com.onlinetutor.pojo;

public class Tutor {
	int tid, exp;
	String first,last,gender,email,qual,phone,password,address;

	String secret_question;
	String secret_answer;
	
	public Tutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tutor(String phone, String password, String qual, int exp) {
		super();
		this.phone = phone;
		this.password = password;
		this.qual = qual;
		this.exp = exp;
	}
	
	public Tutor(String phone, String password, String qual, int exp, String secret_question, String secret_answer) {
		super();
		this.phone = phone;
		this.password = password;
		this.qual = qual;
		this.exp = exp;
	}	

	public Tutor(int tid, int exp, String first, String last, String gender, String email, String qual, String phone,
			String password, String address) {
		super();
		this.tid = tid;
		this.exp = exp;
		this.first = first;
		this.last = last;
		this.gender = gender;
		this.email = email;
		this.qual = qual;
		this.phone = phone;
		this.password = password;
		this.address = address;
	}

	public Tutor(int tid, int exp, String first, String last, String gender, String email, String qual, String phone,
			String password, String address, String secret_question, String secret_answer) {
		super();
		this.tid = tid;
		this.exp = exp;
		this.first = first;
		this.last = last;
		this.gender = gender;
		this.email = email;
		this.qual = qual;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.secret_question = secret_question;
		this.secret_answer = secret_answer;
	}

	public Tutor(int tid, String phone, String password) {
		super();
		this.tid = tid;
		this.phone = phone;
		this.password = password;
	}
	

	

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
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

	public String getQual() {
		return qual;
	}

	public void setQual(String qual) {
		this.qual = qual;
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

	@Override
	public String toString() {
		return "Tutor [tid=" + tid + ", exp=" + exp + ", first=" + first + ", last=" + last + ", gender=" + gender
				+ ", email=" + email + ", qual=" + qual + ", phone=" + phone + ", password=" + password + ", address="
				+ address + ", secret_question=" + secret_question + ", secret_answer=" + secret_answer + "]";
	}




	
	
	
}
