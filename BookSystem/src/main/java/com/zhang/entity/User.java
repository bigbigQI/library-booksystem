package com.zhang.entity;

public class User {



	private int id;
	private String name;
	private String pass;
	private String code;
	private String phone;
	private String email;
	private int booksum; //借书量

	/**
	 * grade 表示权限 1为管理员 2位普通用户
	 */
	private int grade;
	/**
	 * 1为男 2为女
	 */
	private int sex;
	private String img;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getBooksum() {
		return booksum;
	}

	public void setBooksum(int booksum) {
		this.booksum = booksum;
	}
	
}
