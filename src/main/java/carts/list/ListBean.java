package carts.list;

import java.io.Serializable;

public class ListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int itemNo;
	private String productName;
	private int number;
	private int price;
	private String exist;
	
	public ListBean() {
		// TODO Auto-generated constructor stub
	}
	public ListBean(/*int itemNo,*/ String productName, int number, int price/*,String exist*/) {
		//this.itemNo = itemNo;
		this.productName = productName;
		this.number = number;
		this.price = price;
		//this.exist = exist;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	
	
}
