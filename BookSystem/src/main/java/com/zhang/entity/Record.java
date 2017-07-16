package com.zhang.entity;

import java.util.Date;

/**
 * 
 * @author Mr.Zhang 记录实体类
 */
public class Record extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userid;
	private int bookid;
	private Date begigtime;
	private Date lasttime;
	private int status; // 状态 0未还 1已还

	private String bookName;
	private String pressName;
	private String autor;
	private int bookType;
	private String floor;
	private String area;
	private String bookrack;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getBookid() {
		return bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public Date getBegigtime() {
		return begigtime;
	}

	public void setBegigtime(Date begigtime) {
		this.begigtime = begigtime;
	}

	public Date getLasttime() {
		return lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPressName() {
		return pressName;
	}

	public void setPressName(String pressName) {
		this.pressName = pressName;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getBookType() {
		return bookType;
	}

	public void setBookType(int bookType) {
		this.bookType = bookType;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBookrack() {
		return bookrack;
	}

	public void setBookrack(String bookrack) {
		this.bookrack = bookrack;
	}

}
