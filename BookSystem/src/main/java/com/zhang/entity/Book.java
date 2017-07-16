package com.zhang.entity;

/**
 * 
 * @author Mr.Zhang 图书实体类
 */
public class Book {

	private int id;
	private String bookName;
	private String pressName; // 出版社名字
	private String autor;
	private int amount;
	private int bookType; // 图书类型
	private String floor; // 第几层
	private String area; // 区域
	private String bookrack; // 书架
	private String row;// 哪排
	private String lattice;// 哪格
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getLattice() {
		return lattice;
	}

	public void setLattice(String lattice) {
		this.lattice = lattice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
